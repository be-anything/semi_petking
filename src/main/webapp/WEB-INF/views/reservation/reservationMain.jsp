<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservation/reservationMain.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<div class="container mx-auto my-6">
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            예약하기(펜션명 출력)
        </h1>
    </div>
    <div class="flex-grow border-t border-gray-400"></div>
        <%
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = simpleDate.format(date);

    %>
    <p>오늘 날짜 : ${strDate}</p><%=strDate%>
    <p>예약 일정을 선택해주세요.</p>
    <div class="flex justify-start mx-auto my-6">

        <form action="roomSearchForm">
            <fieldset>
                <input type="text" id="datepicker1" name="datepicker1" autocomplete="off" ><input type="text" id="datepicker2" name="datepicker2" autocomplete="off">
                캠프id:<input type="number" id="campId" name="campId">
                <button id="btn-search" type="button"
                        class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
                    조회
                </button>
            </fieldset>
        </form>
    </div>

    <div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">

        <div class="flex justify-start">
            <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                객실 리스트
            </h1>
        </div>
        <div class="flex-grow border-t border-gray-400"></div>
        <table id="roomSearchResult">
            <thead>
            </thead>
            <tbody>
            <%--        <%--%>
            <%--            // 1일 앞 달--%>
            <%--            Calendar preCal = (Calendar)cal.clone();--%>
            <%--            preCal.add(Calendar.DATE, -(week-1));--%>
            <%--            int preDate = preCal.get(Calendar.DATE);--%>

            <%--            out.print("<tr>");--%>
            <%--            // 1일 앞 부분--%>
            <%--            for(int i=1; i<week; i++) {--%>
            <%--                //out.print("<td> </td>");--%>
            <%--                out.print("<td class='gray'>"+(preDate++)+"</td>");--%>
            <%--            }--%>

            <%--            // 1일부터 말일까지 출력--%>
            <%--            int lastDay = cal.getActualMaximum(Calendar.DATE);--%>
            <%--            String cls;--%>
            <%--            for(int i=1; i<=lastDay; i++) {--%>
            <%--                cls = year==ty && month==tm && i==td ? "today":"";--%>

            <%--                out.print("<td class='"+cls+"'>"+i+"</td>");--%>
            <%--                if(lastDay != i && (++week)%7 == 1) {--%>
            <%--                    out.print("</tr><tr>");--%>
            <%--                }--%>
            <%--            }--%>

            <%--            // 마지막 주 마지막 일자 다음 처리--%>
            <%--            int n = 1;--%>
            <%--            for(int i = (week-1)%7; i<6; i++) {--%>
            <%--                // out.print("<td> </td>");--%>
            <%--                out.print("<td class='gray'>"+(n++)+"</td>");--%>
            <%--            }--%>
            <%--            out.print("</tr>");--%>
            <%--        %>--%>
            </tbody>
        </table>
    </div>



    <%--객실정보 수정--%>
    <div class="justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
        <div class="px-4 py-6 sm:grid sm:grid-cols-1 sm:gap-10 sm:px-0 items-start">
            <form name="campRoomUpdateFrm" class="">

            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/reservation/reservationMain.js"></script>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>