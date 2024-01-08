package com.sh.petking.board.controller;

import com.sh.petking.board.model.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/boardRequest")
public class BoardRequestController extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 사용자입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        // 2. 업무로직
        int result = boardService.requestBoard(id);
        req.getSession().setAttribute("msg", "동아리 가입신청이 완료되었습니다. 🤗");
        // 3. 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/board/boardRequest");
    }
}