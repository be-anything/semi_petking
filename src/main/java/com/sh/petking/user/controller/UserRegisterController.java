package com.sh.petking.user.controller;

import com.sh.petking.common.Role;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/userRegister")
public class UserRegisterController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/userRegister.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 인코딩처리
        req.setCharacterEncoding("utf-8");

        // 사용자 입력값 가져오기
        String id = req.getParameter("id");
        String nickname = req.getParameter("nickname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String originProfileName = req.getParameter("originProfileName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");



        User user = new User(id, null, 0L, nickname, name, password, originProfileName,
                    null, email, phone, 0L, Role.U, null);
        System.out.println(user);

        // 업무로직
        int result = userService.insertUser(user);

        // 회원가입성공 메세지
        req.getSession().setAttribute("msg", "회원가입완료. 로그인해주세요.");

        // view단
        resp.sendRedirect(req.getContextPath() + "/");

    }
}
