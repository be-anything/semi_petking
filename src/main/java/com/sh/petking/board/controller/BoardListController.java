package com.sh.petking.board.controller;

import com.sh.petking.board.model.entity.Board;
import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/board/boardList")
public class BoardListController extends HttpServlet {
    private BoardService boardService = new BoardService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 가져오기
        int page = 1; // 기본값
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore) {}

        Map<String, Object> param = Map.of("page", page, "limit", limit);
        System.out.println(param);

        // 2. 업무로직
        List<BoardVo> boards = boardService.findAll(param);
        req.setAttribute("boards", boards);
        System.out.println(boards);

        // b. pagebar영역
        int totalCount = boardService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        req.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(req, resp);
    }
}