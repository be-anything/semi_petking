package com.sh.petking.wish.controller;

import com.sh.petking.wish.model.entity.Wish;
import com.sh.petking.wish.model.service.WishService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/wish/wishInsert")
public class WishInsertController extends HttpServlet {
    private WishService wishService = new WishService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. campId, userId 잘 가져오는지 확인하기
        Long campId = Long.parseLong(req.getParameter("campId"));
        String userId = req.getParameter("userId");
        System.out.println(campId + userId);

        // 2. 업무로직
        Wish wish = new Wish(0, userId, campId, null);
        int result = wishService.insertWish(wish);
        System.out.println(result);
    }
}