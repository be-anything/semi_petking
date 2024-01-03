package com.sh.petking.user.controller;

import com.google.gson.Gson;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user/UserCheckIdDuplicate")
public class UserCheckIdDuplicateServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 입력값 처리
        String id = req.getParameter("id");

        // 업무로직
        User user = userService.findById(id);
        boolean result = user == null;

        // 응답 json 작성
        resp.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = Map.of("result", result);
        new Gson().toJson(map, resp.getWriter());
    }
}
