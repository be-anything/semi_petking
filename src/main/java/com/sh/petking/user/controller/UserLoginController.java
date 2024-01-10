package com.sh.petking.user.controller;

import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.common.PetkingUtils;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;
import com.sh.petking.user.model.vo.UserVo;

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
    private ClubService clubService = new ClubService();

    // 로그인 폼 페이지
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 머물렀던 페이지를 세션에 저장
        String referer = req.getHeader("Referer");
        System.out.println("referer = " + referer); // 저장된정보 확인

        if (!referer.contains("/camp/campLogin") && !referer.contains("user/userLogin"))
            req.getSession().setAttribute("next", referer);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/user/userLogin.jsp");
        requestDispatcher.forward(req, resp);
    }

    // 로그인 처리
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자 입력값 가져오기
        String id = req.getParameter("id");
        String pw = PetkingUtils.getEncryptedPassword(req.getParameter("password"), id);
//        System.out.println(id + pw);

        // 업무 로직
        User user = userService.findById(id);

        // 세션생성 / 가져오기
        HttpSession session = req.getSession();
        if (user != null && pw.equals(user.getPassword())) {
            // 로그인 성공
            session.setAttribute("loginUser", user);
            session.setAttribute("msg", "로그인에 성공하셨습니다.");
            String location = req.getContextPath() + "/";
            String next = (String) req.getSession().getAttribute("next");

            // 접속자의 clubRole 확인하기
            user = (User) req.getSession().getAttribute("loginUser");
            if(user != null){
                ClubUsers clubUsers = clubService.findByUserId(user.getId());
                if(clubUsers != null) {
                    req.getSession().setAttribute("loginUserClubRole", clubUsers.getRole());
                }
            }

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