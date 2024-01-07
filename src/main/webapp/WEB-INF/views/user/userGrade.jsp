<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2024-01-07
  Time: PM 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container mx-auto my-6">
        <h1 class="text-2xl font-bold text-gray-900 md:text-3xl">
            ${userVo.name} 님의 등급은 ${userVo.userGrade.name} 입니다. 🐶
        </h1>
</div>

<div class="container mx-auto mt-8">
    <div class="overflow-x-auto">
        <table class="w-full text-sm text-left text-gray-500 border-collapse">
            <thead class="text-xs text-white bg-green">
            <tr>
                <th scope="col" class="px-6 py-3">번호</th>
                <th scope="col" class="px-6 py-3">사용자 아이디</th>
                <th scope="col" class="px-6 py-3">포인트</th>
                <th scope="col" class="px-6 py-3">적립내용</th>
                <th scope="col" class="px-6 py-3">적립일시</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${point}" var="point" varStatus="vs">
                <tr class="bg-white border-b">
                    <td class="px-6 py-4 font-medium text-gray-900">${vs.index + 1}</td>
                    <td class="px-6 py-4">${point.userId}</td>
                    <td class="px-6 py-4">${point.point}</td>
                    <td class="px-6 py-4 break-all">${point.pointLog}</td>
                    <td class="px-6 py-4">
                        <fmt:parseDate value="${point.regDate}" pattern="yyyy-MM-dd" var="regDate" />
                        <fmt:formatDate value="${regDate}" pattern="yy/MM/dd" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    