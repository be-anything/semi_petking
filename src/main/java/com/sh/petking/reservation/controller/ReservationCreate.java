package com.sh.petking.reservation.controller;

import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.reservation.model.entity.ReservationPay;
import com.sh.petking.reservation.model.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 0108 예약내역을 새로 insert하는 단
 */
@WebServlet("/reservation/reservationCreate")
public class ReservationCreate extends HttpServlet {

    ReservationService reservationService = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReservationCreate, do get......................."); //select

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ReservationCreate, do post......................"); //insert update delete
        long campId = Long.parseLong(req.getParameter("campId"));
        System.out.println("캠핑장아이디 : "+campId);
        long roomId = Long.parseLong(req.getParameter("roomId"));
        System.out.println("룸아이디 : "+roomId);
        String userId = req.getParameter("userId");
        System.out.println("유저아이디 : "+userId);
        String firstDay = req.getParameter("firstDay");
        System.out.println("숙박첫날 : "+firstDay);
        String lastDay = req.getParameter("lastDay");
        System.out.println("숙박마지막날 : "+lastDay);
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(firstDay, dateformatter);
        LocalDate endDate = LocalDate.parse(lastDay, dateformatter);
        long totalFee_before = Long.parseLong(req.getParameter("totalFee_before"));
        System.out.println("최종요금 : "+totalFee_before);
        Integer personSelect = Integer.parseInt(req.getParameter("personSelect"));
        System.out.println("최종인원 : "+personSelect);

        //getParameter로 가져온 값들을 Revervation객체에 담아준다.
        Reservation reservation = new Reservation();
        reservation.setCampId(campId);
        reservation.setRoomId(roomId);
        reservation.setUserId(userId);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setNop(personSelect);

        //getParameter의 값을 reservationPay 객체에 담아준다.
        ReservationPay reservationPay = new ReservationPay();
        reservationPay.setReservEx(totalFee_before);//결제금액
        reservationPay.setReservUsePoint(0);//사용한포인트, 현재 넘기는 값이 없어서 0으로 임시처리

//        HashMap<String, Object> payMap = new HashMap<String, Object>();
//        payMap.put("totalFee_before",totalFee_before);
//        payMap.put("use_point",0);

        //2.업무로직
        int result = reservationService.insertReservaion(reservation,reservationPay);
        System.out.println("isnert한 후 결과..(현재 페이지:reservation Create)");
        //3.게시판 목록페이지로 redirect
        //resp.sendRedirect(req.getContextPath()+"/user/userReservation");
//        long usePoint = Long.parseLong(req.getParameter("usePoint"));
//        System.out.println("사용할 포인트 : "+usePoint);
        //받아온 값을 가지고 reservation table에 새 예약 내역을 insert해준다.
        //id(시퀀스),캠핑장아이디,룸아이디,유저아이디,숙박시작날짜,숙박종료날짜,인원수,기본스테이터스1(default)
    }
}