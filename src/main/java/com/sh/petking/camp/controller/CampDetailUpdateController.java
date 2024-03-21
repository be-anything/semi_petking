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
        File repository = new File("/var/webapps/upload/");
        int sizeThreshold = 10 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        // Camp Attach - insert / delete
        List<CampAttach> campAttaches = new ArrayList<>();
        // Camp with Tags - insert / delete
        List<CampWithTag> campWithTags = new ArrayList<>();
        // Camp with Services - insert / delete
        List<CampWithService> campWithServices = new ArrayList<>();
        List<FileItem> campImgFileItems = new ArrayList<>();

        Long campId = 0L;
        try {
            // 1. 사용자 입력값 처리
            Map<String, List<FileItem>> fileItemMap = servletFileUpload.parseParameterMap(req);
            campId= Long.parseLong(fileItemMap.get("campId").get(0).getString("utf-8"));


            if(fileItemMap.get("tagId") != null && !fileItemMap.get("tagId").isEmpty()) {
                // campWithTag 처리
                for (int i = 0; i < fileItemMap.get("tagId").size(); i++) {
                    Long tagId = Long.parseLong(fileItemMap.get("tagId").get(i).getString("utf-8"));
                    campWithTags.add(new CampWithTag((long) 0, tagId, campId));
                }
            }

            if(fileItemMap.get("serviceId") != null && !fileItemMap.get("serviceId").isEmpty()) {
                // campWithService 처리
                for (int i = 0; i < fileItemMap.get("serviceId").size(); i++) {
                    Long serviceId = Long.parseLong(fileItemMap.get("serviceId").get(i).getString("utf-8"));
                    campWithServices.add(new CampWithService((long) 0, serviceId, campId));
                }
            }

            if(fileItemMap.get("campDetailImg") != null && !fileItemMap.get("campDetailImg").isEmpty()) {
                campImgFileItems = fileItemMap.get("campDetailImg");
                // campAttach 처리
                System.out.println(campImgFileItems);
                System.out.println(campImgFileItems.size());
                for(int i = 0; i < campImgFileItems.size(); i++){
                    System.out.println(campImgFileItems.get(i));
                    String originalImgName = campImgFileItems.get(i).getName().toString();

                    if(originalImgName != null && !("".equals(originalImgName))) {
                        int dotIndex = originalImgName.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalImgName.substring(dotIndex) : "";

                        UUID uuid = UUID.randomUUID();
                        String renamedImgName = uuid + ext;

                        CampAttach campAttach = new CampAttach();
                        campAttach.setCampAttachOriginalName(originalImgName);
                        campAttach.setCampAttachRenamedName(renamedImgName);
                        campAttach.setCampId(campId);

                        campAttaches.add(campAttach);
                        campImgFileItems.get(i).write(new File(repository, renamedImgName));
                    }
                }
            }


//            List<FileItem> campImgAttachItem = fileItemMap.get("campAttach");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Map<String, Object> param = new HashMap<>();
        param.put("campId", campId);
        param.put("campWithTags", campWithTags);
        param.put("campWithServices", campWithServices);
        param.put("campAttaches", campAttaches);

        // 2. 업무로직
        int result = campService.updateCampDetail(param);
        // 사용자 메시지
        Map<String, Object> resultMap = Map.of("msg", "캠핑장 정보가 수정되었습니다.");
        // 3. redirect
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());
    }
}