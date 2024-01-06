<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-01-03
  Time: 오후 3:06
  To change this template use File | Settings | File Templates.
  문의 리스트 작업 시작
  만약 문의내용이없다면 (getCount 등) 등록된 문의가 없습니다 출력.
  ->문의 답변이 있다면 제목 옆에 뱃지 추가.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container mx-auto my-6">
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            문의 리스트(임시)
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
            <c:forEach items="${asks}" var="ask" varStatus="vs">
                <div class="odd:bg-white even:bg-gray-50 border-b ">

                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${ask.id}</th>
                    <td class="px-6 py-4">
                        <a href="${pageContext.request.contextPath}/ask/askDetail?id=${ask.id}"
                           class="hover:underline">${fn:escapeXml(ask.askTitle)}</a>
                            <%-- 제목 옆에 답변 완료 /미완료 출력해보기 --%>
                        <c:if test="${ask.askComment eq null}">
                            <span class="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-600 ring-1 ring-inset ring-gray-500/10">답변 미완료</span>
                        </c:if>
                        <c:if test="${ask.askComment ne null}">
                            <span class="inline-flex items-center rounded-md bg-indigo-50 px-2 py-1 text-xs font-medium text-indigo-700 ring-1 ring-inset ring-indigo-700/10">답변 완료</span>
                        </c:if>

                    </td>
                    <td class="px-6 py-4">${ask.userId}</td>
                    <td class="px-6 py-4">
<%--                            ${ask.askRegDate}--%>
                                <fmt:parseDate value="${ask.askRegDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/></td>

                </div>
                </tr>
            </c:forEach>

            </tbody>
        </table>

    </div>

    <div class="flex justify-end py-10">
        <button type="button"
                onClick="location.href='${pageContext.request.contextPath}/ask/askCreate';"
                class="text-white bg-gradient-to-r from-rose-200 via-rose-300 to-rose-400 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-rose-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">문의 등록</button>
    </div>
</div>



<div class="flex justify-center mt-6">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            ${pagebar}
        </ul>
    </nav>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>