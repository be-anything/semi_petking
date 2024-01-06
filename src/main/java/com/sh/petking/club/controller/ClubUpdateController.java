package com.sh.petking.club.controller;

import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.club.model.vo.ClubVo;
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
import java.util.UUID;

@WebServlet("/club/clubUpdate")
public class ClubUpdateController extends HttpServlet {

    private ClubService clubService = new ClubService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println(id);
        // 2. 업무로직
        ClubVo club = clubService.findById(id);
        req.setAttribute("club", club);
        // 3. forwarding
        req.getRequestDispatcher("/WEB-INF/views/club/clubUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DiskFileItemFactory - ServletFileUpload
        File repository = new File("C:\\Workspaces\\semi_petking\\src\\main\\webapp\\upload\\club");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(10 * 1024 * 1024); // 10mb
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        ClubVo club = new ClubVo();
        System.out.println(club);
        try {
            // 1. 사용자입력값 처리
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : fileItemList) {
                String name = fileItem.getFieldName();
                if (fileItem.isFormField()) {
                    // form field
                    String value = fileItem.getString("utf-8");
                    club.setValue(name, value);
                } else {
                    if (fileItem.getSize() > 0) {
                        String originalName = fileItem.getName();
                        int dotIndex = originalName.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalName.substring(dotIndex) : "";
                        String renamedName = UUID.randomUUID() + ext;
//                        ClubAttach attach = new ClubAttach();
//                        attach.setOriginalName(originalName);
//                        attach.setRenamedName(renamedName);
//                        club.addAttachment(attach);

                        File upFile = new File(repository, renamedName);
                        fileItem.write(upFile); // 파일 출력
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(club);

        // 2. 업무로직
        int result = clubService.updateClub(club);

        req.getSession().setAttribute("msg", "게시물을 성공적으로 수정했습니다.😁");
        // 3. redirect
        resp.sendRedirect(req.getContextPath() + "/club/clubList?id=" + club.getId());
    }
}