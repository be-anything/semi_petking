<%--
  Created by IntelliJ IDEA.
  User: 82104
  Date: 2023-12-29
  Time: 오전 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
    .forms {
        display: none;
    }
    .active {
        display: block;
    }
</style>
<div class="flex flex-wrap justify-between items-center m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
    <ul class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 ">
        <li class="tabBtn">
            <a href="#" aria-current="page" class="inline-block p-4 rounded-t-lg text-white bg-green active px-5">자유게시판</a>
        </li>
        <li class="tabBtn">
            <a href="#" class="inline-block p-4 rounded-t-lg px-5">지식인</a>
        </li>
        <li class="tabBtn">
            <a href="#" class="inline-block p-4 rounded-t-lg px-5">동아리</a>
        </li>
    </ul>
</div>

<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">
    <div class="container mx-auto my-6">
        <div class="flex justify-start">
            <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                자유게시판
            </h1>
        </div>
        <c:if test="${loginUser != null}">
            <div class="flex justify-end">
                <button
                        type="button"
                        onclick="location.href = '${pageContext.request.contextPath}/board/boardCreate';"
                        class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">글쓰기</button>
            </div>
        </c:if>
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">번호</th>
                    <th scope="col" class="px-6 py-3">속성</th>
                    <th scope="col" class="px-6 py-3">제목</th>
                    <th scope="col" class="px-6 py-3">작성자</th>
                    <th scope="col" class="px-6 py-3">작성일</th>
                    <th scope="col" class="px-6 py-3">첨부파일</th>
                    <th scope="col" class="px-6 py-3">조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${boards}" var="board" varStatus="vs">
                    <tr class="odd:bg-white even:bg-gray-50 border-b ">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${board.id}</th>
                        <td class="px-6 py-4">
                            <p>${board.boardType == 'F' ? '자유게시판' : board.boardType == 'F' ? '자유게시판' : '동아리'}</p>
                        </td>
                        <td class="px-6 py-4">
                            <a href="${pageContext.request.contextPath}/board/boardDetail?id=${board.id}" class="hover:underline">${fn:escapeXml(board.boardTitle)}</a>
                            <c:if test="${board.commentCount ge 10}"> <!-- greater than equals 이상 -->
                                <span class="inline-flex items-center rounded-md bg-red-50 px-2 py-1 text-xs font-medium text-red-700 ring-1 ring-inset ring-red-600/10">${board.commentCount}</span>
                            </c:if>
                            <c:if test="${board.commentCount gt 0 && board.commentCount lt 10}"> <!-- 초과 미만 -->
                                <span class="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-700 ring-1 ring-inset ring-gray-600/10">${board.commentCount}</span>
                            </c:if>
                        </td>
                        <td class="px-6 py-4">${board.userId}</td>
                        <td class="px-6 py-4">
                            <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
                        </td>
                        <td class="px-6 py-4">
    <%--                        <c:if test="${board.attachCount gt 0}">--%>
                                <img class="w-[16px]" src="../images/file.png" alt="">
    <%--                        </c:if>--%>
                        </td>
                        <td class="px-6 py-4">${board.viewCount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="flex justify-center mt-6">
        <nav aria-label="Page navigation example">
            <ul class="my-8 flex items-center -space-x-px h-8 text-sm">
                <%-- 생성한 pagebar --%>
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>

<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
    <div class="container mx-auto my-6">
        <div class="flex justify-start">
            <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                지식인
            </h1>
        </div>
        <c:if test="${loginUser != null}">
            <div class="flex justify-end">
                <button
                        type="button"
                        onclick="location.href = '${pageContext.request.contextPath}/board/boardCreate';"
                        class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">글쓰기</button>
            </div>
        </c:if>
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">번호</th>
                    <th scope="col" class="px-6 py-3">속성</th>
                    <th scope="col" class="px-6 py-3">제목</th>
                    <th scope="col" class="px-6 py-3">작성자</th>
                    <th scope="col" class="px-6 py-3">작성일</th>
                    <th scope="col" class="px-6 py-3">첨부파일</th>
                    <th scope="col" class="px-6 py-3">조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${boards}" var="board" varStatus="vs">
                    <tr class="odd:bg-white even:bg-gray-50 border-b ">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${board.id}</th>
                        <td class="px-6 py-4">
                            <p>${board.boardType == 'Q' ? '지식인' : board.boardType == 'F' ? '자유게시판' : '동아리'}
                            </p>
                        </td>
                        <td class="px-6 py-4">
                            <a href="${pageContext.request.contextPath}/board/boardDetail?id=${board.id}" class="hover:underline">${fn:escapeXml(board.boardTitle)}</a>
                            <c:if test="${board.commentCount ge 10}"> <!-- greater than equals 이상 -->
                                <span class="inline-flex items-center rounded-md bg-red-50 px-2 py-1 text-xs font-medium text-red-700 ring-1 ring-inset ring-red-600/10">${board.commentCount}</span>
                            </c:if>
                            <c:if test="${board.commentCount gt 0 && board.commentCount lt 10}"> <!-- 초과 미만 -->
                                <span class="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-700 ring-1 ring-inset ring-gray-600/10">${board.commentCount}</span>
                            </c:if>
                        </td>
                        <td class="px-6 py-4">${board.userId}</td>
                        <td class="px-6 py-4">
                            <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
                        </td>
                        <td class="px-6 py-4">
                                <%--                        <c:if test="${board.attachCount gt 0}">--%>
                            <img class="w-[16px]" src="../images/file.png" alt="">
                                <%--                        </c:if>--%>
                        </td>
                        <td class="px-6 py-4">${board.viewCount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="flex justify-center mt-6">
        <nav aria-label="Page navigation example">
            <ul class="my-8 flex items-center -space-x-px h-8 text-sm">
                <%-- 생성한 pagebar --%>
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>

<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
    <div class="container mx-auto my-6">
        <div class="flex justify-start">
            <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                동아리
            </h1>
        </div>
        <c:if test="${loginUser != null}">
            <div class="flex justify-end">
                <button
                        type="button"
                        onclick="location.href = '${pageContext.request.contextPath}/board/boardCreate';"
                        class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">글쓰기</button>
            </div>
        </c:if>
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">번호</th>
                    <th scope="col" class="px-6 py-3">속성</th>
                    <th scope="col" class="px-6 py-3">제목</th>
                    <th scope="col" class="px-6 py-3">작성자</th>
                    <th scope="col" class="px-6 py-3">작성일</th>
                    <th scope="col" class="px-6 py-3">첨부파일</th>
                    <th scope="col" class="px-6 py-3">조회수</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${boards}" var="board" varStatus="vs">
                    <tr class="odd:bg-white even:bg-gray-50 border-b ">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${board.id}</th>
                        <td class="px-6 py-4">
                            <p>${board.boardType == 'Q' ? '지식인' : board.boardType == 'F' ? '자유게시판' : '동아리'}
                            </p>
                        </td>
                        <td class="px-6 py-4">
                            <a href="${pageContext.request.contextPath}/board/boardDetail?id=${board.id}" class="hover:underline">${fn:escapeXml(board.boardTitle)}</a>
                            <c:if test="${board.commentCount ge 10}"> <!-- greater than equals 이상 -->
                                <span class="inline-flex items-center rounded-md bg-red-50 px-2 py-1 text-xs font-medium text-red-700 ring-1 ring-inset ring-red-600/10">${board.commentCount}</span>
                            </c:if>
                            <c:if test="${board.commentCount gt 0 && board.commentCount lt 10}"> <!-- 초과 미만 -->
                                <span class="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-700 ring-1 ring-inset ring-gray-600/10">${board.commentCount}</span>
                            </c:if>
                        </td>
                        <td class="px-6 py-4">${board.userId}</td>
                        <td class="px-6 py-4">
                            <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd"/>
                        </td>
                        <td class="px-6 py-4">
                                <%--                        <c:if test="${board.attachCount gt 0}">--%>
                            <img class="w-[16px]" src="../images/file.png" alt="">
                                <%--                        </c:if>--%>
                        </td>
                        <td class="px-6 py-4">${board.viewCount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="flex justify-center mt-6">
        <nav aria-label="Page navigation example">
            <ul class="my-8 flex items-center -space-x-px h-8 text-sm">
                <%-- 생성한 pagebar --%>
                ${pagebar}
            </ul>
        </nav>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/board/boardList.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>