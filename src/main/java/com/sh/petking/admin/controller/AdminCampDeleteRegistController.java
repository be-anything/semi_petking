package com.sh.petking.admin.controller;

import com.sh.petking.admin.model.service.AdminCampListService;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/deleteRegist")
public class AdminCampDeleteRegistController extends HttpServlet {
    private AdminCampListService adminCampListService = new AdminCampListService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore) {};
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);

        List<CampVo> campVos = adminCampListService.findDeleteAll(param);
        req.setAttribute("campVos", campVos);

        int totalCount = adminCampListService.getTotalDeleteCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);
        req.getSession().setAttribute("msg", "승인완료");

        req.getRequestDispatcher("/WEB-INF/views/admin/adminCampDeleteList.jsp").forward(req,resp);
    }
}