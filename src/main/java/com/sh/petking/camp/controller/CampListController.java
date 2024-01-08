package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.entity.CampTag;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.common.PetkingUtils;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.wish.model.entity.Wish;
import com.sh.petking.wish.model.service.WishService;
import com.sh.petking.wish.model.vo.WishVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.sh.petking.common.PetkingUtils.getPagebar;

@WebServlet("/camp/campList")
public class CampListController extends HttpServlet {
    private CampService campService = new CampService();
    private WishService wishService = new WishService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리 - X
        // 2. 업무로직
        int page = 1;
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore){};
        Map<String, Object> param = new HashMap<>();

        // 검색어 입력
        String searchType = req.getParameter("search-type");
        String searchKeyword = req.getParameter("search-keyword");
        param.put("searchType", searchType);
        param.put("searchKeyword", searchKeyword);

        param.put("page", page);
        param.put("limit", limit);
        
        // 태그 선택 tagId1=1&tagId2=2&tagId3=3
        // 태그 id가 있는 애들만 목록에 출력되어야 한다.
        List<CampTag> tags = campService.findAllCampTag();
        req.setAttribute("tags", tags);


//        List<Long> tagIds = new ArrayList<>();
        List<String> tagNames = new ArrayList<>();
        for(int i = 1; i < tags.size(); i++){
            String tagName = req.getParameter("tagName");
            System.out.println("1. 입력받은 tagName" + tagName);
            if(tagName != null){
                // 입력된 태그 찾기
                System.out.println("2. 입력받은 tagName 저장");
                tagNames.add(tagName);
                param.put("tagName", tagName);
            }
        }

        List<CampVo> camps = new ArrayList<>();
        // 값 가져오기 - campList
        camps = campService.findAllCampWithTag(param);
        // 페이지바
        int totalCount = campService.getTotalCampWithTagCount(param);
        req.setAttribute("totalCount", totalCount);
        System.out.println("4. tagNames param조건에 해당하는 갯수 구하기" + totalCount);
        String url = req.getRequestURI();

        // 검색어 입력시의 주소 처리
        if(searchType != null && searchKeyword != null){
            url += "?search-type=" + searchType + "&search-keyword=" + searchKeyword;
            url += "&tagName=" + param.get("tagName");
        }
        else {
            url += "?tagName=" + param.get("tagName");
        }
        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        // 태그 처리
        for(CampVo camp : camps) {
            System.out.println("캠핑장 태그랑 정보" + camp);
            if(camp.getTagNames() != null){
                camp.setTagNameList(Arrays.asList(camp.getTagNames().split(",")));
            }
        }
        req.setAttribute("camps", camps);
        req.setAttribute("tagName", param.get("tagName"));

        // wishList - 사용자 id로 찾기
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        System.out.println(loginUser);
        if(loginUser != null) {
            List<Wish> wishes = wishService._findByUserId(loginUser.getId());
            for (Wish wish: wishes) {
                for(CampVo camp: camps){
                    System.out.println(camp.getId());
                    if(camp.getId() == wish.getCampId()){
                        camp.setWish(true);
                        break;
                    }
                    else {
                        camp.setWish(false);
                    }
                }
            }
            req.setAttribute("wishes", wishes);
        }

        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campList.jsp").forward(req, resp);
    }
}