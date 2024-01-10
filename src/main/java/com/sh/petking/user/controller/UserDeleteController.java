package com.sh.petking.user.controller;

import com.sh.petking.delUser.model.entity.DelUser;
import com.sh.petking.delUser.model.service.DelUserService;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/user/userDelete")
public class UserDeleteController extends HttpServlet {
    private UserService userService = new UserService();
    private DelUserService delUserService = new DelUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("탈퇴할 회원: " + loginUser);
        String id = loginUser.getId();

        int result = userService.deleteUser(id);

        // 회원탈퇴성공하면 del_Users Table에 추가
        if (result > 0) {
            DelUser delUser = new DelUser();
            delUser.setUserId(loginUser.getId());
            delUser.setName(loginUser.getName());
            delUser.setRole(loginUser.getRole());
            delUser.setRegDate(LocalDate.from(loginUser.getRegDate()));

            int delResult = delUserService.insertDelUser(delUser);
            System.out.println("삭제된 회원: " + delResult);

            session.invalidate();
            session = req.getSession();
            session.setAttribute("msg", "회원 탈퇴를 완료했습니다.");
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            // 회원 탈퇴 실패 시의 처리
            session.setAttribute("msg", "회원 탈퇴에 실패했습니다.");
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}