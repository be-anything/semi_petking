<%--
  Created by IntelliJ IDEA.
  User: min_j
  Date: 2024-01-06
  Time: PM 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/>

<style>
    .forms {
        display: none;
    }

    .active {
        display: block;
    }
</style>
<div class="ml-auto mr-32 w-2/3">
<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg">
    <ul class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 ">
        <li class="tabBtn">
            <a href="#" aria-current="page" class="inline-block p-4 rounded-t-lg text-white bg-green active px-5">완료된 예약</a>
        </li>
        <li class="tabBtn">
            <a href="#" class="inline-block p-4 rounded-t-lg px-5">진행중인 예약</a>
        </li>
        <li class="tabBtn">
            <a href="#" class="inline-block p-4 rounded-t-lg px-5">취소된 예약</a>
        </li>
    </ul>
</div>


<!-- 완료된 예약내역 -->
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl p-4 rounded-lg bg-gray1 mb-10 forms active">
    <h1 class="text-xl mt-8">완료된 예약은 총 <span class="text-blue font-bold">${doneTotalCount}</span>건 입니다.</h1>
    <c:forEach items="${doneReservations}" var="reservation">
        <div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-white mt-5 mb-5 hover:drop-shadow">
            <div class="w-full flex items-center m-8">
                <a href="${pageContext.request.contextPath}/camp/campDetail?id=${reservation.campId}">
                <img class="w-[300px]" src="${pageContext.request.contextPath}/upload/camp/${reservation.campRenamedImg}" />
                </a>
                <div class="w-full ml-10 relative">
                    <div class="text-black text-base font-normal mt-5">
                        <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">예약번호</span>
                            petking - 000${reservation.id}
                    </div>
                    <div class="text-black text-base font-normal mt-5">
                        <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑장</span>
                            ${reservation.campName}
                    </div>
                    <div class="text-black text-base font-normal mt-5">
                        <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑존</span>
                            ${reservation.roomName}
                    </div>
                    <div class="text-black text-base font-normal mt-5">
                        <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">이용일시</span>
                            ${reservation.startDate} ~ ${reservation.endDate}
                    </div>
                    <div class="text-black text-base font-normal absolute right-0 bottom-[-1.75rem]">
                        <button type="button" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">예약내역 상세보기</button>
                        <button type="button" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">리뷰 작성하기</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="flex justify-center mt-6 mb-20">
        <nav aria-label="Page navigation example">
            <ul class="flex items-center -space-x-px h-8 text-sm">
                ${donePagebar}
            </ul>
        </nav>
    </div>
</div>


<!-- 진행중 예약내역 -->
    <div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl p-4 rounded-lg bg-gray1 mb-10 forms">
        <h1 class="text-xl mt-8">진행중인 예약은 총 <span class="text-blue font-bold">${processTotalCount}</span>건 입니다.</h1>
        <c:forEach items="${processReservations}" var="reservation">
            <div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-white mt-5 mb-5 hover:drop-shadow">
                <div class="w-full flex items-center m-8">
                    <a href="${pageContext.request.contextPath}/camp/campDetail?id=${reservation.campId}">
                        <img class="w-[300px]" src="${pageContext.request.contextPath}/upload/camp/${reservation.campRenamedImg}" />
                    </a>
                    <div class="w-full ml-10 relative">
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">예약번호</span>
                            petking - 000${reservation.id}
                        </div>
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑장</span>
                                ${reservation.campName}
                        </div>
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑존</span>
                                ${reservation.roomName}
                        </div>
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">이용일시</span>
                                ${reservation.startDate} ~ ${reservation.endDate}
                        </div>
                        <div class="text-black text-base font-normal absolute right-0 bottom-[-1.75rem]">
                            <button type="button" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">예약내역 상세보기</button>
                            <a href="${pageContext.request.contextPath}/ask/askCreate?campId=${reservation.campId}"><button type="submit" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">문의하기</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="flex justify-center mt-6 mb-20">
            <nav aria-label="Page navigation example">
                <ul class="flex items-center -space-x-px h-8 text-sm">
                    ${processPagebar}
                </ul>
            </nav>
        </div>
    </div>


<!-- 취소된 예약내역 -->
    <div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl p-4 rounded-lg bg-gray1 mb-10 forms">
        <h1 class="text-xl mt-8">취소된 예약은 총 <span class="text-blue font-bold">${cancelTotalCount}</span>건 입니다.</h1>
        <c:forEach items="${cancelReservations}" var="reservation">
            <div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-white mt-5 mb-5 hover:drop-shadow">
                <div class="w-full flex items-center m-8">
                    <a href="${pageContext.request.contextPath}/camp/campDetail?id=${reservation.campId}">
                        <img class="w-[300px]" src="${pageContext.request.contextPath}/upload/camp/${reservation.campRenamedImg}" />
                    </a>
                    <div class="w-full ml-10 relative">
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">예약번호</span>
                            petking - 000${reservation.id}
                        </div>
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑장</span>
                                ${reservation.campName}
                        </div>
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑존</span>
                                ${reservation.roomName}
                        </div>
                        <div class="text-black text-base font-normal mt-5">
                            <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">이용일시</span>
                                ${reservation.startDate} ~ ${reservation.endDate}
                        </div>
                        <div class="text-black text-base font-normal absolute right-0 bottom-[-1.75rem]">
                            <button type="button" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">예약내역 상세보기</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="flex justify-center mt-6 mb-20">
            <nav aria-label="Page navigation example">
                <ul class="flex items-center -space-x-px h-8 text-sm">
                    ${cancelPagebar}
                </ul>
            </nav>
        </div>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/user/userReservation.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>