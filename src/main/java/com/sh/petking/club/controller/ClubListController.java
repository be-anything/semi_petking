package com.sh.petking.club.controller;

import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.service.ClubService;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Club> club = clubService.findAll();
        req.setAttribute("clubs", club);

        req.getRequestDispatcher("/WEB-INF/views/club/clubList.jsp").forward(req,resp);
    }
}