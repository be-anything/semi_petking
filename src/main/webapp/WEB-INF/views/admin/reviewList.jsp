<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2023-12-29
  Time: PM 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<div class="w-3/4 mx-auto mt-8">
    <div class="overflow-x-auto">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400 border-collapse">
            <thead class="text-xs text-white bg-green">
        <tr>
            <th scope="col" class="px-6 py-2">
                번호
            </th>
            <th scope="col" class="px-8 py-4">
                회원아이디
            </th>
            <th scope="col" class="px-8 py-4">
                리뷰 태그
            </th>
            <th scope="col" class="px-8 py-4">
                캠핑장
            </th>
            <th scope="col" class="px-8 py-4">
                리뷰 제목
            </th>
            <th scope="col" class="px-4 py-2">
                조회수
            </th>
            <th scope="col" class="px-8 py-4">
                작성시간
            </th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review" varStatus="vs">
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <td class="px-8 py-6 font-medium text-gray-900">${vs.index + 1}</td>
            <td class="px-8 py-6">
                <a href="${pageContext.request.contextPath}/admin/reviewDetail?id=${review.id}">
                ${review.userId}
            </td>
            <td class="px-8 py-6">
                ${review.reviewTag}
            </td>
            <td class="px-8 py-6">
                    ${review.camp.campName}
            </td>
            <td class="px-8 py-6">
                ${review.reviewTitle}
            </td>
            <td class="px-8 py-6">
                ${review.viewCount}
            </td>
            <td class="px-8 py-6">
                <fmt:parseDate value="${review.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
            </td>
            <td>
            <button
                    class="del-btn bg-red text-white px-4 py-2 rounded focus:outline-none focus:shadow-outline-red hover:bg-red-600">
                삭제
            </button>
                <form name="reviewDeleteFrm" action="${pageContext.request.contextPath}/admin/reviewDelete" method="post">
                    <input type="hidden" name="id" value="${review.id}">
                </form>
            </td>
        </tr>

        </c:forEach>
        </tbody>
    </table>


    <div class="flex justify-center mt-8">
        <nav aria-label="Page navigation example">
            <ul class="flex items-center -space-x-px h-8 text-sm">
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/review/reviewDelete.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    