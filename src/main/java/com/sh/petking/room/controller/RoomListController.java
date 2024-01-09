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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        System.out.println("RoomListController - doGet");
        //1.사용자 입력값 가져오기
        //기본적으로 page에 1값을 주는 이유 페이지단 처리[아래에]하다가 null이 발생할 수 있으므로.
        int page = 1;
        int limit = 5;
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
        //join으로 인한 paging 문제
        //전체조회 쿼리후에 리턴한 List<RoomVo> 를 직접 offset기준으로 limit개만 필터링하기 (데이터가 많거나 많아질 경우는 추천하지 않음)
        List<RoomVo> newRooms = null;
        if (rooms != null) {
            System.out.println("rooms not null - streaming");
//                     rooms.stream()
//                    .skip(page * limit)
//                    .limit(limit);
           // pagingTest(rooms);
           // System.out.println("pagingTest 리턴 후  ::: "+rooms);

            newRooms=
                    rooms.stream()
                            .skip((page-1)*limit) //스킵할 갯수
                            .limit(limit) // 한 페이지당 보여줄 갯수
                            .collect(Collectors.toList());
        }

        System.out.println("paging ::: "+newRooms);
        req.setAttribute("rooms",newRooms);

        //2)pageBar 영역
        int totalCount = roomService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = PetkingUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);
        System.out.println("게시물 총 갯수:"+totalCount);
        //System.out.println(boards);

        req.getRequestDispatcher("/WEB-INF/views/room/roomList.jsp").forward(req,resp);
    }

    private List<RoomVo> pagingTest(List<RoomVo> rooms) {

        List<RoomVo> newRooms=
                rooms.stream()
                .skip(4) //스킵할 갯수
                .limit(4) // 보여줄 갯수
                .collect(Collectors.toList()); //다시 list형태로 되돌림
        return newRooms;
    }
}
