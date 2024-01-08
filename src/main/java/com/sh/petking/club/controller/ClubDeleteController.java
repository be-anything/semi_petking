package com.sh.petking.club.controller;

import com.sh.petking.club.model.service.ClubService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/club/clubDelete")
public class ClubDeleteController extends HttpServlet {
    private ClubService clubService = new ClubService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 사용자입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        // 2. 업무로직
        int result = clubService.deleteClub(id);
        req.getSession().setAttribute("msg", "동아리를 삭제를 성공했습니다. 🤗");
        // 3. 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/club/clubList");
    }
}