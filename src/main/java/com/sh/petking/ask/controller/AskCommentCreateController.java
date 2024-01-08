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

/**
 * 0104 문의 답변
 * 기존에 있는 문의글을 update하는것임.
 * 
 */

@WebServlet("/ask/askCommentCreate")
public class AskCommentCreateController extends HttpServlet {
    private AskService askService = new AskService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력값 처리
        System.out.println("AskCommentCreateController do post");
        long askId = Long.parseLong(req.getParameter("id"));
        long campId = Long.parseLong(req.getParameter("campId"));
        String askComment = req.getParameter("askComment");
        System.out.println("문의글번호:"+askId+"/캠프장번호:"+campId+"/답변내용/"+askComment);

        AskVo ask = new AskVo();
        ask.setId(askId);
        ask.setCampId(campId);
        ask.setAskComment(askComment);
        ask.setAskCommentRegDate(LocalDateTime.now());


        //2.업무로직
        int result = askService.updateAsk(ask);
        req.getSession().setAttribute("msg","문의 답변이 등록되었습니다.");

        //3.redirect 문의목록으로 리다이렉트처리
        resp.sendRedirect(req.getContextPath()+"/ask/askList");
    }
}