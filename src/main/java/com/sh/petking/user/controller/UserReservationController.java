package com.sh.petking.user.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.reservation.model.entity.Reservation;
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
        int totalCount = reservationService.getTotalDonReservCount(param);
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI();

        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        // 사용자 아이디 및 페이지바로 (완료된) 예약 내역 가져오기
        List<ReservationVo> reservations = reservationService.findByDonReservUserId(param);
        System.out.println(reservations);
        req.setAttribute("reservations", reservations);

        req.getRequestDispatcher("/WEB-INF/views/user/userReservation.jsp").forward(req, resp);
    }
}