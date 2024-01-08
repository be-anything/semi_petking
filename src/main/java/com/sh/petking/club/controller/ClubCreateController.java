package com.sh.petking.club.controller;

import com.sh.petking.club.model.entity.Club;
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

import static java.lang.System.out;

@WebServlet("/club/clubCreate")
public class ClubCreateController extends HttpServlet {

    private ClubService clubService = new ClubService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clubName = req.getParameter("clubName");
        String userId = req.getParameter("userId");
        String clubIntroTitle = req.getParameter("clubIntroTitle");
        String clubIntroContent = req.getParameter("clubIntroContent");

        Club club = new Club(0L, clubName, clubIntroTitle, clubIntroContent, null, userId);

        System.out.println(club);

        req.getRequestDispatcher("/WEB-INF/views/club/clubCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값처리 및 파일업로드
        File repository = new File("C:\\Workspaces\\semi_petking\\src\\main\\webapp\\upload\\club");
        int sizeThreshold = 10 * 1024 * 1024; // 10mb (1mb = 1024kb, 1kb = 1024b)

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);

        ClubVo club = new ClubVo();

        // ServletFileUpload 실제요청을 핸들링할 객체
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            // 전송된 값을 하나의 FileItem으로 관리
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);

            for (FileItem item : fileItemList) {
                String name = item.getFieldName(); // input[name]
                if(item.isFormField()) {
                    // 일반 텍스트필드 : Club객체에 설정
                    String value = item.getString("utf-8");
                    out.println(name + " = " + value);
                    // Club객체에 설정자 로직 구현
                    club.setValue(name, value);
                }
                else {
                    // 파일 : 서버컴퓨터에 저장, 파일정보를 Attachment객체로 만들어서 db에 저장
                    if (item.getSize() > 0) {
                        out.println(name);
                        String originalName = item.getName(); // 업로드 파일명
                        out.println("파일 : " + originalName);
                        out.println("크기 : " + item.getSize() + " byte");

                        int dotIndex = originalName.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalName.substring(dotIndex) : "";

                        UUID uuid = UUID.randomUUID(); // 고유한 문자열 토큰 발급
                        String renamedName = uuid + ext; // 저장된 파일명 (파일덮어쓰기, 인코딩이슈 방지)
                        out.println("새 파일명 : " + renamedName);

                        // 서버컴퓨터 파일 저장
                        File upFile = new File(repository, renamedName);
                        item.write(upFile); // throw Exception

                        // Attachment 객체생성
//                        BoardAttach attach = new BoardAttach();
//                        attach.setOriginalName(originalName);
//                        attach.setRenamedName(renamedName);
//                        board.addAttachment(attach);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        out.println(club); // club객체, attach객체들

        // 2. 업무로직
        int result = clubService.insertClub(club);
        req.getSession().setAttribute("msg", "게시글을 성공적으로 등록했습니다. 😉");

        // 3. redirect 목록페이지
        resp.sendRedirect(req.getContextPath() + "/club/clubList");
    }
}