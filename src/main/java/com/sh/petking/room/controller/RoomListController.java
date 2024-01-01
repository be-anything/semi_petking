package com.sh.petking.room.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;
import com.sh.petking.user.model.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 객실 리스트
 * 기존 servlet 역할
 */
@WebServlet("/room/roomList")
public class RoomListController extends HttpServlet {
    private RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RoomListController- do get");
        List<RoomVo> rooms = roomService.findAll();
        System.out.println("=============================================");
        System.out.println(rooms);
        req.setAttribute("rooms", rooms);

        //int totalCount = roomService.getTotalCount();
        String url = req.getRequestURI();
        //String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        //req.setAttribute("pagebar", pagebar);





        req.getRequestDispatcher("/WEB-INF/views/room/roomList.jsp").forward(req, resp);
    }
}
