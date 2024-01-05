package com.sh.petking.reservation.controller;

import com.google.gson.Gson;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 0105 혜진
 * 비동기(ajax)로 객실 찾기.
 * 0.캠핑장 4번에 속하는 모든 객실을 찾는다.
 * select * from room where camp_id=4;
 */

@WebServlet("/reservation/reservationRoomSearch")
public class ReservationRoomSearch extends HttpServlet
{
    private RoomService roomService = new RoomService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력 값 처리
        System.out.println("ReservationRoomSearch do get!");
        //2.업무 로직
        List<RoomVo> room = roomService.findByCampId(4);
        System.out.println("결과:"+room);

//        long campId = Long.parseLong(req.getParameter("campId"));
//        System.out.println("===============================");
//        System.out.println("campId:"+campId);
//        List<RoomVo> room = roomService.findByCampId(campId);
//        System.out.println("결과:"+room);

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

}