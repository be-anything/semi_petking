package com.sh.petking.user.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/userDelete")
public class UserDeleteController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/userDetail.jsp").forward(req, resp);
        // 사용자 입력값 처리
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String id = loginUser.getId();
        System.out.println(id);

        // 업무로직
        int result = userService.deleteUser(id);

        // 세션해제 후 새로 생성해 msg저장
//        session.invalidate();

//        session = req.getSession();
        session.setAttribute("msg", "회원탈퇴완료");
        session.invalidate();

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/");
    }
}