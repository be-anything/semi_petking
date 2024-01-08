<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2024-01-02
  Time: AM 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<div class="w-full mx-auto mt-8">
    <div class="overflow-x-auto">
        <table class="w-5/6 text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400 border-collapse">
            <thead class="text-xs text-white bg-green">
            <tr>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">번호</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">사업자 아이디</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">사업자 이름</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">캠핑장 이름</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">캠핑장 전화번호</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">캠핑장 주소</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">사업자번호</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">리뷰수</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">좋아요수</th>
                <th scope="col" class="px-6 py-4 whitespace-nowrap">가입일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${campVos}" var="camp" varStatus="vs">
                <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                    <td class="px-6 py-6 font-medium text-gray-900">${vs.index + 1}</td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.businessId}</td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.businessName}</td>
                    <td class="px-6 py-6 whitespace-nowrap">
                        <a href="${pageContext.request.contextPath}/camp/campDetail?id=${camp.id}">
                                ${camp.campName}
                        </a>
                    </td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.campPhone}</td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.campAddr}</td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.businessNumber}</td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.reviewCount}</td>
                    <td class="px-6 py-6 whitespace-nowrap">${camp.wishCount}</td>
                    <td class="px-6 py-6 whitespace-nowrap">
                        <fmt:parseDate value="${camp.regDate}" pattern="yyyy-MM-dd" var="regDate" />
                        <fmt:formatDate value="${regDate}" pattern="yy/MM/dd" />
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