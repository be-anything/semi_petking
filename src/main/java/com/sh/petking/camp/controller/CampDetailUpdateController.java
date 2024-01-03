package com.sh.petking.camp.controller;

import com.google.gson.Gson;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.entity.CampAttach;
import com.sh.petking.camp.model.entity.CampWithService;
import com.sh.petking.camp.model.entity.CampWithTag;
import com.sh.petking.camp.model.service.CampService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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

@WebServlet("/camp/campDetailUpdate")
public class CampDetailUpdateController extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 셋팅
        File repository = new File("C:\\Users\\min_j\\Dropbox\\Workspaces\\semi_petking\\target\\semi_petking\\upload\\camp");
        int sizeThreshold = 10 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        System.out.println("1");

        // Camp Attach - insert / delete
//        CampAttach campAttach = new CampAttach();
        // Camp with Tags - insert / delete
        List<CampWithTag> campWithTags = new ArrayList<>();
        // Camp with Services - insert / delete
        List<CampWithService> campWithServices = new ArrayList<>();

        List<Camp> camps = new ArrayList<>();
        camps.add(new Camp());

        try {
            // 1. 사용자 입력값 처리
            Map<String, List<FileItem>> fileItemMap = servletFileUpload.parseParameterMap(req);
            camps.get(0).setId(Long.parseLong(fileItemMap.get("campId").get(0).getString("utf-8")));

            // campWithTag 처리
            for (int i = 0; i < fileItemMap.get("tagId").size(); i++) {
                Long tagId = Long.parseLong(fileItemMap.get("tagId").get(i).getString("utf-8"));
                campWithTags.add(new CampWithTag((long) 0, tagId, camps.get(0).getId()));
                System.out.println("213213");
            }
            // campWithService 처리
            for (int i = 0; i < fileItemMap.get("serviceId").size(); i++) {
                Long serviceId = Long.parseLong(fileItemMap.get("serviceId").get(i).getString("utf-8"));
                campWithServices.add(new CampWithService((long) 0, serviceId, camps.get(0).getId()));
                System.out.println("213213");
            }

            System.out.println(campWithTags);
            System.out.println(campWithServices);

            // campAttach 처리
//            List<FileItem> campImgAttachItem = fileItemMap.get("campAttach");
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }

        System.out.println("3");

        Map<String, List<Object>> param = new HashMap<>();
        param.put("campId", Collections.singletonList(camps));
        param.put("campWithTags", Collections.singletonList(campWithTags));
        param.put("campWithServices", Collections.singletonList(campWithServices));
        System.out.println(param);
        // 2. 업무로직
        int result = campService.updateCampDetail(param);

        // 사용자 메시지
        Map<String, Object> resultMap = Map.of("msg", "캠핑장 정보가 수정되었습니다.");

        // 3. redirect
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());
    }
}