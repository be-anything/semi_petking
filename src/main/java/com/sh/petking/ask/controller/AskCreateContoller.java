package com.sh.petking.ask.controller;

import com.sh.petking.ask.model.service.AskService;
import com.sh.petking.ask.model.vo.AskVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/ask/askCreate")
public class AskCreateContoller extends HttpServlet {
    private AskService askService = new AskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 문의하기 버튼 클릭시 get form 제출 및 캠핑장 아이디 가져오기
        Long campId = Long.parseLong(req.getParameter("campId"));
        req.setAttribute("campId", campId);
        //작성 jsp page로 이동
        req.getRequestDispatcher("/WEB-INF/views/ask/askCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("askCreateController - doPost!");
        //name값으로 가져오는 값들.
        String userId = req.getParameter("userId"); //loginUser.id
        long campId = Long.parseLong(req.getParameter("campId"));
        String askTitle = req.getParameter("askTitle");
        String askContent = req.getParameter("askContent");
        System.out.println("문의자:"+userId);
       // System.out.println("캠핑장번호:"+campId);
        System.out.println("문의 제목:"+askTitle);
        System.out.println("문의 내용:"+askContent);

        AskVo ask = new AskVo();
        ask.setUserId(userId);
        ask.setCampId(campId); //임시 매직넘버
        ask.setAskTitle(askTitle);
        ask.setAskContent(askContent);
        ask.setAskRegDate(LocalDateTime.now()); //os기준 현재 시간으로 넘김 sysdate로 insert시 시간차 발생
        int result = askService.insertAsk(ask);
        req.getSession().setAttribute("msg","문의글을 성공적으로 등록하였습니다");

        //3.게시판 목록페이지로 redirect
        //3.게시판 목록페이지로 redirect -> 해당 유저의 문의글만 보이도록 이동

        System.out.println("문의 내용 작성 후  - 성공알려주는 alert ");
        resp.sendRedirect(req.getContextPath()+"/ask/askList?userId=" + userId);

    }
}