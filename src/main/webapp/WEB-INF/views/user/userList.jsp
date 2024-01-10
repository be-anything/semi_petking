<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2023-12-30
  Time: PM 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.sh.petking.common.Role"%>
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
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                no
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                회원아이디
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                등급
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                닉네임
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                이름
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                이메일
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                핸드폰번호
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                포인트
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                권한
            </th>
            <th scope="col" class="px-8 py-4 whitespace-nowrap">
                가입일
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="vs">
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <td class="px-8 py-6 font-medium text-gray-900">${vs.index + 1}</td>
                <td class="px-8 py-6">
                        ${user.id}
                </td>
                <td class="px-8 py-6">
                        ${user.userGrade.name}
                </td>
                <td class="px-8 py-6">
                        ${user.nickname}
                </td>
                <td class="px-8 py-6">
                        ${user.name}
                </td>
                <td class="px-8 py-6">
                        ${user.email}
                </td>
                <td class="px-8 py-6">
                        ${user.phone}
                </td>
                <td class="px-8 py-6">
                        ${user.resultPoint}
                </td>
                <td class="px-8 py-6">
                    <select data-id="${user.id}" data-reg-date="${user.regDate}" class="user-role bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                        <option value="U" ${user.role == Role.U ? "selected" : ""}>일반</option>
                        <option value="A" ${user.role == Role.A ? "selected" : ""}>관리자</option>
                    </select>
                </td>
                <td class="px-8 py-6">
                    <fmt:parseDate value="${user.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                    <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form name="userRoleUpdateFrm" action="${pageContext.request.contextPath}/admin/updateUserRole" method="post">
        <input type="hidden" name="id">
        <input type="hidden" name="role">
    </form>

    <div class="flex justify-center mt-8">
        <nav aria-label="Page navigation example">
            <ul class="flex items-center -space-x-px h-8 text-sm">
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/admin/adminUpdate.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    