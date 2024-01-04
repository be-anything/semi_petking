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

@WebServlet("/camp/campAttachDelete")
public class CampAttachDeleteController extends HttpServlet {
    private CampService campService = new CampService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        Long attachId = Long.parseLong(req.getParameter("attachId"));
        System.out.println(attachId);

        // 업무로직
        int result = campService.deleteCampAttach(attachId);

        // 응답
        resp.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = Map.of("result", result);
        new Gson().toJson(map, resp.getWriter());


    }
}