package com.sh.petking.reservation.controller;

import com.google.gson.Gson;
import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.reservation.model.service.ReservationService;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 0105 혜진
 * 비동기(ajax)로 객실 찾기.
 * 0.캠핑장 4번에 속하는 모든 객실을 찾는다.
 * select * from room where camp_id=4;
 */

@WebServlet("/reservation/reservationRoomSearch")
public class ReservationRoomSearch extends HttpServlet
{
    private ReservationService reservationService = new ReservationService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력 값 처리
        System.out.println("ReservationRoomSearch do get!");

        String firstDay = req.getParameter("firstDay");
        String lastDay = req.getParameter("lastDay");
        System.out.println(firstDay+"/"+lastDay);
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld1 = LocalDate.parse(firstDay, dateformatter);
        LocalDate ld2 = LocalDate.parse(lastDay, dateformatter);
        System.out.println("첫날 : "+ld1);
        System.out.println("마지막날 : "+ld2);
        int campId = Integer.parseInt(req.getParameter("campId"));
        System.out.println("검색할 캠핑장 아이디 : "+campId);
        //2.업무 로직
        //map에 캠핑아이디,첫날,마지막날짜를 담아 쿼리에 인자값으로 넘긴다.
        Map<String, Object> params = new HashMap<>();
        params.put("firstDay", ld1);
        params.put("lastDay", ld2);
        params.put("campId", campId);
        List<RoomVo> room = reservationService.findByCampId(params);
        //List<Reservation> reservations = reservationService.findAbleRoom(params);
        //List<Room> room = reservationService.findAbleRoom(campId);
        System.out.println(campId+"번 캠핑장 객실 리스트 결과:"+room);


        //3.응답 처리 : 자바 컬렉션 데이터를 json으로 변환 후에 출력 by gson
        //header : 미디어타입:application/json  인코딩
        resp.setContentType("application/json; charset=utf-8");

        /**
         * Gson
         * toJson : java->json
         * fromJson : json->java
         */
//        String jsonData = new Gson().toJson(celebs);
//        System.out.println(jsonData);
//        resp.getWriter().print(jsonData);
        //위의 세 줄을 아래 한줄로.
        new Gson().toJson(room, resp.getWriter());
    }
    //ajax-gson을 사용하여 별도의 페이지 이동없이 바로 값 전달
}