package com.sh.petking.review.controller;

import com.google.gson.Gson;
import com.sh.petking.review.model.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/review/reviewAttachDelete")
public class ReviewDeleteController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        Long attachId = Long.parseLong(req.getParameter("attachId"));
        Long reviewId = Long.parseLong(req.getParameter("reviewId"));
        System.out.println(attachId);
        System.out.println(reviewId);

        Map<String, Object> param = new HashMap<>();
        param.put("attachId", attachId);
        param.put("reviewId", reviewId);

        // 업무로직
        int result = reviewService.deleteReviewAttach(param);

        // 응답
        resp.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = Map.of("result", result);
        new Gson().toJson(map, resp.getWriter());

    }
}