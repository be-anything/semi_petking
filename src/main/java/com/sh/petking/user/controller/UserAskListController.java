package com.sh.petking.user.controller;

import com.sh.petking.ask.model.entity.Ask;
import com.sh.petking.ask.model.service.AskService;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/userAskList")
public class UserAskListController extends HttpServlet {

    AskService askService = new AskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("askListController - doGet");
        String userId = req.getParameter("userId");

        //페이징 처리 없이 일단 selectAll -> page 처리
        int page = 1;
        int limit = 5;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        param.put("page", page);
        param.put("limit", limit);
        param.put("userId", userId);

        // 페이지바
        int totalCount = askService.getTotalUserAsk(param);
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI() + "?userId=" + userId;

        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

//        List<Ask> ask = askService.findAll();
        List<Ask> ask = askService.findByUserId(param);
        System.out.println("ASk : "+ask);
        req.setAttribute("asks",ask);

        req.getRequestDispatcher("/WEB-INF/views/user/userAskList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("askListController - doPost");
    }
}