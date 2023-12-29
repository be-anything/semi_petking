package com.sh.petking.review.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.service.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/review/reviewList")
public class ReviewListController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int limit = 10;
        try{
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();
        param.put("page", page);
        param.put("limit", limit);

        List<Review> reviews = reviewService.findAll(param);
        req.setAttribute("reviews", reviews);

        int totalCount = reviewService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        req.getRequestDispatcher("/WEB-INF/views/review/reviewList.jsp").forward(req, resp);

    }
}