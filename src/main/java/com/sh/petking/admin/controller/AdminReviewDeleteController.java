package com.sh.petking.admin.controller;

import com.sh.petking.review.model.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/reviewDelete")
public class AdminReviewDeleteController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        int result = reviewService.deleteReview(id);
        System.out.println(result);
        req.getSession().setAttribute("msg", "리뷰를 삭제했습니다.");

        resp.sendRedirect(req.getContextPath() + "/review/reviewList");
    }
}