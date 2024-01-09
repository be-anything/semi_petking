package com.sh.petking.reservation.controller;

import com.sh.petking.reservation.model.service.ReservationService;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 특정 캠핑장 선택 후 진입하는 예약 메인 페이지
 * 현재 4번 캠핑장 [가고파캠핑장] 기준으로 작성 ->해당 캠핑장에 등록된 객실들을 보여줘야한다..
 */
@WebServlet("/reservation/reservationMain")
public class ReservationMain extends HttpServlet 
{
    ReservationService reservationService = new ReservationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("예약메인 ReservationMain");
        long campId = Long.parseLong(req.getParameter("campId"));
        System.out.println(campId+"================객실리스트 캠핑장연결=======================");
        //0109 camp_id=26 기준으로 헤더->예약하기(임시) 눌렀을 때 26번 캠핑장에 해당하는 모든 객실을 먼저 띄워주기로 합니다.
        //임시로 붙여놨던 캠핑장 아이디 입력하는 input은 삭제할 예정.
        List<Room> room = reservationService.findByRoomCampId(campId);

        System.out.println("예약메인 ReservationMain - campId 결과값");
        System.out.println("객실 갯수 : "+ room.size());
        req.setAttribute("rooms",room);

        //객실 갯수가 0이라면..
        if(room.size()==0)
        {
            req.setAttribute("roomCount",room.size());
        }


        req.getRequestDispatcher("/WEB-INF/views/reservation/reservationMain.jsp").forward(req, resp);
    }
}