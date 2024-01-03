package com.sh.petking.user.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/userPasswordUpdate")
public class UserPasswordUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    // 비밀번호 변경 페이지
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/userPasswordUpdate.jsp").forward(req, resp);
    }

    // 비밀번호 변경처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String id = loginUser.getId();

        String location = req.getContextPath();
        String msg = null;

        // 사용자 입력값 처리(기존 비밀번호, 새로운 비밀번호)
        String oldPassword = PetkingUtils.getEncryptedPassword(req.getParameter("oldPassword"), id);
        String newPassword = PetkingUtils.getEncryptedPassword(req.getParameter("newPassword"), id);

        // 기존비밀번호 비교
        if (oldPassword.equals(loginUser.getPassword())) {
            System.out.println("비교성공");

            // 업무로직
            loginUser.setPassword(newPassword);
            int result = userService.userPasswordUpdate(loginUser);
            msg = "비밀번호 변경 완료";
            location += "/user/userDetail";
        } else {
            msg = "비밀번호를 다시 확인해주세요";
            location += "/user/userPasswordUpdate";
        }

        // 리다이렉트처리
        session.setAttribute("msg", msg);
        resp.sendRedirect(location);
    }
}
