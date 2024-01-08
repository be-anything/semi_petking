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

@WebServlet("/pet/petDelete")
public class PetDeleteController extends HttpServlet {
    private PetService petService = new PetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 입력값처리
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String userId = loginUser.getId();

        // 업무로직
        int result = petService.deletePet(userId);
        req.getSession().setAttribute("msg", "반려동물 정보를 삭제했습니다.");

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/user/userDetail");

    }
}
