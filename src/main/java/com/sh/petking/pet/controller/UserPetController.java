package com.sh.petking.pet.controller;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.service.PetService;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/userPet")
public class UserPetController extends HttpServlet {
    private PetService petService = new PetService();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User loginUser = (User) req.getSession().getAttribute("loginUser");
//        req.getRequestDispatcher("/WEB-INF/views/user/userDetail.jsp").forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        String petName = req.getParameter("petName");
        int petAge = Integer.parseInt(req.getParameter("petAge"));
        String petGender = req.getParameter("petGender");
        String neutered = req.getParameter("neutered");

        Pet pet = new Pet(loginUser.getId(), petName, petAge, petGender, neutered, null);
        System.out.println(pet);

        int result = petService.insertPet(pet);
    }
}
