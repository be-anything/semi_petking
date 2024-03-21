package com.sh.petking.user.controller;

import com.google.gson.Gson;
import com.sh.petking.camp.model.entity.CampAttach;
import com.sh.petking.camp.model.entity.CampWithService;
import com.sh.petking.camp.model.entity.CampWithTag;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet("/user/userProfileUpdate")
public class UserProfileUpdateController extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 셋팅
        File repository = new File("\\var\\webapps\\upload");
        int sizeThreshold = 10 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        // Profile
        User user = new User();

        try {
            // 1. 사용자 입력값 처리
            Map<String, List<FileItem>> fileItemMap = servletFileUpload.parseParameterMap(req);
            user.setId(fileItemMap.get("id").get(0).getString("utf-8"));

            FileItem profileImg = fileItemMap.get("profileImg").get(0);

            if(profileImg.getSize() > 0){
                String originalImgName = profileImg.getName();
                user.setOriginProfileName(originalImgName);

                int dotIndex = originalImgName.lastIndexOf(".");
                String ext = dotIndex > -1 ? originalImgName.substring(dotIndex) : "";

                UUID uuid = UUID.randomUUID();
                String renamedImgName = uuid + ext;
                user.setRenamedProfileName(renamedImgName);
                profileImg.write(new File(repository, renamedImgName));
            }
            else {
                user.setOriginProfileName(userService.findById(user.getId()).getOriginProfileName());
                user.setRenamedProfileName(userService.findById(user.getId()).getRenamedProfileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 2. 업무 로직
        int result = userService.updateUserProfile(user);

        if(result > 0){
            User user2 = userService.findById(user.getId());
            req.getSession().setAttribute("loginUser", user2);
        }

        // 사용자 메시지
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("renamedProfileName", user.getRenamedProfileName());
        resultMap.put("msg", "프로필 사진이 수정되었습니다.");

        // 3. redirect
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());
    }
}