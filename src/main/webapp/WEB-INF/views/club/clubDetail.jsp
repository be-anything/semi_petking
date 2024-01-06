<%--
  Created by IntelliJ IDEA.
  User: 82104
  Date: 2024-01-05
  Time: 오후 7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="xl:container p-8">
    <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${loginUser.id}님 반가워요🤗</h5>
    </div>
</div>
<div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
    <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${club.clubName}</h5>
    <p class="mb-3 font-normal text-gray-500">${club.clubIntroTitle}</p>
    <p class="mb-3 font-normal text-gray-700">${club.clubIntroContent}</p>
<%--    <c:forEach items="${clubVo.attachments}" var="attach">--%>
<%--        <a href="${pageContext.request.contextPath}/upload/club/${clubAttach.renamedname}"--%>
<%--           download="${boardAttach.originalName}" class="flex items-center text-blue-600 hover:underline">--%>
<%--            <img src="../images/file.png" class="w-[16px] mr-1">--%>
<%--                ${clubAttach.originalName}--%>
<%--        </a>--%>
<%--    </c:forEach>--%>
<%--    <div class="text-sm mt-2 font-medium text-gray-400">--%>
<%--        조회수 <span>${club.viewCount}</span>--%>
<%--    </div>--%>
    <div class="text-sm mt-2 font-medium text-gray-400">
        작성일
        <span>
            <fmt:parseDate value="${club.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
        </span>
    </div>
    <%-- 작성자 본인과 관리자에게만 노출 --%>
<%--    <c:if test="${loginUser.id eq board.userId}">--%>
        <div class="flex justify-end">
            <button
                    type="button"
                    onclick="location.href = '${pageContext.request.contextPath}/club/clubUpdate?id=${club.id}';"
                    class="px-5 py-2.5 mt-4 mr-4 sm:mt-6 text-sm font-medium text-center text-black bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                수정
            </button>
            <button type="button"
                    onclick="confirm('정말 삭제하시겠습니까? 😯') && document.clubDeleteFrm.submit()"
                    class="px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-black bg-red-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                삭제
            </button>
        </div>
        <form
                action="${pageContext.request.contextPath}/club/clubDelete"
                method="post"
                name="clubDeleteFrm">
            <input type="hidden" name="id" value="${club.id}">
        </form>
<%--    </c:if>--%>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>