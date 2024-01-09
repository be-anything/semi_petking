package com.sh.petking.review.controller;

import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/review/reviewDetail")
public class ReviewDetailController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        // 조회수 관련처리
        Cookie[] cookies = req.getCookies();
        List<String> reviewCookieValues = getReviewCookieValues(cookies);
        boolean hasRead = reviewCookieValues.contains(String.valueOf(id));
        System.out.println(hasRead);

        // 사진이 필요함
        ReviewVo review = reviewService.findByIdWithAttach(id, hasRead);
        System.out.println(review);

        if(!hasRead){
            reviewCookieValues.add(String.valueOf(id));
            String value = String.join("/", reviewCookieValues);
            Cookie cookie = new Cookie("review", value);
            cookie.setMaxAge(365 * 24 * 60 * 60);
            cookie.setPath(req.getContextPath() + " /review/reviewDetail");
            resp.addCookie(cookie);
        }

        if(review.getReviewTag() != null){
            // csvTag 핸들링
            List<String> tags = Arrays.asList(review.getReviewTag().split(","));
            req.setAttribute("tags", tags);
        }

        req.setAttribute("review", review);


        System.out.println("controller" + review);
        req.getRequestDispatcher("/WEB-INF/views/review/reviewDetail.jsp").forward(req, resp);
    }

    private List<String> getReviewCookieValues(Cookie[] cookies) {
        List<String> reviewCookieValues = new ArrayList<>();
        if(cookies != null){
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                if("review".equals(name)){
                    String[] temp = value.split("/");
                    for (String _id: temp) {
                        reviewCookieValues.add((_id));
                    }
                }
            }
        }
        return reviewCookieValues;
    }
}