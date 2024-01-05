<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.Calendar"%>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservation/reservationMain.css">

<%--<%--%>
<%--    request.setCharacterEncoding("utf-8");--%>

<%--    Calendar cal = Calendar.getInstance();--%>

<%--    // 시스템 오늘날짜--%>
<%--    int ty = cal.get(Calendar.YEAR);--%>
<%--    int tm = cal.get(Calendar.MONTH)+1;--%>
<%--    int td = cal.get(Calendar.DATE);--%>

<%--    int year = cal.get(Calendar.YEAR);--%>
<%--    int month = cal.get(Calendar.MONTH)+1;--%>

<%--    // 파라미터 받기--%>
<%--    String sy = request.getParameter("year");--%>
<%--    String sm = request.getParameter("month");--%>

<%--    if(sy!=null) { // 넘어온 파라미터가 있으면--%>
<%--        year = Integer.parseInt(sy);--%>
<%--    }--%>
<%--    if(sm!=null) {--%>
<%--        month = Integer.parseInt(sm);--%>
<%--    }--%>

<%--    cal.set(year, month-1, 1);--%>
<%--    year = cal.get(Calendar.YEAR);--%>
<%--    month = cal.get(Calendar.MONTH)+1;--%>

<%--    int week = cal.get(Calendar.DAY_OF_WEEK); // 1(일)~7(토)--%>
<%--%>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Insert title here</title>--%>
<%--    <link rel="icon" href="data:;base64,iVBORw0KGgo=">--%>
<%--    <style type="text/css">--%>
<%--        *{--%>
<%--            margin: 0; padding: 0;--%>
<%--            box-sizing: border-box;--%>
<%--        }--%>

<%--        body {--%>
<%--            font-size: 14px;--%>
<%--            font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;--%>
<%--        }--%>

<%--        a {--%>
<%--            color: #000;--%>
<%--            text-decoration: none;--%>
<%--            cursor: pointer;--%>
<%--        }--%>
<%--        a:active, a:hover {--%>
<%--            text-decoration: underline;--%>
<%--            color: #F28011;--%>
<%--        }--%>

<%--        .calendar {--%>
<%--            width: 280px;--%>
<%--            margin: 30px auto;--%>
<%--        }--%>
<%--        .calendar .title{--%>
<%--            height: 37px;--%>
<%--            line-height: 37px;--%>
<%--            text-align: center;--%>
<%--            font-weight: 600;--%>
<%--        }--%>

<%--        .calendar table {--%>
<%--            width: 100%;--%>
<%--            border-collapse: collapse;--%>
<%--            border-spacing: 0;--%>
<%--        }--%>

<%--        .calendar table thead tr:first-child{--%>
<%--            background: #f6f6f6;--%>
<%--        }--%>

<%--        .calendar table td{--%>
<%--            padding: 10px;--%>
<%--            text-align: center;--%>
<%--            border: 1px solid #ccc;--%>
<%--        }--%>

<%--        .calendar table td:nth-child(7n+1){--%>
<%--            color: red;--%>
<%--        }--%>
<%--        .calendar table td:nth-child(7n){--%>
<%--            color: blue;--%>
<%--        }--%>
<%--        .calendar table td.gray {--%>
<%--            color: #ccc;--%>
<%--        }--%>
<%--        .calendar table td.today{--%>
<%--            font-weight:700;--%>
<%--            background: deeppink;--%>
<%--        }--%>

<%--        .calendar .footer{--%>
<%--            height: 25px;--%>
<%--            line-height: 25px;--%>
<%--            text-align: right;--%>
<%--            font-size: 12px;--%>
<%--        }--%>

<%--        #myTable ,th ,td--%>
<%--        {--%>
<%--            border: 1px solid black;--%>
<%--        }--%>

<%--        .changeColor {--%>
<%--            background-color: #bff0ff;--%>
<%--        }--%>

<%--    </style>--%>

<%--</head>--%>
<%--<body>--%>

<%--<div class="calendar">--%>
<%--    <div class="title">--%>
<%--        <a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>"><</a>--%>
<%--        <label><%=year%>년 <%=month%>월</label>--%>
<%--        <a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">></a>--%>
<%--    </div>--%>

<%--    <table>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <td>일</td>--%>
<%--            <td>월</td>--%>
<%--            <td>화</td>--%>
<%--            <td>수</td>--%>
<%--            <td>목</td>--%>
<%--            <td>금</td>--%>
<%--            <td>토</td>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
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
<%--        </tbody>--%>
<%--    </table>--%>

<%--    <div class="footer">--%>
<%--        <a href="calendar.jsp">오늘날짜로</a>--%>
<%--    </div>--%>

<%--</div>--%>

<%--<table id="myTable" border=1>	<thead>	    <tr>	        <th></th>	        <th>sample1</th>	        <th>sample2</th>	        <th>sample3</th>	        <th>sample4</th>	    </tr>	</thead>	<tbody>	    <tr>	        <th>sample1</th>	        <td>Text1</td>	        <td>Text2</td>	        <td>Text3</td>	        <td>Text4</td>	    </tr>	    <tr>	        <th>sample2</th>	        <td>Text11</td>	        <td>Text22</td>	        <td>Text33</td>	        <td>Text44</td>	    </tr>	    <tr>	        <th>sample3</th>	        <td>Text111</td>	        <td>Text222</td>	        <td>Text333</td>	        <td>Text444</td>	    </tr>	    <tr>	        <th>sample4</th>	        <td>Text1111</td>	        <td>Text2222</td>	        <td>Text3333</td>	        <td>Text4444</td>	    </tr>	</tbody></table>--%>



<%--</body>--%>
<%--</html>--%>
<%--<script>--%>
<%--    //:  DOM 객체만 로드 되자마자 처리됩니다. 즉 load 보다 먼저 일어납니다.--%>
<%--    $(document).ready(function(){--%>
<%--        changeColor();--%>
<%--        clickTd();--%>
<%--        clickTr();})--%>

<%--    function changeColor(){--%>
<%--        $('#myTable tr').mouseover(function() {--%>
<%--            $(this).addClass('changeColor');--%>
<%--        }).mouseout(function() {--%>
<%--            $(this).removeClass('changeColor');--%>
<%--        });--%>

<%--    }--%>

<%--    function clickTd(){--%>
<%--        $("#myTable tr td").click(function(){--%>
<%--            var text = $(this).text();--%>
<%--            alert(text);--%>
<%--        });  }--%>
<%--    function clickTr(){--%>
<%--        $("#myTable tr").click(function(){--%>
<%--            var text = $(this).text();--%>
<%--            alert(text);--%>
<%--        });--%>
<%--    }--%>


<%--    $("#table tr").click(function(){--%>
<%--        var tdArray = new Array();--%>
<%--        var td = $(this).children();--%>
<%--        //tr에 있는 모든 데이터--%>
<%--        console.log($(this).text());--%>

<%--        //만들어둔 배열에 td값을 하나하나 담아준다.--%>
<%--        td.each(function(i){--%>
<%--            tdArray.push(td.eq(i).text());	});--%>
<%--        console.log(tdArray);--%>
<%--        var td1 = tdArray[0];--%>
<%--        var td2 = tdArray[1];--%>
<%--        var td3 = tdArray[2];--%>
<%--        var td4 = tdArray[3];--%>
<%--        var td5 = tdArray[4];--%>
<%--        console.log(td1);--%>
<%--        console.log(td2);--%>
<%--        console.log(td3);--%>
<%--        console.log(td4);--%>
<%--        console.log(td5);--%>
<%--    });--%>
<%--</script>--%>

<div class="container mx-auto my-6">
<div class="flex justify-start">
    <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
        예약하기(펜션명 출력)
    </h1>
</div>
    <div class="flex-grow border-t border-gray-400"></div>


    <div class="mx-auto my-6" >
        <div class="flex justify-normal">
            <table align="center" id="calendarTest">
                <tr>
                    <td><font size=1%; color="#B3B6B3"><label onclick="beforem()" id="before" ></label></font></td>
                    <td colspan="5" align="center" id="yearmonth"></td>
                    <td><font size=1%; color="#B3B6B3"><label onclick="nextm()" id="next"></label></font></td>
                </tr>
                <tr>
                    <td align="center"> <font color="#FF9090">일</font></td>
                    <td align="center"> 월 </td>
                    <td align="center"> 화 </td>
                    <td align="center"> 수 </td>
                    <td align="center"> 목 </td>
                    <td align="center"> 금 </td>
                    <td align="center"><font color=#7ED5E4>토</font></td>
                </tr>
            </table>
        </div>
        <button id="btn-check"
                class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
            모든캠핑장 조회
        </button>

    </div>

    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-xl">
            객실 목록()
        </h1>
    </div>
    <div class="flex-grow border-t border-gray-400"></div>

<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">
    <table id="rooms">
        <tr>
            <th>방번호</th>
            <td class="room-id"></td>
        </tr>
        <tr>
            <th>캠핑장</th>
            <td class="room-camp-id"></td>
        </tr>
        <tr>
            <th>방이름</th>
            <td class="room-name"></td>
        </tr>
    </table>
</div>

<div class="justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
    <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-10 sm:px-0 items-start">
        <form name="campDetailUpdateFrm">
            <input type="hidden" name="campId" value="${camp.id}">
            <div class="px-4 sm:px-0">
                <dl class="mt-10 mx-4">
                    <h1 class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">부가서비스</h1>
                    <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                        <c:forEach items="${campServices}" var="service">
                            <c:if test="${camp.campWithServices.toString().contains(service.name)}">
                                <div class="options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" checked name="serviceId" value="${service.id}" class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5" >
                                        ${service.name}
                                </div>
                            </c:if>
                            <c:if test="${!camp.campWithServices.toString().contains(service.name)}">
                                <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" name="serviceId" value="${service.id}" class="hidden peer tag-btn" >
                                        ${service.name}
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <h1 class="block mb-2 mt-5 text-xl font-medium text-gray-900 dark:text-white">캠핑장 소개 사진</h1>
                    <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                        <c:forEach items="${camp.campAttaches}" var="campAttach" varStatus="vs">
                            <div class="flex items-center justify-center w-full">
                                <label for="campImg${vs.index}"
                                       style="background-size: cover; background-position: center; background-image: url('${pageContext.request.contextPath}/upload/camp/${campAttach.campAttachRenamedName}')"
                                       class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                    <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                        <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                        </svg>
                                        <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span></p>
                                    </div>
                                    <input id="campImg${vs.index}" name="campDetailImg" type="file" class="campImg hidden campAttach" />
                                </label>
                            </div>
                        </c:forEach>
                        <c:forEach var="box" begin="0" end="${9 - camp.campAttaches.size()}" varStatus="vs">
                            <div class="flex items-center justify-center w-full">
                                <label for="campImg${vs.index + camp.campAttaches.size()}"
                                       style="background-size: cover; background-position: center"
                                       class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                    <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                        <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                        </svg>
                                        <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span></p>
                                    </div>
                                    <input id="campImg${vs.index + camp.campAttaches.size()}" name="campDetailImg" type="file" class="campImg hidden campAttach" />
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <%-- 캠핑장 태그 --%>
                    <h1 class="mt-5 block mb-2 text-xl font-medium text-gray-900 dark:text-white">태그를 선택해주세요</h1>
                    <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                        <c:forEach items="${campTags}" var="tag">
                            <c:if test="${camp.campWithTags.toString().contains(tag.name)}">
                                <div class="options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" checked name="tagId" value="${tag.id}" class="hidden peer tag-btn" >
                                        ${tag.name}
                                </div>
                            </c:if>
                            <c:if test="${!camp.campWithTags.toString().contains(tag.name)}">
                                <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" name="tagId" value="${tag.id}" class="hidden peer tag-btn" >
                                        ${tag.name}
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <div class="px-4 py-4 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                        <!-- 레이아웃용 빈 div -->
                        <div></div>
                        <div></div>
                        <div></div>
                        <button id="updateDetailBtn" type="button" class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">수정하기</button>
                    </div>
                </dl>
            </div>
        </form>
    </div>
</div>
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