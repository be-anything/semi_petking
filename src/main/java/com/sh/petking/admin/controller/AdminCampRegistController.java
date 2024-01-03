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

@WebServlet("/admin/registList")
public class AdminCampRegistController extends HttpServlet {
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

        List<CampVo> campVos = adminCampListService.findRegistAll(param);
        req.setAttribute("campVos", campVos);

        int totalCount = adminCampListService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        req.getRequestDispatcher("/WEB-INF/views/admin/adminCampRegistList.jsp").forward(req,resp);
    }
}