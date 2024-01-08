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

@WebServlet("/review/reviewCommentCreate")
public class ReviewCommentCreateController extends HttpServlet {
    private ReviewCommentService reviewCommentService = new ReviewCommentService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String content = req.getParameter("content");
        Long reviewId = Long.parseLong(req.getParameter("reviewId"));
        LocalDateTime regDate = LocalDateTime.now();

        ReviewComment reviewComment = new ReviewComment(0L, userId, reviewId, content, regDate);
        int result = reviewCommentService.insertComment(reviewComment);
        System.out.println(reviewComment);

        resp.sendRedirect(req.getContextPath() + "/review/reviewDetail?id=" + reviewId);
    }
}