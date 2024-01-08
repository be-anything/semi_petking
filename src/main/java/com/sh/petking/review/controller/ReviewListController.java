package com.sh.petking.review.controller;

import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.common.PetkingUtils;
import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.wish.model.entity.Wish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/review/reviewList")
public class ReviewListController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리 - X
        // 2. 업무로직
        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        param.put("page", page);
        param.put("limit", limit);

        // 값 가져오기 - rowbound생성
        List<ReviewVo> reviews = reviewService.findPhotoReview(param);

        for (ReviewVo review: reviews) {
            if(review.getReviewTag() != null){
                // csvTag 핸들링
                List<String> tags = Arrays.asList(review.getReviewTag().split(","));
                review.setTags(tags);
            }
        }
        req.setAttribute("reviews", reviews);

        // 페이지바
        int totalCount = reviewService.getTotalCount();
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI();

        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);
        System.out.println(totalCount);

        req.getRequestDispatcher("/WEB-INF/views/review/reviewList.jsp").forward(req, resp);
    }
}