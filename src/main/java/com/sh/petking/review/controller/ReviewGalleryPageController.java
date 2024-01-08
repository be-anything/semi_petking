package com.sh.petking.review.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.petking.common.LocalDateTypeAdapter;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;
import com.sh.petking.common.LocalDateTimeTypeAdapter;
import com.sh.petking.review.model.vo._ReviewVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet("/review/reviewPage")
public class ReviewGalleryPageController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 퍼리
        int page = Integer.parseInt(req.getParameter("page"));
        final int limit = 5;
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        System.out.println(param);

        // 2. 업무로직
        List<ReviewVo> reviews = reviewService.findAll(param);
//        List<ReviewVo> photoReviews = reviewService.findPhotoReview();
        System.out.println(reviews);
//        System.out.println(photoReviews);

        // 3. json 응답처리
        resp.setContentType("application/json; charset=utf-8");
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
//        gson.toJson(photoReviews, resp.getWriter());

    }
}