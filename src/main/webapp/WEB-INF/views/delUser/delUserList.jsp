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
<div class="w-6xl ml-82">

<div class="relative overflow-x-auto">
    <table class="w-62 text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
            <th scope="col" class="px-6 py-3">
                번호
            </th>
            <th scope="col" class="px-6 py-3">
                회원아이디
            </th>
            <th scope="col" class="px-6 py-3">
                회원이름
            </th>
            <th scope="col" class="px-6 py-3">
                권한
            </th>
            <th scope="col" class="px-6 py-3">
                탈퇴일
            </th>
            <th scope="col" class="px-6 py-3">
                탈퇴사유
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${delUsers}" var="delUser" varStatus="vs">
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        ${vs.index + 1}
                </th>
                <td class="px-6 py-4">
                        ${delUser.userId}
                </td>
                <td class="px-6 py-4">
                        ${delUser.name}
                </td>
                <td class="px-6 py-4">
                        ${delUser.role}
                </td>
                <td class="px-6 py-4">
                    ${delUser.delDate}
                </td>
                <td class="px-6 py-4">
                        ${delUser.delReason}
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