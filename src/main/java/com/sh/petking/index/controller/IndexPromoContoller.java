package com.sh.petking.index.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.index.vo.CampPromotionVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class IndexPromoContoller extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CampPromotionVo> promotionVos = campService.finaPromoAll();
        req.setAttribute("promotionVos", promotionVos);

        List<Camp> camps = campService.findNewCampAll();
        req.setAttribute("camps", camps);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}