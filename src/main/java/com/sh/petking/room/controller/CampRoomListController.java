package com.sh.petking.room.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/camp/campRoomList")
public class CampRoomListController extends HttpServlet {
    RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long campId = Long.parseLong(req.getParameter("id"));
        List<RoomVo> rooms = roomService.findRoomListByCampId(campId);
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/WEB-INF/views/room/campRoomList.jsp").forward(req,resp);
    }
}