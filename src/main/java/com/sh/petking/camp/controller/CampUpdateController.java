package com.sh.petking.camp.controller;

import com.google.gson.Gson;
import com.sh.petking.camp.model.entity.CampTag;
import com.sh.petking.camp.model.entity._CampService;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/camp/campUpdate")
public class CampUpdateController extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        Long id = Long.parseLong(req.getParameter("id"));
        CampVo camp = campService.findById(id);

        // 2. 업무로직
        req.setAttribute("camp", camp);
        // - 부가서비스 종류 가져오기
        List<_CampService> campServices = campService.findAllCampService();
        req.setAttribute("campServices", campServices);
        System.out.println(campServices);
        // - 태그 종류 가져오기
        List<CampTag> campTags = campService.findAllCampTag();
        req.setAttribute("campTags", campTags);
        System.out.println(campTags);

        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campUpdate.jsp").forward(req, resp);
    }

    /**
     * 캠핑장 사진 테스트를 위해서 넣었습니다 ~!
     * 제 pc 로컬 주소라서 다른 피씨에서 테스트하면 아마 오류날거에요...
     *
     * *** 기본정보 - 메인이미지, 캠핑장 소개, 캠핑장 이름, 캠핑장 전화번호, 캠핑장 주소 수정
     * *** 부가정보 - 부가 서비스, 캠핑장 사진, 캠핑장 태그 -> campDetailUpdate 로 생성 예정
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. 셋팅
        File repository = new File("C:\\Users\\cksgm\\Dropbox\\workspaces_ming\\web_server_workspace\\semi_petking\\target\\semi_petking\\upload\\camp");
        int sizeThreshold = 10 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        // camp테이블 update
        CampVo camp = new CampVo();
        
        try {
            // 1. 사용자 입력값 처리 및 파일 업로드
            // 비동기적 처리
            Map<String, List<FileItem>> fileItemMap = servletFileUpload.parseParameterMap(req);
            Long id = Long.parseLong(fileItemMap.get("id").get(0).getString("utf-8"));
            String campIntro = fileItemMap.get("campIntro").get(0).getString("utf-8");
            String campName = fileItemMap.get("campName").get(0).getString("utf-8");
            String campPhone = fileItemMap.get("campPhone").get(0).getString("utf-8");
            String campAddr = fileItemMap.get("campAddr").get(0).getString("utf-8");
            camp.setId(id);
            camp.setCampName(campName);
            camp.setCampAddr(campAddr);
            camp.setCampPhone(campPhone);
            camp.setCampIntro(campIntro);

            FileItem mainImgFileItem = fileItemMap.get("campImg").get(0);
            if(mainImgFileItem.getSize() > 0){
                String originalImgName = mainImgFileItem.getName();
                camp.setCampOriginalImg(originalImgName);

                int dotIndex = originalImgName.lastIndexOf(".");
                String ext = dotIndex > -1 ? originalImgName.substring(dotIndex) : "";

                UUID uuid = UUID.randomUUID();
                String renamedImgName = uuid + ext;
                camp.setCampRenamedImg(renamedImgName);
                mainImgFileItem.write(new File(repository, renamedImgName));
            }
            else {
                camp.setCampOriginalImg(campService.findById(camp.getId()).getCampOriginalImg());
                camp.setCampRenamedImg(campService.findById(camp.getId()).getCampRenamedImg());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println(camp);

        // 2. 업무 로직
        int result = campService.updateCamp(camp);

        // 사용자 메시지
        Map<String, Object> resultMap = Map.of("msg", "캠핑장 정보가 수정되었습니다.");

        // 3. redirect
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(resultMap, resp.getWriter());
    }
}