package com.sh.petking.user.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.reservation.model.service.ReservationService;
import com.sh.petking.reservation.model.vo.ReservationVo;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/userReservation")
public class UserReservationController extends HttpServlet {
    private UserService userService = new UserService();
    private ReservationService reservationService = new ReservationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 완료된 예약내역 가져오기
        // 사용자 아이디 가져오기
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        user = userService.findById(user.getId());

        int page = 1;
        int limit = 5;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        param.put("page", page);
        param.put("limit", limit);
        param.put("userId", user.getId());

        // 페이지바
        int doneTotalCount = reservationService.getTotalDonReservCount(param);
        req.setAttribute("doneTotalCount", doneTotalCount);
        String url = req.getRequestURI();

        String donePagebar = PetkingUtils.getPagebar(page, limit, doneTotalCount, url);
        req.setAttribute("donePagebar", donePagebar);

        // 사용자 아이디 및 페이지바로 (완료된) 예약 내역 가져오기
        List<ReservationVo> doneReservations = reservationService.findByDonReservUserId(param);
        System.out.println(doneReservations);
        req.setAttribute("doneReservations", doneReservations);


        // 진행중인 예약내역 가져오기
        // 1. 사용자 입력값
        // 사용자 아이디 가져오기
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};

        // 페이지바
        int processTotalCount = reservationService.getTotalProcessReservCount(param);
        req.setAttribute("processTotalCount", processTotalCount);

        String processPagebar = PetkingUtils.getPagebar(page, limit, processTotalCount, url);
        req.setAttribute("processPagebar", processPagebar);

        // 사용자 아이디 및 페이지바로 (진행중) 예약 내역 가져오기
        List<ReservationVo> processReservations = reservationService.findByProcessReservUserId(param);
        req.setAttribute("processReservations", processReservations);
        
        
        // 취소된 예약내역 가져오기
        // 1. 사용자 입력값
        // 사용자 아이디 가져오기
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};

        // 페이지바
        int cancelTotalCount = reservationService.getTotalCancelReservCount(param);
        req.setAttribute("cancelTotalCount", cancelTotalCount);

        String cancelPagebar = PetkingUtils.getPagebar(page, limit, cancelTotalCount, url);
        req.setAttribute("processPagebar", cancelPagebar);

        // 사용자 아이디 및 페이지바로 (진행중) 예약 내역 가져오기
        List<ReservationVo> cancelReservations = reservationService.findByCancelReservUserId(param);
        req.setAttribute("cancelReservations", cancelReservations);


        req.getRequestDispatcher("/WEB-INF/views/user/userReservation.jsp").forward(req, resp);
    }
}