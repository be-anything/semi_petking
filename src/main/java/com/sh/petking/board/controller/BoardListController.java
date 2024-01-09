package com.sh.petking.board.controller;

import com.sh.petking.board.model.entity.Board;
import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.common.PetkingUtils;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.vo.UserVo;

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
    private ClubService clubService = new ClubService();
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

        // 접속자의 clubRole 확인하기
//        User user = (User) req.getSession().getAttribute("loginUser");
//        if(user != null){
//            ClubUsers clubUsers = clubService.findByUserId(user.getId());
//            System.out.println(clubUsers.getRole());
//            req.setAttribute("role", clubUsers.getRole());
//        }


        // b. pagebar영역
        int totalCount = boardService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        req.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(req, resp);
    }
}