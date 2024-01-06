package com.sh.petking.admin.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/campDeleteRequest")
public class AdminCampDeleteRequestController extends HttpServlet {
    private CampService campService = new CampService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        CampVo camp = campService.findById(id);
        req.setAttribute("camp", camp);

        req.getRequestDispatcher("/WEB-INF/views/admin/adminCampDeleteRegistList.jsp").forward(req,resp);
    }
}