package com.sh.petking.club.controller;

import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.club.model.vo.ClubVo;
import com.sh.petking.common.PetkingUtils;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.wish.model.entity.Wish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/club/clubList")
public class ClubListController extends HttpServlet {

    private ClubService clubService = new ClubService();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        List<Club> clubs = clubService.findAll();
//        req.setAttribute("clubs", clubs);
//        System.out.println(clubs);
//
//        req.getRequestDispatcher("/WEB-INF/views/club/clubList.jsp").forward(req,resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 가져오기
        try {
        } catch (NumberFormatException ignore) {}

        // 2. 업무로직
        List<Club> clubs = clubService.findAll();
        req.setAttribute("clubs", clubs);
        System.out.println(clubs);

        req.getRequestDispatcher("/WEB-INF/views/club/clubList.jsp").forward(req, resp);
    }
}