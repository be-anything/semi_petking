package com.sh.petking.user.controller;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.service.PetService;
import com.sh.petking.pet.model.vo.PetVo;
import com.sh.petking.user.model.entity.Point;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/userDetail")
public class UserDetailController extends HttpServlet {
    private UserService userService = new UserService();
    private PetService petService = new PetService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        Pet pet = petService.findByPet(loginUser.getId());

        req.setAttribute("pet", pet);
        System.out.println(pet);

        List<Point> points = userService.findPointAll(loginUser.getId());
        System.out.println(points);
        req.setAttribute("points",points);
        Long pointSum = 0L;
        for (Point point : points) {
            pointSum = pointSum + point.getPoint();
        }
        req.getSession().setAttribute("pointSum", pointSum);

        req.getRequestDispatcher("/WEB-INF/views/user/userDetail.jsp").forward(req, resp);
    }
}