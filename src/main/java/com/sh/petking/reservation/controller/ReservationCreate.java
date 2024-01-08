package com.sh.petking.reservation.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 0108 예약내역을 새로 insert하는 단
 */
@WebServlet("/reservation/reservationCreate")
public class ReservationCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReservationCreate, do get.......................");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReservationCreate, do post......................");
        long pTagValue = Long.parseLong(req.getParameter("pTagValue"));

        System.out.println("최종요금 : "+pTagValue);
    }
}