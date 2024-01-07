package com.sh.petking.review.controller;

import com.sh.petking.review.model.entity.ReviewComment;
import com.sh.petking.review.model.service.ReviewCommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/review/reviewCommentDelete")
public class ReviewCommentDeleteController extends HttpServlet {
    private ReviewCommentService reviewCommentService = new ReviewCommentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Long reviewId = Long.parseLong(req.getParameter("reviewId"));

        int result = reviewCommentService.deleteComment(id);
        System.out.println(result);

        resp.sendRedirect(req.getContextPath() + "/review/reviewDetail?id=" + reviewId);
    }
}