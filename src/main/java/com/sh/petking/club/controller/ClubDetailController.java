package com.sh.petking.club.controller;

import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.club.model.vo.ClubVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/club/clubDetail")
public class ClubDetailController extends HttpServlet {

    private ClubService clubService = new ClubService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 처리
//        long id = Long.parseLong(req.getParameter("id"));

        // 조회수 관련 처리
//        Cookie[] cookies = req.getCookies();
//        List<String> clubCookieValues = getClubCookieValues(cookies);
//        boolean hasRead = clubCookieValues.contains(String.valueOf(id));
//        System.out.println(hasRead);

//        ClubVo club =  clubService.findById(id, hasRead);
//        ClubVo club =  clubService.findById(id);

//        System.out.println("댓글-----------");
//        System.out.println(club.getComments());

        // 2. 업무로직
//        req.setAttribute("club", club);
//        System.out.println(club);

        // 3. 포워딩
//        if(!hasRead){
//            clubCookieValues.add(String.valueOf(id));
//            String value = String.join("/", clubCookieValues);
//            Cookie cookie = new Cookie("club", value);
//            cookie.setMaxAge(365 * 24 * 60 * 60);
//            cookie.setPath(req.getContextPath() + " /club/clubDetail");
//            resp.addCookie(cookie);
//        }
        req.getRequestDispatcher("/WEB-INF/views/club/clubDetail.jsp").forward(req, resp);
    }

//    private List<String> getClubCookieValues(Cookie[] cookies) {
//        List<String> clubCookieValues = new ArrayList<>();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                String name = cookie.getName();
//                String value = cookie.getValue();
//                if ("club".equals(name)) {
//                    String[] temp = value.split("/");
//                    for (String _id : temp) {
//                        clubCookieValues.add((_id));
//                    }
//                }
//            }
//        }
//        return clubCookieValues;
//    }
}