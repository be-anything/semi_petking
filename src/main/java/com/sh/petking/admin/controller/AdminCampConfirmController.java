package com.sh.petking.admin.controller;

import com.sh.petking.camp.model.entity.Approve;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/CampConfirm")
public class AdminCampConfirmController extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        CampVo camp = campService.findById(id);
        req.setAttribute("camp", camp);
        req.getRequestDispatcher("/WEB-INF/views/admin/adminCampConfirm.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        int campState = Integer.parseInt(req.getParameter("campState"));
        String campMsg = req.getParameter("campMsg");
        Camp camp = campService.findById(id);
        camp.setCampState(campState);
        int result = campService.updateCamp(camp);
        req.getSession().setAttribute("msg", "승인이 완료되었습니다.☺️");
        String url = "/admin/registList?id=" + id;
        if(campState == -1){
            Approve approve = new Approve();
            approve.setCampId(id);
            approve.setCampMsg(campMsg);
            result = campService.insertApprove(approve);
            req.getSession().setAttribute("msg", "반려처리가 완료되었습니다.😑");
        } else if (campState == 2) {
            Long id1 = Long.parseLong(req.getParameter("id"));
            Camp camp1 = campService.findById(id);
            camp.setCampState(campState);
            int result1 = campService.updateDeleteCamp(camp);
            System.out.println(id1);
            System.out.println(camp1);
            System.out.println(result1);
            req.getSession().setAttribute("msg", "삭제가 완료되었습니다.😑");
            url = "/admin/deleteRegist";
        }
        resp.sendRedirect(req.getContextPath() + url);


    }
}















