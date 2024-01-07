package com.sh.petking.user.controller;

import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.user.model.entity.Point;
import com.sh.petking.user.model.service.UserService;
import com.sh.petking.user.model.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/grade")
public class UserGradeController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserVo userVo = userService.findGradeId(id);
        req.setAttribute("userVo", userVo);

        List<Point> point = userService.findPointAll(id);
        System.out.println(point);
        req.setAttribute("point",point);

        req.getRequestDispatcher("/WEB-INF/views/user/userGrade.jsp").forward(req, resp);
    }
}