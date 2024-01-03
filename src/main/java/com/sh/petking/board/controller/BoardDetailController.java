package com.sh.petking.board.controller;

import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/boardDetail")
public class BoardDetailController extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 처리
        Long id = Long.parseLong(req.getParameter("id"));
        BoardVo board =  boardService.findById(id);

        // 2. 업무로직
        req.setAttribute("board", board);
        System.out.println(board);

        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req, resp);
    }
}
