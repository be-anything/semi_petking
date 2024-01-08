package com.sh.petking.club.controller;

import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.club.model.vo.ClubVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/club/clubDetail")
public class ClubDetailController extends HttpServlet {
    private ClubService clubService = new ClubService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 처리
        long id = Long.parseLong(req.getParameter("id"));

        ClubVo club = clubService.findById(id);
        System.out.println(id);
        // 2. 업무로직
//
        req.setAttribute("club", club);
        System.out.println(club);
        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/club/clubDetail.jsp").forward(req, resp);
    }
}