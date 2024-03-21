package com.sh.petking.board.controller;

import com.sh.petking.board.model.entity.BoardAttach;
import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/board/boardUpdate")
public class BoardUpdateController extends HttpServlet {
    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println(id);
        // 2. 업무로직
        BoardVo board = boardService.findById(id);
        req.setAttribute("board", board);
        // 3. forwarding
        req.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DiskFileItemFactory - ServletFileUpload
        File repository = new File("/var/webapps/upload/");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(10 * 1024 * 1024); // 10mb
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        BoardVo board = new BoardVo();
        System.out.println(board);
        try {
            // 1. 사용자입력값 처리
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : fileItemList) {
                String name = fileItem.getFieldName();
                if (fileItem.isFormField()) {
                    // form field
                    String value = fileItem.getString("utf-8");
                    board.setValue(name, value);
                } else {
                    if (fileItem.getSize() > 0) {
                        String originalName = fileItem.getName();
                        int dotIndex = originalName.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalName.substring(dotIndex) : "";
                        String renamedName = UUID.randomUUID() + ext;
                        BoardAttach attach = new BoardAttach();
                        board.addAttachment(attach);

                        File upFile = new File(repository, renamedName);
                        fileItem.write(upFile); // 파일 출력
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(board);

        // 2. 업무로직
        int result = boardService.updateBoard(board);

        req.getSession().setAttribute("msg", "게시물을 성공적으로 수정했습니다.😁");
        // 3. redirect
        resp.sendRedirect(req.getContextPath() + "/board/boardDetail?id=" + board.getId());
    }
}