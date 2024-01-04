package com.sh.petking.camp.controller;

import com.google.gson.Gson;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/camp/checkBusinessId")
public class CheckBusinessIdController extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        String businessNumber = req.getParameter("businessNumber");
        System.out.println(businessNumber);

        // 업무로직
        Camp camp = campService.findByBusinessNumber(businessNumber);
        System.out.println(camp);
        boolean result = camp == null;
        System.out.println(result);
        // 응답
        resp.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = Map.of("result", result);
        new Gson().toJson(map, resp.getWriter());
    }
}