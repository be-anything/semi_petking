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
    private UserService userService = new UserService();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User loginUser = (User) session.getAttribute("loginUser");
//        System.out.println("loginUser : " + loginUser);
//
//        User user = userService.findById(loginUser.getId());
//        req.setAttribute("user", user);
//        req.getRequestDispatcher("/WEB-INF/views/pet/petUpdate.jsp").forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 입력값 가져오기
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String userId = loginUser.getId();
        String newPetName = req.getParameter("newPetName");
        String newPetAge1 = req.getParameter("newPetAge");
        int newPetAge = 0;
            if (newPetAge1 != null && newPetAge1.isEmpty()) {
                newPetAge = Integer.parseInt(newPetAge1);
            }
        String newPetGender = req.getParameter("newPetGender");
        String newNeutered = req.getParameter("newNeutered");

        Pet pet = new Pet(userId, newPetName, newPetAge, newPetGender, newNeutered, null);
        System.out.println("입력받은pet값 : " + pet);

        // 업무로직
        int petUpdated = petService.updatePet(pet);

        // 메세지
        if (petUpdated > 0) {
            // 업데이트 성공 메시지 설정
            req.getSession().setAttribute("msg", "반려동물 정보수정 성공");
        } else {
            // 업데이트 실패 메시지 설정
            req.getSession().setAttribute("error", "반려동물 정보수정 실패");
        }

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/user/userDetail");
    }
}
