package com.sh.petking.reservation.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/reservation/ReservationProgress")
public class ReservationProgress extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("예약진행 페이지 ReservationProgress");
        req.getRequestDispatcher("/WEB-INF/views/reservation/ReservationProgress.jsp").forward(req, resp);
    }
}