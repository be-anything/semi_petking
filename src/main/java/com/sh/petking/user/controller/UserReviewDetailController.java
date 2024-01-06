package com.sh.petking.user.controller;

import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/userReviewDetail")
public class UserReviewDetailController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ReviewVo review = reviewService.findById(id);
        req.setAttribute("review", review);
        req.getRequestDispatcher("/WEB-INF/views/review/reviewDetail.jsp").forward(req, resp);
    }
}