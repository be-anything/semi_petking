<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="bg-white pt-6 mx-auto w-3/5 mt-10">

    <div class="flex flex-wrap">
        <c:forEach items="${reviews}" var="review">
        <div class="w-[45%] h-fit p-5 shadow-md rounded-lg m-4">
            <div class="mb-4">
                ${review.campName} ${review.userId}님의
                캠핑로그
            </div>
            <div class="transition-all duration-300 cursor-pointer filter grayscale hover:grayscale-0">
                <a href="${pageContext.request.contextPath}/review/reviewDetail?id=${review.id}">
                    <c:if test="${review.renamedName == null}">
                        <div class="w-full h-[50px] bg-gray1 text-center pt-2.5 rounded-md hover:bg-gray2">리뷰 자세히보기</div>
                    </c:if>
                    <c:if test="${review.renamedName != null}">
                        <img class="rounded-lg" src="${pageContext.request.contextPath}/upload/review/${review.renamedName}" alt="image description">
                    </c:if>
                </a>
            </div>
            <div class="flex justify-between items-center">
            <div class="block mb-2 text-md font-medium text-gray-900 dark:text-white mt-4">${review.reviewTitle}</div>
            <div class="rounded-md bg-white mb-2 mt-4 text-xs font-medium text-black block text-md font-medium">조회수 : ${review.viewCount}</div>
            </div>
            <ul class="mt-1">
                <c:forEach items="${review.tags}" var="tag">
                    <li class="relative inline-flex items-center justify-center p-0.5 mb-1 me-1 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-red-200 via-red-300 to-yellow-200 group-hover:from-red-200 group-hover:via-red-300 group-hover:to-yellow-200 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-red-100 dark:focus:ring-red-400">
                        <span class="relative px-4 py-1.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        #${tag}
                        </span>
                    </li>
                </c:forEach>
            </ul>
            <div class="text-xs flex justify-end">
                <fmt:parseDate value="${review.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                <fmt:formatDate value="${regDate}" pattern="yyyy/MM/dd"/>
            </div>
        </div>
    </c:forEach>
    </div>
</div>

<div class="flex justify-center mt-12 mb-24">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            ${pagebar}
        </ul>
    </nav>
</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>