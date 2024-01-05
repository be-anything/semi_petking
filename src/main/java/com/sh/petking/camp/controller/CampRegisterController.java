package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/camp/campRegister")
public class CampRegisterController extends HttpServlet {
    private CampService campService = new CampService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/camp/campRegister.jsp").forward(req, resp);
    }

    /**
     * 위도 경도도 찾자~
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값
        String businessId = req.getParameter("businessId");
        String businessPassword = req.getParameter("businessPassword");
        String businessNumber = req.getParameter("businessNumber");
        String businessName = req.getParameter("businessName");
        String campName = req.getParameter("campName");
        String campIntro = req.getParameter("campIntro");
        String campPhone = req.getParameter("campPhone");
        String campAddr = req.getParameter("campAddr");
        double campLcLa = Double.parseDouble(req.getParameter("campLcLa"));
        double campLcLo = Double.parseDouble(req.getParameter("campLcLo"));

        // 비밀번호 암호화하기
        businessPassword = PetkingUtils.getEncryptedPassword(businessPassword, businessId);
        Camp camp = new Camp(0L, businessId, businessPassword, businessNumber, businessName, campName, campIntro, campPhone, campAddr, campLcLa, campLcLo, "default.png", "default.png", 0, null);
        System.out.println(camp);

        // 2. 업무로직
        int result = campService.insertCamp(camp);
        
        // 3. redirect 하기
        resp.sendRedirect(req.getContextPath() + "/camp/campLogin");
    }
}