package com.sh.petking.wish.controller;

import com.sh.petking.wish.model.entity.Wish;
import com.sh.petking.wish.model.service.WishService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wish/wishDelete")
public class WishDeleteController extends HttpServlet {
    private WishService wishService = new WishService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값
        Long campId = Long.valueOf(req.getParameter("campId"));
        String userId = req.getParameter("userId");
        Wish wish = new Wish(0, userId, campId, null);

        int result = wishService.deleteWish(wish);
        System.out.println(result);
    }
}