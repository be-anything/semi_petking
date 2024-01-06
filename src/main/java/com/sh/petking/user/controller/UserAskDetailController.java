package com.sh.petking.user.controller;

import com.sh.petking.ask.model.exception.AskException;
import com.sh.petking.ask.model.service.AskService;
import com.sh.petking.ask.model.vo.AskVo;
import com.sh.petking.common.PetkingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 0104 문의게시글 상세 페이지
 * 제목 클릭 시 보여주는 상세페이지
 * 1.글번호
 * 2.제목
 * 3.작성자
 * 4.작성날짜
 * 5.작성내용
 * ----------
 * +
 * escape html 처리
 *
 */
@WebServlet("/user/userAskDetail")
public class UserAskDetailController extends HttpServlet {
    private AskService askService = new AskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.사용자 입력 값 처리.
        try {
            long id = Long.parseLong(req.getParameter("id"));
            System.out.println("현재 문의 게시글 번호:"+id);

            //2.업무로직
            //조회
            AskVo ask = askService.findById(id); //id로 특정 문의글 1개 조회

            //xss공격대비 escapehtml처리
            String safeHtml = PetkingUtils.escapeHtml(ask.getAskContent());
            //개행문자(\n) -> <br>
            ask.setAskContent(PetkingUtils.convertLineFeedToBr(safeHtml));
            req.setAttribute("ask", ask); //결과값으로 가져온 ask를 저장

            System.out.println(ask.getCamp());
            //3.forward
            req.getRequestDispatcher("/WEB-INF/views/user/campAskDetail.jsp").forward(req, resp);
        } catch (Exception e)
        {
            throw new AskException("문의글 상세보기 오류",e);
        }

        //예외 전환해서 던지기 : 사용자 친화적 메시지, 원인예외 wrapping
        //catch 하단에 최상위 예외 Exception e로 변경 후
        //throw new BoardException("게시글 상세보기 오류",e);

    }


}