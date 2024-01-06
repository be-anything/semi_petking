<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="w-2/3 ml-auto mr-24 mb-8">
    <div class="container mx-auto my-6">
        <div class="flex justify-start">
            <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                내가 작성한 리뷰
            </h1>
        </div>
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">번호</th>
                    <th scope="col" class="px-6 py-3">제목</th>
                    <th scope="col" class="px-6 py-3">작성자 </th>
                    <th scope="col" class="px-6 py-3">등록일</th>
                </tr>
                </thead>
                <tbody>
                <%--  작성된 문의 내역 출력하는 구간. forEach로 반복 --%>
                <c:forEach items="${reviews}" var="review" varStatus="vs">
                    <div class="odd:bg-white even:bg-gray-50 border-b ">
                        <tr>
                            <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${review.id}</th>
                            <td class="px-6 py-4">
                                <a href="${pageContext.request.contextPath}/review/reviewDetail?id=${review.id}"
                                   class="hover:underline">${fn:escapeXml(review.reviewTitle)}</a>
                                    <%-- 댓글 수 노출하기 --%>
                                    <%--                            <c:if test="${review.askCommentCount}">--%>
                            </td>
                            <td class="px-6 py-4">${review.userId}</td>
                            <td class="px-6 py-4">
                                <fmt:parseDate value="${review.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/></td>
                        </tr>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="flex justify-center mt-6">
        <nav aria-label="Page navigation example">
            <ul class="flex items-center -space-x-px h-8 text-sm">
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>