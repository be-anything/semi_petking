package com.sh.petking.ask.controller;

import com.sh.petking.ask.model.service.AskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ask/askCreate")
public class AskCreateContoller extends HttpServlet {
    private AskService askService = new AskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //작성 jsp page로 이동
        req.getRequestDispatcher("/WEB-INF/views/ask/askCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("askCreateController - doPost!");
        //name값으로 가져오는 값들.
        String userId = req.getParameter("userId");
        String askTitle = req.getParameter("askTitle");
        String askContent = req.getParameter("askContent");
        System.out.println("문의자:"+userId);
        System.out.println("문의 제목:"+askTitle);
        System.out.println("문의 내용:"+askContent);

    }
}