package com.sh.petking.review.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/review/reviewList")
public class ReviewListController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}