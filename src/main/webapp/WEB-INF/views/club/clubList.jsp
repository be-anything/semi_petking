<%--
  Created by IntelliJ IDEA.
  User: 82104
  Date: 2024-01-04
  Time: 오후 6:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<c:if test="${loginUser != null && loginUser.clubId == null}">
<div class="flex justify-center pt-[300px]">
    <div class="w-fit h-fit mr-1">
        <a href="${pageContext.request.contextPath}/club/clubCreate" class="rounded-lg py-[120px] px-[100px] pt-[180px] bg-gray2 hover:bg-indigo-100 hover:drop-shadow-2xl">
            <i class="fa-solid fa-users text-9xl text-black"></i>
        </a>
        <p class="mt-[125px] text-lg text-center">동아리 생성</p>
    </div>
    <div class="w-fit h-fit ml-1">
        <a href="${pageContext.request.contextPath}/board/boardList" class="rounded-lg py-[120px] px-[100px] pt-[180px] bg-gray2 hover:bg-purple-100 hover:drop-shadow-2xl">
            <i class="fa-solid fa-sign-in text-9xl text-black"></i>
        </a>
        <p class="mt-[125px] text-lg text-center">동아리 가입</p>
    </div>
</div>
</c:if>

<c:if test="${loginUser != null && loginUser.clubId != null}">
<div class="xl:container p-8">
    <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">가입 한 동아리 : ${loginUser.clubId}번 동아리</h5>
    </div>
</div>
<div class="py-8 px-60 mx-auto max-w-2xl lg:py-16">
        <a href="${pageContext.request.contextPath}/club/clubDetail?id=${loginUser.clubId}" class="rounded-lg py-[120px] px-[100px] pt-[180px] bg-gray2 hover:bg-orange-100 hover:drop-shadow-2xl">
            <i class="fa fa-smile-o  text-9xl text-black"></i>
        </a>
        <p class="mt-[125px] text-lg text-center">내 동아리 들어가기</p>
</div>
    <div class="w-full mx-auto mt-8">
    <div class="overflow-x-auto">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400 border-collapse">
        <thead class="text-xs text-white bg-green">
        <tr>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                가입 번호
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                동아리 번호
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                회원 아이디
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                가입 상태
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                가입 날짜
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                회원 구분
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clubUsers}" var="clubUsers" varStatus="vs">
            <c:if test="${loginUser.clubId eq clubUsers.clubId}">
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <td class="px-8 py-6 font-medium text-gray-900">${vs.index + 1}</td>
                <td class="px-8 py-6">
                        ${clubUsers.clubId}
                </td>
                <td class="px-8 py-6">
                        ${clubUsers.userId}
                </td>
                <td class="px-8 py-6">
                        ${clubUsers.joinState == 1 ? '가입 회원' : ''}
                </td>
                <td class="px-8 py-6">
                    <fmt:parseDate value="${clubUsers.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                    <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
                </td>
                <td class="px-8 py-6">
                            ${clubUsers.role == 'A' ? '동아리 장' : '회원'}
                </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

</c:if>

<c:if test="${loginUser == null}">
    <div class="xl:container p-8">
        <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
            <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">로그인 후 사용 가능합니다!</h5>
        </div>
    </div>
</c:if>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>