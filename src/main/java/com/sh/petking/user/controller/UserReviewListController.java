package com.sh.petking.user.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;
import com.sh.petking.user.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/userReviewList")
public class UserReviewListController extends HttpServlet {
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 해당 사용자 리뷰만 찾아오기
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        param.put("page", page);
        param.put("limit", limit);
        param.put("userId", loginUser.getId());

        // 페이지바
        int totalCount = reviewService.getTotalUserReview(param);
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI() + "?userId=" + loginUser.getId();

        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        List<ReviewVo> reviews = reviewService.findByUserId(param);
        System.out.println(reviews);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/WEB-INF/views/user/userReviewList.jsp").forward(req,resp);
    }
}