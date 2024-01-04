package com.sh.petking.ask.controller;

import com.sh.petking.ask.model.entity.Ask;
import com.sh.petking.ask.model.service.AskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ask/askList")
public class AskListController extends HttpServlet {

    AskService askService = new AskService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("askListController - doGet");

        //페이징 처리 없이 일단 selectAll
        List<Ask> ask = askService.findAll();
        System.out.println("ASk : "+ask);
        req.setAttribute("asks",ask);




        req.getRequestDispatcher("/WEB-INF/views/ask/askList.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("askListController - doPost");
    }
}