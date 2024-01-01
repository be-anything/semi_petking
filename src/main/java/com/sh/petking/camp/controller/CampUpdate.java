package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
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
import java.util.List;
import java.util.UUID;

@WebServlet("/camp/campUpdate")
public class CampUpdate extends HttpServlet {
    private CampService campService = new CampService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        Long id = Long.parseLong(req.getParameter("id"));
        CampVo camp = campService.findById(id);
        // 2. 업무로직
        req.setAttribute("camp", camp);
        System.out.println(camp);
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
        File repository = new File("C:\\Users\\min_j\\Dropbox\\Workspaces\\semi_petking\\src\\main\\webapp\\upload\\camp");
        int sizeThreshold = 10 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        // camp테이블 update
        CampVo camp = new CampVo();
        
        try {
            // 1. 사용자 입력값 처리 및 파일 업로드
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            for(FileItem item: fileItems) {
                String name = item.getFieldName();
                if(item.isFormField()){
                    // form안의 일반 field - id(findById), campIntro | campPhone | campName | campAddr
                    String value = item.getString("utf-8");
                    camp.setValue(name, value);
                }
                else {
                    // form안의 메인 이미지 업로드
                    if(item.getSize() > 0){
                        String originalImgName = item.getName();
                        System.out.println(originalImgName);
                        int dotIndex = originalImgName.lastIndexOf(".");
                        String ext = dotIndex > -1 ? originalImgName.substring(dotIndex) : "";

                        UUID uuid = UUID.randomUUID();
                        String renamedImgName = uuid + ext;

                        // 파일 저장
                        File upFile = new File(repository, renamedImgName);
                        item.write(upFile);
                        System.out.println(item);

                        camp.setCampOriginalImg(originalImgName);
                        camp.setCampRenamedImg(renamedImgName);
                    }
                    else {
                        camp.setCampOriginalImg(campService.findById(camp.getId()).getCampOriginalImg());
                        camp.setCampRenamedImg(campService.findById(camp.getId()).getCampRenamedImg());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(camp);

        // 2. 업무 로직
        int result = campService.updateCamp(camp);
        Camp camp2 = campService.findById(camp.getId());
        req.setAttribute("camp", camp2);
        if(result > 0) {
            req.getSession().setAttribute("msg", "캠핑장 정보수정이 완료되었습니다. 😀");
        }
        else {
            req.getSession().setAttribute("msg", "캠핑장 정보수정에 실패했습니다. 😀");
        }
        // 3. redirect
        resp.sendRedirect(req.getContextPath() + "/camp/campDetail?id=" + camp.getId());
    }
}