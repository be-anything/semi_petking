package com.sh.petking.pet.controller;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.service.PetService;
import com.sh.petking.user.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pet/petInsert")
public class PetInsertController extends HttpServlet {
    private PetService petService = new PetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        // 사용자 입력값 가져오기
        String id = req.getParameter("loginUser");
        String petName = req.getParameter("petName");
        int petAge = Integer.parseInt(req.getParameter("petAge"));
        String petGender = req.getParameter("petGender");
        String neutered = req.getParameter("neutered");

        Pet pet = new Pet(loginUser.getId(), petName, petAge, petGender, neutered, null);
        System.out.println(pet);

        // 업무로직
        int result = petService.insertPet(pet);
        req.getSession().setAttribute("msg", "반려동물정보를 입력완료했습니다");

        // view단
        resp.sendRedirect(req.getContextPath() + "/user/userDetail");

    }
}
