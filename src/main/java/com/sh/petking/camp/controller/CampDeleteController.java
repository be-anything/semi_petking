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

@WebServlet("/camp/campDelete")
public class CampDeleteController extends HttpServlet {
    private CampService campService = new CampService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Camp camp = campService.findById(id);
        camp.setCampState(2);
        int result = campService.updateCampState(camp);

        // 사용자 메시지
        Map<String, Object> resultMap = Map.of("msg", "캠핑장 삭제 요청을 보냈습니다.");

        // 3. redirect
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());
    }
}