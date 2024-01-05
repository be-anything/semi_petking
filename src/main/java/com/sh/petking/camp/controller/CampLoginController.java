package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/camp/campLogin")
public class CampLoginController extends HttpServlet {
    private CampService campService = new CampService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer = req.getHeader("Referer");
        System.out.println("referer = " + referer);

        if(!referer.contains("/camp/campLogin") || !referer.contains("user/userLogin")){
            req.getSession().setAttribute("next", referer);
        }

        req.getRequestDispatcher("/WEB-INF/views/camp/campLogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String businessId = req.getParameter("id");
        String businessPassword = req.getParameter("password");
        String _saveId = req.getParameter("saveId.checked");

        businessPassword = PetkingUtils.getEncryptedPassword(businessPassword, businessId);

        Camp camp = campService.findByBusinessId(businessId);
        HttpSession session = req.getSession();
        if(camp != null && businessPassword.equals(camp.getBusinessPassword())){
            session.setAttribute("loginCamp", camp);
            String locaion = req.getContextPath() + "/";
            String next = (String) session.getAttribute("next");
            if(next != null){
                locaion = next;
                session.removeAttribute("next");
            }
            resp.sendRedirect(locaion);
        }
        else{
            session.setAttribute("msg", "아이디가 존재하지 않거나, 비밀번호가 틀립니다.");
            resp.sendRedirect(req.getContextPath() + "/camp/campLogin");
        }
    }
}