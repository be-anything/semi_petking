package com.sh.petking.user.controller;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.board.model.entity.BoardAttach;
import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;
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
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/review/reviewCreate")
public class UserReviewCreateController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long campId = Long.parseLong(req.getParameter("campId"));
        req.setAttribute("campId", campId);
        req.getRequestDispatcher("/WEB-INF/views/review/reviewCreate.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리 및 파일 업로드
        File repository = new File("C:\\Users\\min_j\\Dropbox\\Workspaces\\semi_petking\\src\\main\\webapp\\upload\\review");
        int sizeThreshold = 10 * 1024 * 1024; // 10mb 10 * 1kb * 1kb (1mb = 1024kb, 1kb = 1024b)
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);

        ReviewVo review = new ReviewVo();
        List<Attachment> attachments = new ArrayList<>();

        // ServletFileUpload 실제 요청을 핸들링할 객체
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            // 전송된 값을 하나의 FileItem으로 관리
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);

            for (FileItem item: fileItemList) {
                String name = item.getFieldName();
                if(item.isFormField()) {
                    // 일반 텍스트 : Board 객체에 설정
                    String value = item.getString("utf-8");
                    System.out.println(name + " = " + value);
                    // Board 객체에 설정자 로직 구현
                    review.setValue(name, value);
                    review.setRegDate(LocalDateTime.now());
                    review.setBoardAttr(2L);
                }
                else {
                    // 파일 : 서버컴퓨터에 저장, 파일정보를 Attachment 객체로 만들어서 db에 저장
                    if(item.getSize() > 0){
                        String originalFilename = item.getName(); // 업로드 파일명
                        System.out.println("파일 : " + originalFilename);
                        System.out.println("크기 : " + item.getSize() + "byte");
                        int dotIndex = originalFilename.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalFilename.substring(dotIndex) : "";

                        UUID uuid = UUID.randomUUID(); // 고유한 문자열 토큰 발급
                        String renamedFilename = uuid + ext; // 저장된 파일명(파일 덮어쓰기, 인코딩이슈 방지)
                        System.out.println("새 파일명 : " + renamedFilename);

                        // 서버컴퓨터 파일 저장
                        File upFile = new File(repository, renamedFilename);
                        item.write(upFile); // throw Exception

                        // Attachment 객체 생성
                        Attachment attach = new Attachment();
                        attach.setOriginalName(originalFilename);
                        attach.setRenamedName(renamedFilename);
                        attachments.add(attach);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println(review);

        Map<String, Object> param = new HashMap<>();
        param.put("review", review);
        param.put("attachments", attachments);

        // 2. 업무로직
        int result = reviewService.insertReview(param);
        // session에 저장
        req.getSession().setAttribute("msg", "리뷰를 성공적으로 등록했습니다. 😎");

        // 3. redirect - board/boardList
        resp.sendRedirect(req.getContextPath() + "/user/userReviewList");
    }
}