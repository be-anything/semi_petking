package com.sh.petking.club.controller;

import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.service.ClubService;
import com.sh.petking.common.Role;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/club/clubRequest")
public class ClubRequestController extends HttpServlet {
    private BoardService boardService = new BoardService();
    private UserService userService = new UserService();
    private ClubService clubService = new ClubService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // board id
        // userId
        // users 테이블 club_id 업데이트
        // club_users 인서트 U
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        BoardVo board = boardService.findById(Long.parseLong(req.getParameter("id")));
        User clubAdmin = userService.findById(board.getUserId());

        loginUser = userService.findById(loginUser.getId());
        ClubUsers clubUsers = new ClubUsers(clubAdmin.getClubId(), loginUser.getId(), 1, null, Role.U);
        System.out.println("동아리장의 클럽 아이디 : " + clubUsers.getClubId());
        loginUser.setClubId(clubUsers.getClubId());

        Map<String, Object> param = new HashMap<>();
        param.put("user", loginUser);
        param.put("clubUsers", clubUsers);

        int result = clubService.insertClubUsersRequest(param);

        // users 테이블이 업데이트 되었으므로 session의 loginUser 정보 업데이트
        User afterLoginUser = userService.findById(loginUser.getId());
        req.getSession().setAttribute("loginUser", afterLoginUser);
        resp.sendRedirect(req.getContextPath() + "/club/clubDetail?id=" + clubAdmin.getClubId());
    }
}