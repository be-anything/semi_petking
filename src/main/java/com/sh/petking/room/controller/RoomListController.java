package com.sh.petking.room.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;
import com.sh.petking.user.model.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 객실 리스트
 * 기존 servlet 역할
 *  0102 : page bar 추가 한 페이지에 5개만 노출
 */
@WebServlet("/room/roomList")
public class RoomListController extends HttpServlet {
    RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력값 가져오기
        //기본적으로 page에 1값을 주는 이유 페이지단 처리[아래에]하다가 null이 발생할 수 있으므로.
        int page = 1;
        int limit = 4;
        try
        {
            //값이 있으면 정수 변환, 값이 없으면 (null)이라 포맷에러 발생
            page  = Integer.parseInt(req.getParameter("page"));
        }
        catch(NumberFormatException ignore) {}
        //변경불가 immutable객체 , 요소로(key,value) null값 줄 수 없음.
        Map<String,Object> param = Map.of("page", page, "limit", limit);
        System.out.println(param);

        //2.업무로직
        //1)content영역 : 전체 조회 쿼리 + RowBounds | Top-n 분석 쿼리
        List<RoomVo> rooms = roomService.findAll(param);
        req.setAttribute("rooms",rooms);

        //2)pageBar 영역
        int totalCount = roomService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);
        System.out.println("게시물 총 갯수:"+totalCount);
        //System.out.println(boards);
        req.getRequestDispatcher("/WEB-INF/views/room/roomList.jsp").forward(req,resp);
    }
}
