package com.sh.petking.review.controller;

import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/review/reviewDetail")
public class ReviewDetailController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        
        // 사진이 필요함
        ReviewVo review = reviewService.findByIdWithAttach(id);
        req.setAttribute("review", review);
        // csvTag 핸들링
        List<String> tags = Arrays.asList(review.getReviewTag().split(","));
        req.setAttribute("tags", tags);

        System.out.println("controller" + review);
        req.getRequestDispatcher("/WEB-INF/views/review/reviewDetail.jsp").forward(req, resp);
    }
}