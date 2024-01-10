package com.sh.petking.common.filter;

import com.sh.petking.camp.model.entity.Camp;
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
        "/camp/campAskDetail",
        "/camp/campAskList",
        "/camp/campAttachDelete",
        "/camp/campDelete",
        "/camp/campDetailUpdate",
        "/camp/campManagement",
        "/camp/campReviewList",
        "/camp/campUpdate",
        "/camp/campUpdate",
})
public class AuthenticationCampFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        // 인증여부 검사
        HttpSession session = req.getSession();
        Camp loginCamp = (Camp) req.getSession().getAttribute("loginCamp");
        if (loginCamp == null) {
            session.setAttribute("msg", "사업자 로그인 후 사용가능합니다.");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        super.doFilter(req, resp, chain); // 없으면 servlet으로 안넘어가짐
    }
}
