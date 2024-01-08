package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.review.model.service.ReviewService;
import com.sh.petking.review.model.vo.ReviewVo;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.wish.model.entity.Wish;
import com.sh.petking.wish.model.service.WishService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/camp/campDetail")
public class CampDetailController extends HttpServlet {
    private CampService campService = new CampService();
    private WishService wishService = new WishService();
    private ReviewService reviewService = new ReviewService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        Long id = Long.parseLong(req.getParameter("id"));
        CampVo camp = campService.findById(id);
        // 2. 업무로직
        req.setAttribute("camp", camp);

        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if(loginUser != null) {
            Map<String, Object> param = new HashMap<>();
            param.put("userId", loginUser.getId());
            param.put("campId", id);
            List<Wish> wishes = wishService.findByUserIdCampId(param);

            if(wishes.size() > 0) {
                req.setAttribute("wishes", wishes);
                System.out.println(wishes);
            }
        }

        // 캠핑장 리뷰 찾아오기
        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        param.put("page", page);
        param.put("limit", limit);
        param.put("campId", id);
        // 페이지바
        int totalCount = reviewService.getTotalCampReview(param);
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI() + "?id=" + id;
        List<ReviewVo> reviews = reviewService.findByCampId(param);
        System.out.println(reviews);
        req.setAttribute("reviews", reviews);


        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campDetail.jsp").forward(req, resp);
    }
}