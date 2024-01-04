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

<c:if test="${loginUser.clubId == null && loginUser != null}">
<div class="py-8 px-4 mx-auto max-w-2xl lg:py-16">
    <form name="clubMainFrm" method="post" enctype="multipart/form-data">
        <button type="button" onClick="location.href='${pageContext.request.contextPath}/club/clubCreate';" class="inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-blue bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
            동아리 생성
        </button>
        <button type="button" onClick="location.href='${pageContext.request.contextPath}/board/boardList';" class="inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-blue bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
            동아리 가입
        </button>
    </form>
</div>
</c:if>
<c:if test="${loginUser.clubId != null}">
<div class="xl:container p-8">
    <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">동아리 소개</h5>
    </div>
</div>
</c:if>
<c:if test="${loginUser == null}">
    <div class="xl:container p-8">
        <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
            <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">로그인 후 사용 가능합니다!</h5>
        </div>
    </div>
</c:if>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>