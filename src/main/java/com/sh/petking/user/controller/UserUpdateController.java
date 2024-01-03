package com.sh.petking.user.controller;

import com.sh.petking.common.RandomNicknameGenerator;
import com.sh.petking.common.Role;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/userUpdate")
public class UserUpdateController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 인코딩처리
        req.setCharacterEncoding("utf-8");

        // 사용자 입력값 가져오기
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String originalProfileName = req.getParameter("originalProfileName");
        String renamedProfileName = req.getParameter("renamedProfileName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

    User user = new User(id, null, 0L, nickname, name, password, originalProfileName, renamedProfileName, email,
            phone, 0L, Role.U, null);
        System.out.println(user);

        // 업무로직
        int result = userService.updateUser(user);

        // db수정 성공하면 session속성의 loginUser도 업데이트
        User userUpdated = userService.findById(id);
        req.getSession().setAttribute("loginUser", userUpdated);

        // redirect
        resp.sendRedirect(req.getContextPath() + "/user/userDetail");
    }
}
