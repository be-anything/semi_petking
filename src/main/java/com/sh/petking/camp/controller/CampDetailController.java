package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
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

        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campDetail.jsp").forward(req, resp);
    }
}