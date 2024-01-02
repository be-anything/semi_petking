package com.sh.petking.admin.controller;

import com.sh.petking.common.Role;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/updateUserRole")
public class AdminUpdateUserRole extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String _role = req.getParameter("role");
        Role role = Role.valueOf(_role);
        System.out.println(role);
        User user = new User();
        user.setId(id);
        user.setRole(role);

        int result = userService.updateUserRole(user);
        System.out.println(user);
        System.out.println(result);
        req.getSession().setAttribute("msg", "회원권한을 성공적으로 업데이트했습니다.");
        resp.sendRedirect(req.getContextPath() + "/user/userList");
    }
}