package com.sh.petking.user.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.petking.common.LocalDateTypeAdapter;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/userProcessReservation")
public class UserProcessReservationController extends HttpServlet {
    private UserService userService = new UserService();
    private ReservationService reservationService = new ReservationService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값
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
        int totalCount = reservationService.getTotalProcessReservCount(param);
//        req.setAttribute("processTotalCount", totalCount);
        String url = req.getRequestURI();

        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
//        req.setAttribute("processPagebar", pagebar);

        // 사용자 아이디 및 페이지바로 (진행중) 예약 내역 가져오기
        List<ReservationVo> reservations = reservationService.findByProcessReservUserId(param);
        System.out.println(reservations);

        resp.setContentType("application/json; charset=utf-8");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("reservations", reservations);
        resultMap.put("processTotalCount", totalCount);
        resultMap.put("processPagebar", pagebar);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
        gson.toJson(resultMap, resp.getWriter());
    }
}