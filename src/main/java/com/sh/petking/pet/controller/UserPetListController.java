package com.sh.petking.pet.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.pet.model.service.PetService;
import com.sh.petking.pet.model.vo.PetVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//@WebServlet("/user/userPet")
public class UserPetListController extends HttpServlet {
    private PetService petService = new PetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int limit = 5;

        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {}
            Map<String ,Object> param = Map.of("page", page, "limit", limit);

            List<PetVo> pets = petService.findAllPet(param);
            req.setAttribute("pets", pets);


            int totalCount = petService.getTotalCount();
            String url = req.getRequestURI();
            String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
            req.setAttribute("pagebar", pagebar);
            req.getRequestDispatcher("/WEB-INF/views/user/userDetail.jsp").forward(req, resp);
    }
}
