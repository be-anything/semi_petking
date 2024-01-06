package com.sh.petking.reservation.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 특정 캠핑장 선택 후 진입하는 예약 메인 페이지
 * 현재 4번 캠핑장 [가고파캠핑장] 기준으로 작성 ->해당 캠핑장에 등록된 객실들을 보여줘야한다..
 */
@WebServlet("/reservation/reservationMain")
public class ReservationMain extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("예약메인 ReservationMain");
        req.getRequestDispatcher("/WEB-INF/views/reservation/reservationMain.jsp").forward(req, resp);
    }
}