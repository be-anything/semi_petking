package com.sh.petking.board.controller;

import com.sh.petking.board.model.service.BoardService;
import com.sh.petking.board.model.vo.BoardVo;

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
        // 1. 사용자입력값 처리
        long id = Long.parseLong(req.getParameter("id"));

        // 조회수 관련 처리
        Cookie[] cookies = req.getCookies();
        List<String> boardCookieValues = getBoardCookieValues(cookies);
        boolean hasRead = boardCookieValues.contains(String.valueOf(id));
        System.out.println(hasRead);

        BoardVo board =  boardService.findById(id, hasRead);

        System.out.println("댓글-----------");
        System.out.println(board.getComments());

        // 2. 업무로직
        req.setAttribute("board", board);
        System.out.println(board);

        // 3. 포워딩
        if(!hasRead){
            boardCookieValues.add(String.valueOf(id));
            String value = String.join("/", boardCookieValues);
            Cookie cookie = new Cookie("board", value);
            cookie.setMaxAge(365 * 24 * 60 * 60);
            cookie.setPath(req.getContextPath() + " /board/boardDetail");
            resp.addCookie(cookie);
        }
        req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req, resp);
    }

    private List<String> getBoardCookieValues(Cookie[] cookies) {
        List<String> boardCookieValues = new ArrayList<>();
        if(cookies != null){
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                if("board".equals(name)){
                    String[] temp = value.split("/");
                    for (String _id: temp) {
                        boardCookieValues.add((_id));
                    }
                }
            }
        }
        return boardCookieValues;
    }
}
