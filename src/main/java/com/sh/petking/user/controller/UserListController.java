package com.sh.petking.user.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.user.model.service.UserService;
import com.sh.petking.user.model.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/userList")
public class UserListController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int limit = 10;
        try{
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore) {};
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);

        List<UserVo> users = userService.findAll(param);
        req.setAttribute("users", users);

        int totalCount = userService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);
        System.out.println(users);

        req.getRequestDispatcher("/WEB-INF/views/user/userList.jsp").forward(req, resp);
    }
}