<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2023-12-29
  Time: PM 6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<div class="w-5/6 mx-auto mt-8">
    <div class="overflow-x-auto">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400 border-collapse">
            <thead class="text-xs text-white bg-green">
            <tr>
                <th scope="col" class="px-8 py-4 text-left">번호</th>
                <th scope="col" class="px-8 py-4">회원아이디</th>
                <th scope="col" class="px-8 py-4">회원이름</th>
                <th scope="col" class="px-8 py-4">권한</th>
                <th scope="col" class="px-8 py-4">탈퇴일</th>
                <th scope="col" class="px-8 py-4">탈퇴사유</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${delUsers}" var="delUser" varStatus="vs">
                <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                    <td class="px-8 py-6 font-medium text-gray-900">${vs.index + 1}</td>
                    <td class="px-8 py-6">${delUser.userId}</td>
                    <td class="px-8 py-6">${delUser.name}</td>
                    <td class="px-8 py-6">${delUser.role}</td>
                    <td class="px-8 py-6">${delUser.delDate}</td>
                    <td class="px-8 py-6">${delUser.delReason}</td>
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

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    