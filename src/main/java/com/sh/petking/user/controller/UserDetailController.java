package com.sh.petking.user.controller;

import com.sh.petking.user.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/userDetail")
public class UserDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            session.setAttribute("msg", "로그인 후 사용가능합니다.");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        req.getRequestDispatcher("WEB-INF/views/user/userDetail.jsp").forward(req, resp);
    }
}