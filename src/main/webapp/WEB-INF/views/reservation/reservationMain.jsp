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
<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">
    <table id="celebs">
        <thead>
        <tr>
            <th>객실 이름</th>
            <th>객실 타입</th>
            <th>적정 인원</th>
            <th>최대 인원</th>
        <tr>
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
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-xl">
            객실 목록
        </h1>
    </div>
    <div class="flex-grow border-t border-gray-400"></div>
    <%-- 조회 결과를 출력해줄 테이블   --%>
    <div>
        <table id="roomSearchResult">
            <thead>

            </thead>
            <tbody>

            </tbody>
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
                                        <input type="checkbox" checked name="serviceId" value="${service.id}"
                                               class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                            ${service.name}
                                    </div>
                                </c:if>
                                <c:if test="${!camp.campWithServices.toString().contains(service.name)}">
                                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                        <input type="checkbox" name="serviceId" value="${service.id}"
                                               class="hidden peer tag-btn">
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
                                            <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400"
                                                 aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                                 viewBox="0 0 20 16">
                                                <path stroke="currentColor" stroke-linecap="round"
                                                      stroke-linejoin="round" stroke-width="2"
                                                      d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                            </svg>
                                            <p class="mb-2 text-sm text-gray-500 text-center"><span
                                                    class="font-semibold">사진 업로드<br></span></p>
                                        </div>
                                        <input id="campImg${vs.index}" name="campDetailImg" type="file"
                                               class="campImg hidden campAttach"/>
                                    </label>
                                </div>
                            </c:forEach>
                            <c:forEach var="box" begin="0" end="${9 - camp.campAttaches.size()}" varStatus="vs">
                                <div class="flex items-center justify-center w-full">
                                    <label for="campImg${vs.index + camp.campAttaches.size()}"
                                           style="background-size: cover; background-position: center"
                                           class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                        <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                            <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400"
                                                 aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                                                 viewBox="0 0 20 16">
                                                <path stroke="currentColor" stroke-linecap="round"
                                                      stroke-linejoin="round" stroke-width="2"
                                                      d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                            </svg>
                                            <p class="mb-2 text-sm text-gray-500 text-center"><span
                                                    class="font-semibold">사진 업로드<br></span></p>
                                        </div>
                                        <input id="campImg${vs.index + camp.campAttaches.size()}" name="campDetailImg"
                                               type="file" class="campImg hidden campAttach"/>
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
                                        <input type="checkbox" checked name="tagId" value="${tag.id}"
                                               class="hidden peer tag-btn">
                                            ${tag.name}
                                    </div>
                                </c:if>
                                <c:if test="${!camp.campWithTags.toString().contains(tag.name)}">
                                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                        <input type="checkbox" name="tagId" value="${tag.id}"
                                               class="hidden peer tag-btn">
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
                            <button id="updateDetailBtn" type="button"
                                    class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
                                수정하기
                            </button>
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