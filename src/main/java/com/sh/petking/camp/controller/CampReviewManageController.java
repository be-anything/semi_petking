package com.sh.petking.camp.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/camp/campReviewList")
public class CampReviewManageController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long campId = Long.parseLong(req.getParameter("campId"));


        // 캠핑장 리뷰 찾아오기
        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        param.put("page", page);
        param.put("limit", limit);
        param.put("campId", campId);
        // 페이지바
        int totalCount = reviewService.getTotalCampReview(param);
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI() + "?campId=" + campId;
        List<ReviewVo> reviews = reviewService.findByCampId(param);
        System.out.println(reviews);
        req.setAttribute("reviews", reviews);
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campReviewList.jsp").forward(req, resp);
    }
}