<%--
  Created by IntelliJ IDEA.
  User: 82104
  Date: 2024-01-02
  Time: 오후 6:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="xl:container p-8">
    <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${board.boardTitle}</h5>
        <p class="mb-3 font-normal text-gray-500">${board.userId}</p>
        <p class="mb-3 font-normal text-gray-700">${board.boardContent}</p>
        <div class="text-sm mt-2 font-medium text-gray-400">
            조회수 <span>${board.viewCount}</span>
        </div>
        <div class="text-sm mt-2 font-medium text-gray-400">
            작성일
            <span>
            <fmt:parseDate value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
        </span>
        </div>
        <%-- 작성자 본인과 관리자에게만 노출 --%>
            <div class="flex justify-end">
                <c:if test="${loginUser.id eq board.userId}">
                <button
                        type="button"
                        onclick="location.href = '${pageContext.request.contextPath}/board/boardUpdate?id=${board.id}';"
                        class=" py-2.5 px-4 text-xs font-medium text-indigo-600 hover:bg-indigo-200 ms-30">
                    수정
                </button>
                <button type="button"
                        onclick="confirm('정말 삭제하시겠습니까? 😯') && document.boardDeleteFrm.submit()"
                        class=" py-2.5 px-4 text-xs font-medium text-red-600 hover:bg-red-200 ms-30">
                    삭제
                </button>
                </c:if>
                <c:if test="${loginUser.clubId eq null && board.boardType == 'C' && loginUser != null}">
                    <button type="button"
                            onclick="confirm('동아리를 가입 하시겠습니까? 😎') && document.clubRequestFrm.submit()"
                            class=" py-2.5 px-4 text-xs font-medium text-sky-600 hover:bg-blue-200 ms-30">
                        가입 하기
                    </button>
                </c:if>
            </div>
            <form
                    action="${pageContext.request.contextPath}/board/boardDelete"
                    method="post"
                    name="boardDeleteFrm">
                <input type="hidden" name="id" value="${board.id}">
            </form>
            <form
                    action="${pageContext.request.contextPath}/club/clubRequest"
                    method="post"
                    name="clubRequestFrm">
                <input type="hidden" name="id" value="${board.id}">
            </form>
    </div>

    <!-- 댓글 폼 -->
    <div class="w-full my-2">
        <form
                name="boardCommentCreateFrm"
                action="${pageContext.request.contextPath}/board/boardCommentCreate"
                method="post">
            <input type="hidden" name="boardId" value="${board.id}">
            <input type="hidden" name="userId" value="${loginUser.id}">
            <div class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50">
                <div class="px-4 py-2 bg-white rounded-t-lg">
                    <label for="content" class="sr-only">댓글 작성하기</label>
                    <textarea id="content"
                              name="content"
                              rows="4"
                              onclick="'${loginUser.id}' || alert('로그인후 댓글을 작성하세요');"
                              class="resize-none w-full px-0 text-sm text-gray-900 bg-white border-0"
                              placeholder="댓글을 작성하세요" required></textarea>
                </div>
                <div class="flex items-center justify-end px-3 py-2 border-t">
                    <button type="submit"
                            class=" py-2.5 px-4 text-xs font-medium text-purple-600 hover:bg-purple-200 ms-30">
                        댓글 등록
                    </button>
                </div>
            </div>
        </form>
    </div>

    <!-- 댓글 테이블 -->
    <div class="relative my-8 shadow-xl sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500">
            <tbody>
            <c:forEach items="${board.comments}" var="comment" varStatus="vs">
                    <%-- 댓글 tr --%>
                    <tr class="bg-white border-b hover:bg-gray-50">
                        <td scope="row" colspan="2" class="w-4/6 px-6 py-4 font-medium text-gray-800">
                            <sub class="text-gray-500">${comment.userId}</sub>
                            <sub class="text-gray-400">
                                <fmt:parseDate value="${comment.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
                            </sub>
                            <p class="mt-2">
                                    ${comment.content}
                            </p>
                        </td>
                        <td class="px-6 py-4">
                            <c:if test="${(loginUser.id eq comment.userId || loginUser.role eq Role.A) && loginUser.id != null}">
                                <div class="flex">
                                    <a href="javascript:confirm('정말 삭제하시겠습니까? 😲') && document.boardCommentDeleteFrm${comment.id}.submit();"
                                        class=" py-2.5 px-4 text-xs font-medium text-red-600 hover:bg-red-200 ms-30">
                                        삭제하기</a>
                                </div>
                                <form name="boardCommentDeleteFrm${comment.id}" action="${pageContext.request.contextPath}/board/boardCommentDelete" method="post">
                                    <input type="hidden" name="id" value="${comment.id}">
                                    <input type="hidden" name="boardId" value="${board.id}">
                                </form>
                            </c:if>
                        </td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/board/boardDetail.js"></script>