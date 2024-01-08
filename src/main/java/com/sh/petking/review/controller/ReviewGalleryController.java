package com.sh.petking.review.controller;

import com.sh.petking.review.model.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/review/reviewGallery")
public class ReviewGalleryController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값
        // 2. 업무로직
        int limit = 5;
        int totalCount = reviewService.getTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / limit);
        req.setAttribute("totalPage", totalPage);
        // 3. view단처리
        req.getRequestDispatcher("/WEB-INF/views/review/reviewGallery.jsp").forward(req, resp);

    }
}