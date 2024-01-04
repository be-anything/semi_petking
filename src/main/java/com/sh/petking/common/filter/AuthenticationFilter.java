package com.sh.petking.common.filter;

import com.sh.petking.user.model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/user/userDetail",
        "/user/userUpdate",
        "/user/userDelete",
})
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 인증여부 검사
        HttpSession session = req.getSession();
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            session.setAttribute("msg", "로그인 후 사용가능합니다.");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        super.doFilter(req, resp, chain); // 없으면 servlet으로 안넘어가짐
    }
}
