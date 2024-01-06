package com.sh.petking.wish.controller;

import com.sh.petking.user.model.entity.User;
import com.sh.petking.wish.model.entity.Wish;
import com.sh.petking.wish.model.service.WishService;
import com.sh.petking.wish.model.vo.WishVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/userWishList")
public class UserWishListController extends HttpServlet {
    private WishService wishService = new WishService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 id 가져오기
        HttpSession session = req.getSession();
        User loginUser =(User) session.getAttribute("loginUser");
        List<WishVo> wishes = wishService.findByUserId(loginUser.getId());
        session.setAttribute("wishes", wishes);

        req.getRequestDispatcher("/WEB-INF/views/user/userWishList.jsp").forward(req, resp);
    }
}