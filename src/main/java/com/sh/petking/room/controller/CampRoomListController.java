package com.sh.petking.room.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 객실 리스트
 * 기존 servlet 역할
 *
 *  0102 : page bar 추가 한 페이지에 5개만 노출
 *  0110 : page bar 추가 작업하기
 */
@WebServlet("/camp/campRoomList")
public class CampRoomListController extends HttpServlet {
    RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("0110 페이지바 추가 : CampRoomListController - doGet");
        //1.사용자 입력값 가져오기
        Long campId = Long.parseLong(req.getParameter("id"));
        System.out.println("나의 캠핑존(객실)목록을 불러옵니다. 캠핑 id는 "+campId+"입니다.");
        int page = 1;
        int limit = 2;
        try
        {
            page  = Integer.parseInt(req.getParameter("page"));
        }
        catch(NumberFormatException ignore) {}
        //변경불가 immutable객체 , 요소로(key,value) null값 줄 수 없음.
        Map<String,Object> param = Map.of("page", page, "limit", limit);
        System.out.println("param:::::::::::"+param);

        //2.업무로직 param+ campId로 조회
        List<RoomVo> rooms = roomService.findRoomListByCampId(campId);
        //join으로 인한 paging 문제
        //전체조회 쿼리후에 리턴한 List<RoomVo> 를 직접 offset기준으로 limit개만 필터링하기 (데이터가 많거나 많아질 경우는 추천하지 않음)
        List<RoomVo> newRooms = null;
        //findAll로 가져온 값이 null이 아닌 경우 즉, 값이 있는 경우
        if (rooms != null) { 
            System.out.println("rooms not null - streaming");
            newRooms=
                    rooms.stream()
                            .skip((page-1)*limit) //스킵할 갯수
                            .limit(limit) // 한 페이지당 보여줄 갯수
                            .collect(Collectors.toList());//다시 list형태로 되돌림
        }
        req.setAttribute("rooms",newRooms);

        //3.페이지 바 영역
        int totalCount = roomService.getTotalCount(campId);
        String url = req.getRequestURI()+"?id="+campId;
        System.out.println("현재 url:"+url);
        String pagebar = PetkingUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);
        System.out.println("게시물 총 갯수:"+totalCount);

        //req.setAttribute("rooms", rooms);
        req.setAttribute("roomCampId", campId);

        req.getRequestDispatcher("/WEB-INF/views/room/campRoomList.jsp").forward(req,resp);
    }
}