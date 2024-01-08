<%--
  Created by IntelliJ IDEA.
  User: min_j
  Date: 2024-01-05
  Time: PM 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="flex mt-10 justify-between items-center mx-auto max-w-8xl rounded-lg mb-10 relative">
<button data-drawer-target="sidebar-multi-level-sidebar" data-drawer-toggle="sidebar-multi-level-sidebar" aria-controls="sidebar-multi-level-sidebar" type="button" class="inline-flex items-center p-2 mt-2 ms-3 text-sm text-gray-500 rounded-lg sm:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600">
    <span class="sr-only">Open sidebar</span>
    <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
        <path clip-rule="evenodd" fill-rule="evenodd" d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"></path>
    </svg>
</button>

<aside class="z-40 w-64 h-[600px] absolute top-0" aria-label="Sidebar">
    <div class="h-full px-3 py-4 overflow-y-auto bg-salmon dark:bg-gray-800 text-center text-black rounded-lg border border-gray2">
        <ul class="space-y-2 font-medium">
            <li class="flex justify-center">
                <a href="${pageContext.request.contextPath}/user/userDetail" class="w-[150px] h-[150px] flex items-center bg-light-pink p-2 rounded-full group">
                    <img class="flex-1 whitespace-nowrap" src="${pageContext.request.contextPath}/upload/user/${loginUser.renamedProfileName}">
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/grade?id=${loginUser.id}" class="flex items-center text-center text-black bg-light-pink p-2 hover:bg-white rounded-full group">
                    <span class="flex-1 whitespace-nowrap">포인트 000점</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userDetail" class="flex items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                    <span class="flex-1 whitespace-nowrap">내 정보보기</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userWishList" class="flex items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                    <span class="flex-1 whitespace-nowrap">찜 목록</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userReservation" class="flex items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                    <span class="flex-1 whitespace-nowrap">예약 내역</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userReviewList?userId=${loginUser.id}" class="flex items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                    <span class="flex-1 whitespace-nowrap">리뷰 내역</span>
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/user/userAskList?userId=${loginUser.id}" class="flex items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                    <span class="flex-1 whitespace-nowrap">문의 내역</span>
                </a>
            </li>
<%--            <li>--%>
<%--                <a href="${pageContext.request.contextPath}/user/userDelete" class="flex mt-10 items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">--%>
<%--                    <span class="flex-1 whitespace-nowrap text-sm font-regular">회원탈퇴</span>--%>
<%--                </a>--%>
<%--            </li>--%>

            <%-- 수업시간 코드 --%>
            <li>
                <p class="flex mt-10 items-center p-2 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                    <a href="javascript:confirm('정말 탈퇴하시겠습니까?') && document.userDeleteFrm.submit();" class="flex-1 whitespace-nowrap text-sm font-regular">회원탈퇴</a>
                </p>
                <form name="userDeleteFrm" action="${pageContext.request.contextPath}/user/userDelete" method="post">
                    <input type="hidden" name="id" value="${loginUser.id}">
                    <input type="hidden" name="password" value="${loginUser.password}">
                </form>
            </li>

        </ul>
    </div>
</aside>

    <script src="${pageContext.request.contextPath}/js/user/userDelete.js"></script>
<%--<form action="${pageContext.request.contextPath}/user/userDelete" method="post" name="userDeleteFrm"></form>--%>
