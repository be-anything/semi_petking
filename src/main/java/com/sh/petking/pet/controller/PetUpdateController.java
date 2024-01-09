package com.sh.petking.pet.controller;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.pet.model.service.PetService;
import com.sh.petking.pet.model.vo.PetVo;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pet/petUpdate")
public class PetUpdateController extends HttpServlet {
    private PetService petService = new PetService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 입력값 가져오기
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        String petName = req.getParameter("newPetName");
        String petAge = req.getParameter("newPetAge");
        int petAge1 = 0;
            if (petAge != null) {
                petAge1 = Integer.parseInt(petAge);
            }
        String petGender = req.getParameter("newGender");
        String neutered = req.getParameter("newNeutered");
        Pet pet = new Pet(userId, petName, petAge1, petGender, neutered, null);

        // 업무로직
        int petUpdated = petService.updatePet(pet);

        // 메세지
        if (petUpdated > 0) {
            // 업데이트 성공 메시지 설정
            req.getSession().setAttribute("msg", "반려동물 정보수정 성공했습니다.");
        } else {
            // 업데이트 실패 메시지 설정
            req.getSession().setAttribute("error", "반려동물 정보수정 실패했습니다.");
        }

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/user/userDetail");
    }
}
