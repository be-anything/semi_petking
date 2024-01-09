package com.sh.petking.common.filter;

import com.sh.petking.common.Role;
import com.sh.petking.user.model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser == null || loginUser.getRole() != Role.A){
            session.setAttribute("msg", "관리자만 이용 가능합니다.🐶");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        super.doFilter(req, resp, chain);
    }
}
