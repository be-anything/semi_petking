<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2024-01-04
  Time: PM 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
    .forms {
        display: none;
    }
    .active {
        display: block;
    }
</style>
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg mt-10">
    <ul class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 ">
        <li class="tabBtn">
            <a href="${pageContext.request.contextPath}/admin/registList" aria-current="page" class="inline-block p-4 rounded-t-lg  active px-5">캠핑장 신규등록 요청</a>
        </li>
        <li class="tabBtn">
            <a href="${pageContext.request.contextPath}/admin/deleteRegist" class="inline-block p-4 text-white bg-green rounded-t-lg px-5">캠핑장 삭제 요청</a>
        </li>

    </ul>
</div>
<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">

    <div class="relative overflow-x-auto">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
            <th scope="col" class="px-6 py-3">
                번호
            </th>
            <th scope="col" class="px-6 py-3">
                캠핑장 이름
            </th>
            <th scope="col" class="px-6 py-3">
                사업자 이름
            </th>
            <th scope="col" class="px-6 py-3">
                사업자 아이디
            </th>
            <th scope="col" class="px-6 py-3">
                삭제요청일
            </th>
            <th scope="col" class="px-6 py-3">
                상태
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${campVos}" var="camp" varStatus="vs">

            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        ${vs.index + 1}
                </th>
                <td class="px-6 py-4">
                    <a href="${pageContext.request.contextPath}/admin/campDeleteRequest?id=${camp.id}">
                            ${camp.campName}
                </td>
                <td class="px-6 py-4">
                        ${camp.businessName}
                </td>
                <td class="px-6 py-4">
                        ${camp.businessId}
                </td>
                <td class="px-6 py-4">
                    <fmt:parseDate value="${camp.regDate}" pattern="yyyy-MM-dd" var="regDate"/>
                    <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
                </td>
                <td class="px-6 py-4">
                    <c:if test="${camp.campState eq 2}">
                        <c:set target="${camp}" property="campState" value="1" />
                        <p>삭제승인대기</p>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="flex justify-center mt-6">
        <nav aria-label="Page navigation example">
            <ul class="flex items-center -space-x-px h-8 text-sm">
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    