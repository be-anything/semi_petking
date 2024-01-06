package com.sh.petking.camp.controller;

import com.sh.petking.camp.model.entity.Camp;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        // 값 가져오기 - campList
        List<CampVo> camps = campService.findAll(param);
        req.setAttribute("camps", camps);
        System.out.println(camps);

        // wishList - 사용자 id로 찾기
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        System.out.println(loginUser);
        if(loginUser != null) {
            List<WishVo> wishes = wishService.findByUserId(loginUser.getId());
            if(wishes.size() > 0) {
                req.setAttribute("wishes", wishes);
                System.out.println(wishes);
            }
        }

        // 페이지바
        int totalCount = campService.getTotalCount(param);
        req.setAttribute("totalCount", totalCount);
        String url = req.getRequestURI();

        // 검색어 입력시의 주소 처리
        if(searchType != null && searchKeyword != null){
            url += "?search-type=" + searchType + "&search-keyword=" + searchKeyword;
        }


        String pagebar = PetkingUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);
        System.out.println(totalCount);

        // 3. 포워딩
        req.getRequestDispatcher("/WEB-INF/views/camp/campList.jsp").forward(req, resp);
    }
}