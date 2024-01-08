package com.sh.petking.user.controller;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.service.PetService;
import com.sh.petking.pet.model.vo.PetVo;
import com.sh.petking.user.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/userDetail")
public class UserDetailController extends HttpServlet {
    private PetService petService = new PetService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        Pet pet = petService.findByPet(loginUser.getId());

        req.setAttribute("pet", pet);
        System.out.println(pet);

        req.getRequestDispatcher("/WEB-INF/views/user/userDetail.jsp").forward(req, resp);
    }
}