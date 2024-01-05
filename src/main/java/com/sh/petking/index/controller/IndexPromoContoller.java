package com.sh.petking.index.controller;

import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.index.model.service.IndexPromoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index/promo")
public class IndexPromoContoller extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CampVo> campVos = campService.finaPromoAll();
        req.setAttribute("campVos", campVos);

        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}