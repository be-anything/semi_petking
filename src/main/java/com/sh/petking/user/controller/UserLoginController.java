package com.sh.petking.user.controller;

import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/userLogin")
public class UserLoginController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 머무를 페이지 세션에 저장
        String referer = req.getHeader("Referer");
//        System.out.println("referer = " + referer); // 저장된정보 확인

        if (!referer.contains("/user/userlogin"))
            req.getSession().setAttribute("next", referer);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/user/userLogin.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 입력값 인코딩 처리
        req.setCharacterEncoding("utf-8");

        // 사용자 입력값 가져오기
        String id = req.getParameter("id");
        String pw = req.getParameter("password");
//        System.out.println(id + pw);

        // 업무 로직
        User user = userService.findById(id);
//        System.out.println(user);

        // 세션생성 / 가져오기
        HttpSession session = req.getSession();
        if (user != null && pw.equals(user.getPassword())) {
            session.setAttribute("loginUser", user);
            String location = req.getContextPath() + "/";
            String  next = (String) req.getSession().getAttribute("next");
            if (next != null) {
                location = next;
                req.getSession().removeAttribute("next");
            }
            resp.sendRedirect(location);
        } else {
            session.setAttribute("msg", "아이디, 비밀번호를 다시 확인해주세요.");
            resp.sendRedirect(req.getContextPath() + "/user/userLogin");
        }
        // view단 처리
    }
}