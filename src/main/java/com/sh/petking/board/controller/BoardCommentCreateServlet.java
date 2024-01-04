package com.sh.petking.board.controller;

import com.sh.petking.board.model.entity.BoardComment;
import com.sh.petking.board.model.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/boardCommentCreate")
public class BoardCommentCreateServlet extends HttpServlet {
    private BoardService boardService = new BoardService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 처리
        long boardId = Long.parseLong(req.getParameter("boardId"));
        String userId = req.getParameter("userId");
        String content = req.getParameter("content");

        BoardComment comment = new BoardComment();
        comment.setBoardId(boardId);
        comment.setUserId(userId);
        comment.setContent(content);
        System.out.println(comment);

        // 2. 업무로직
        int result = boardService.insertBoardComment(comment);
        req.getSession().setAttribute("msg", "댓글을 정상등록했습니다. 😄");

        // 3. redirect
        resp.sendRedirect(req.getContextPath() + "/board/boardDetail?id=" + boardId);
    }
}