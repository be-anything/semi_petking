package com.sh.petking.room.controller;

import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 0102 객실정보 수정
 */
@WebServlet("/room/roomUpdate")
public class RoomUpdateController extends HttpServlet
{
    RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //do get으로 기존의 데이터를 읽어옴.

        //1.사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println("RoomUpdateController : "+id);

        //2.업무로직
        RoomVo room = roomService.findById(id);
        req.setAttribute("room",room); //단일객체 이므로 room
        System.out.println(room);
        //3.forwarding
        req.getRequestDispatcher("/WEB-INF/views/room/roomUpdate.jsp").forward(req,resp);

    }

    //수정할 값을 post방식을 통해 update한다.
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
}