package com.sh.petking.reservation.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.room.model.exception.RoomException;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;
import com.sh.petking.user.model.entity.Point;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;
import com.sh.petking.user.model.vo.UserVo;
import com.sun.tools.jconsole.JConsoleContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@WebServlet("/reservation/ReservationProgress")
public class ReservationProgress extends HttpServlet
{
    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet 예약진행 페이지 ReservationProgress");

        HttpSession session = req.getSession();

        String firstDay = (String) session.getAttribute("firstDay");
        String lastDay = (String) session.getAttribute("lastDay");
        int roomId = (Integer) session.getAttribute("roomId");
        System.out.println(firstDay+"///////"+lastDay);
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld1 = LocalDate.parse(firstDay, dateformatter);
        LocalDate ld2 = LocalDate.parse(lastDay, dateformatter);
        System.out.println("do get 첫날 : "+ld1);
        System.out.println("do get 마지막날 : "+ld2);
        System.out.println("do get 예약할 객실 아이디 : "+roomId);

        //현재 로그인된 상태라면 user값 가지고있는지 체크해보기
        User user = (User) session.getAttribute("loginUser");
        if(user!=null)
            System.out.println("현재 로그인한 유저 : "+user);

        //1.사용자 입력값 처리
        try {

            System.out.println("현재 객실 글 번호:"+roomId);
            //2.업무로직
            //조회
            RoomVo room = roomService.findById(roomId); //id와 조회여부까지 확인한다.
            //조회한 room 정보 확인
            System.out.println("예약 페이지 현재 객실 번호:"+room);

            if(user!=null) {
                //user정보 긁어오기 point
                UserVo userVo = userService.findGradeId(user.getId());
                req.setAttribute("userVo", userVo);

                List<Point> point = userService.findPointAll(user.getId());
                System.out.println("나의 포인트:" + point);
                req.setAttribute("point", point);
            }



            //xss공격대비 escapehtml처리
            String safeHtml = PetkingUtils.escapeHtml(room.getRoomIntro());
            //개행문자(\n) -> <br>
            room.setRoomIntro(PetkingUtils.convertLineFeedToBr(safeHtml));
            req.setAttribute("room", room);

            req.setAttribute("firstDay", ld1);
            req.setAttribute("lastDay", ld2);
            req.setAttribute("roomId", roomId);
            //3.forward
            req.getRequestDispatcher("/WEB-INF/views/reservation/ReservationProgress.jsp").forward(req, resp);



        } catch (Exception e)
        {
            throw new RoomException("객실글 상세보기 오류",e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("예약 진행 do post..........................................................");
        String firstDay = req.getParameter("firstDay");
        String lastDay = req.getParameter("lastDay");
        System.out.println(firstDay+"///////"+lastDay);
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld1 = LocalDate.parse(firstDay, dateformatter);
        LocalDate ld2 = LocalDate.parse(lastDay, dateformatter);
        System.out.println("do post 첫날 : "+ld1);
        System.out.println("do post마지막날 : "+ld2);
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        System.out.println("do post예약할 객실 아이디 : "+roomId);

        req.setAttribute("firstDay",firstDay);
        req.setAttribute("lastDay",lastDay);
        req.setAttribute("roomId",roomId);

        HttpSession session = req.getSession();

        session.setAttribute("firstDay",firstDay);
        session.setAttribute("lastDay",lastDay);
        session.setAttribute("roomId",roomId);

        //3.게시판 목록페이지로 redirect
      // resp.sendRedirect(req.getContextPath()+"/reservation/ReservationProgress");
    }
}