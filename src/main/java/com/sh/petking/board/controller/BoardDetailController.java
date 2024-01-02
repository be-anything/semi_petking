package com.sh.petking.board.controller;

import com.sh.petking.board.model.exception.BoardException;
import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board/boardDetail")
public class BoardDetailController extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 사용자입력값 처리
            long id = Long.parseLong(req.getParameter("id"));
            System.out.println(id);


            // 2. 업무로직
            // 조회수 관련처리
            Cookie[] cookies = req.getCookies();
            List<String> boardCookieValues = getBoardCookieValues(cookies);
            boolean hasRead = boardCookieValues.contains(String.valueOf(id)); // 현재 게시글 읽음여부
            System.out.println(hasRead); // true 이미 읽었음, false 처음 읽음

            // 조회
//            BoardVo board = boardService.findById(id, hasRead);
//            System.out.println(board);
//
//            // xss공격대비 escapeHtml처리
//            String safeHtml = PetkingUtils.escapeHtml(board.getBoardContent());
//            // 개행문자 (\n) -> <br>
//            board.setBoardContent(PetkingUtils.convertLineFeedToBr(safeHtml));
//            req.setAttribute("board", board);

            // 응답 쿠키 생성
            // 만료시간 쿠키종류
            // - session cookie -1 지정한 경우, session이 만료되면 쿠키 자동 삭제
            // - persistant cookie n초 지정한 경우
            if(!hasRead) {
                boardCookieValues.add(String.valueOf(id)); // 현재 게시글 id를 추가
                String value = String.join("/", boardCookieValues); // [12,34,56] -> "12/34/56"
                Cookie cookie = new Cookie("board", value);
                cookie.setMaxAge(365 * 24 * 60 * 60); // 음수인 경우 session종료시 삭제, 0인 경우 즉시 삭제
                cookie.setPath(req.getContextPath() + "/board/boardDetail"); // 지정한 경로일때만 클라이언트에서 서버로 쿠키 전송
                resp.addCookie(cookie);
            }
            // 3. forward
            req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req, resp);
        } catch (Exception e) {
            // 예외 전환해서 던지기 : 사용자친화적 메세지, 원인예외 wrapping
//            throw new BoardException("게시글 상세보기 오류", e);
        }
    }

    private List<String> getBoardCookieValues(Cookie[] cookies) {
        List<String> boardCookieValues = new ArrayList<>();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + " = " + value);
                if("board".equals(name)) {
                    String[] temp = value.split("/"); // 23/34/56
                    for (String _id : temp) {
                        boardCookieValues.add(_id);
                    }
                }
            }
        }
        return boardCookieValues;
    }
}
