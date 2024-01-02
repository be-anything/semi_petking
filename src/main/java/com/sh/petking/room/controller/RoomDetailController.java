package com.sh.petking.room.controller;

import com.sh.petking.common.PetkingUtils;
import com.sh.petking.room.model.exception.RoomException;
import com.sh.petking.room.model.service.RoomService;
import com.sh.petking.room.model.vo.RoomVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 객실 페이지 상세 보기 only read.
 *  Escape Html처리 할 것.
 */
@WebServlet("/room/roomDetail")
public class RoomDetailController extends HttpServlet {

    private RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("객실페이지 상세보기........");

        //1.사용자 입력값 처리
        try {
            long id = Long.parseLong(req.getParameter("id"));
            System.out.println("현재 객실 글 번호:"+id);

            //2.업무로직

            //조회
            RoomVo room = roomService.findById(id); //id와 조회여부까지 확인한다.

            //xss공격대비 escapehtml처리
            String safeHtml = PetkingUtils.escapeHtml(room.getRoomIntro());
            //개행문자(\n) -> <br>
            room.setRoomIntro(PetkingUtils.convertLineFeedToBr(safeHtml));
            req.setAttribute("room", room);
            //3.forward
            req.getRequestDispatcher("/WEB-INF/views/room/roomDetail.jsp").forward(req, resp);
        } catch (Exception e)
        {
            throw new RoomException("객실글 상세보기 오류",e);
        }

        //예외 전환해서 던지기 : 사용자 친화적 메시지, 원인예외 wrapping
        //catch 하단에 최상위 예외 Exception e로 변경 후
        //throw new BoardException("게시글 상세보기 오류",e);


    }

}
