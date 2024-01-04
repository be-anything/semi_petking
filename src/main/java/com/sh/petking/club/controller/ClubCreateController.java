package com.sh.petking.club.controller;

import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/club/clubCreate")
public class ClubCreateController extends HttpServlet {

    private ClubService clubService = new ClubService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/club/clubCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        long id = Long.parseLong(req.getParameter("id"));
        String clubName = req.getParameter("clubName");
        String clubIntroTitle = req.getParameter("clubIntroTitle");
        String clubIntroContent = req.getParameter("clubIntroContent");
        LocalDateTime regDate = LocalDateTime.parse(req.getParameter("regDate"));
        String userId = req.getParameter("userId");

        Club club = new Club(id, clubName, clubIntroTitle, clubIntroContent, regDate, userId);
        System.out.println(club);

        // 업무로직
        int result = clubService.insertClub(club);

        // 회원가입성공 메세지
        req.getSession().setAttribute("msg", "동아리 생성이 완료되었습니다.");

        // view단
        resp.sendRedirect(req.getContextPath() + "/board/boardList");
    }
}