package com.sh.petking.delUser.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.delUser.model.entity.DelUser;
import com.sh.petking.delUser.model.service.DelUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/delUser/delUserList")
public class DelUserListController extends HttpServlet {
    private DelUserService delUserService = new DelUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int limit = 10;
        try{
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);

        List<DelUser> delUsers = delUserService.findAll(param);
        req.setAttribute("delUsers", delUsers);

        int totalCount = delUserService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        req.getRequestDispatcher("/WEB-INF/views/delUser/delUserList.jsp").forward(req, resp);

    }
}