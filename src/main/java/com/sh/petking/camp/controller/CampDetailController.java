package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/camp/campDetail")
public class CampDetailController extends HttpServlet {
    private CampService campService = new CampService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        Long id = Long.parseLong(req.getParameter("id"));
        CampVo camp = campService.findById(id);
        // 2. 업무로직
        req.setAttribute("camp", camp);
        System.out.println(camp);
        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campDetail.jsp").forward(req, resp);
    }
}