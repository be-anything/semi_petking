<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="bg-white">
    <div class="pt-6">
        <!-- Image gallery -->
        <c:if test="${review.attachments.size() != 0}">
            <div class="mx-auto mt-6 max-w-2xl sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-${review.attachments.size()} h-[350px] lg:gap-x-4 lg:px-8">
                <c:forEach items="${review.attachments}" var="attach">
                    <div class="aspect-h-4 aspect-w-3 hidden overflow-hidden rounded-lg lg:block">
                        <img src="${pageContext.request.contextPath}/upload/review/${attach.renamedName}" alt="Two each of gray, white, and black shirts laying flat." class="h-full w-full object-cover object-center">
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <!-- Product info -->
        <div class="mx-auto max-w-2xl px-4 pt-10 sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-3 lg:grid-rows-[auto,auto,1fr] lg:gap-x-8 lg:px-8 lg:pt-16">
            <div class="lg:col-span-2 lg:border-r lg:border-gray-200 lg:pr-8 flex">
                <h1 class="text-2xl w-[85%] font-bold tracking-tight text-gray-900 sm:text-3xl">${review.reviewTitle}</h1>
                <span class="inline-flex items-center rounded-md bg-yellow-50 px-2 py-1 text-xs font-medium text-yellow-800 ring-1 ring-inset ring-yellow-600/20">조회수 : ${review.viewCount}</span>
            </div>

            <!-- Options -->
            <div class="mt-4 lg:row-span-3 lg:mt-0 flex flex-wrap content-between justify-end">
                <div class="mt-4">
                    <a href="${pageContext.request.contextPath}/camp/campDetail?id=${review.campId}" class="hover:underline"><span class="text-2xl tracking-tight text-gray-900">${review.campName}</span></a> 한줄 후기
                    <div class="">
                        <ul role="list" class="list-disc space-y-2 text-sm">
                            <c:forEach items="${tags}" var="tag">
                                <li class="relative inline-flex items-center justify-center p-0.5 mb-2 me-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-red-200 via-red-300 to-yellow-200 group-hover:from-red-200 group-hover:via-red-300 group-hover:to-yellow-200 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-red-100 dark:focus:ring-red-400">
                                    <span class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                    #${tag}
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <%--리뷰 수정 삭제 해야댐~! --%>
                <c:if test="${loginUser.id == review.userId}">
                    <div class="">
                        <ul class="space-y-2 text-sm">
                            <li class="relative inline-flex items-center justify-center">
                                <button type="button" onclick="location.href='${pageContext.request.contextPath}/review/reviewUpdate?id=${review.id}'" class="text-white bg-gradient-to-r from-teal-400 via-teal-500 to-teal-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-teal-300 dark:focus:ring-teal-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
                                    리뷰 수정하기
                                </button>
                            </li>
                            <li class="relative inline-flex items-center justify-center">
                                <button type="button" onclick="if(confirm('정말 삭제하시겠습니까?')){document.userReviewDeleteFrm.submit();}" class="delbtn text-white bg-gradient-to-r from-red-400 via-red-500 to-red-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 shadow-lg shadow-red-500/50 dark:shadow-lg dark:shadow-red-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
                                    리뷰 삭제하기
                                    <form action="${pageContext.request.contextPath}/user/userReviewDelete" method="post" name="userReviewDeleteFrm">
                                        <input type="hidden" name="id" value="${review.id}">
                                    </form>
                                </button>
                            </li>
                        </ul>
                    </div>
                </c:if>
            </div>

            <div class="py-10 lg:col-span-2 lg:col-start-1 lg:border-r lg:border-gray-200 lg:pb-16 lg:pr-8 lg:pt-6">
                <!-- Description and details -->
                <div>
                    <div class="space-y-6">
                        <p class="text-base text-gray-900 h-72 border border-gray2 p-2">${review.reviewContent}</p>
                    </div>
                </div>
                <div class="mt-5 flex justify-end">
                    <h3 class="font-medium text-gray-900 mr-8">작성일</h3>
                    <p class="text-gray-400"><span class="text-gray-600">
                       <fmt:parseDate value="${review.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                        <fmt:formatDate value="${regDate}" pattern="yyyy년 MM월 dd일 (HH:mm)"/>
                    </p>
                </div>
                <div class="mt-5 flex justify-end">
                    <h3 class="font-medium text-gray-900 mr-8">작성자</h3>
                    <p class="text-gray-400"><span class="text-gray-600">
                       ${review.userId}
                    </p>
                </div>
            </div>


        </div>
        <div class="mx-auto mt-0 max-w-2xl lg:grid lg:max-w-7xl p-8 mb-12">
            <%-- 사용자 댓글 출력 --%>
            <div class="relative border border-gray-200 overflow-x-auto sm:rounded-lg mb-2">
                <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <caption class="p-5 text-lg font-semibold text-left rtl:text-right text-gray-900 bg-white dark:text-white dark:bg-gray-800">
                        총 ${review.reviewComments.size()}개의 댓글이 달렸습니다.
                    </caption>
                    <tbody>
                    <c:forEach items="${review.reviewComments}" var="comment">
                        <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                            <th scope="row" class="w-3/5 px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                ${comment.content}
                            </th>
                            <td class="px-6 py-4">
                                ${comment.userId}
                            </td>
                            <td class="px-6 py-4">
                            <td class="px-6 py-4">
                                <fmt:parseDate value="${comment.regDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
                            </td>
                            <c:if test="${comment.userId == loginUser.id}">
                                <td class="px-6 py-4 text-right">
                                    <form name="commentDeleteFrm" action="${pageContext.request.contextPath}/review/reviewCommentDelete" method="post">
                                        <input type="hidden" name="id" value="${comment.id}">
                                        <input type="hidden" name="reviewId" value="${review.id}">
                                        <button type="submit" class="font-medium text-red hover:underline">삭제</button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>

            <c:if test="${loginUser != null || loginCamp != null}">
                <form method="post" name="commentCreateFrm" action="${pageContext.request.contextPath}/review/reviewCommentCreate">
                    <div class="w-full mb-4 border border-gray-200 rounded-lg bg-gray-50 dark:bg-gray-700 dark:border-gray-600">
                        <div class="px-4 py-2 bg-white rounded-t-lg dark:bg-gray-800">
                            <label for="content" class="sr-only"></label>
                            <textarea id="content" name="content" rows="4" class="w-full px-0 text-sm text-gray-900 bg-white border-0 dark:bg-gray-800 focus:ring-0 dark:text-white dark:placeholder-gray-400" placeholder="댓글을 작성해주세요" required></textarea>
<%--                            ID--%>
<%--                            USER_ID--%>
<%--                            REVIEW_ID--%>
<%--                            CONTENT--%>
<%--                            REG_DATE--%>

                        </div>
                        <input type="hidden" name="userId" value="${loginUser.id}">
                        <input type="hidden" name="reviewId" value="${review.id}">
                        <div class="flex items-center justify-between px-3 py-2 border-t dark:border-gray-600">
                            <button type="submit" class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white bg-black rounded-lg hover:focus:ring-4 hover:focus:ring-gray2 hover:bg-[#000]">
                                댓글 등록
                            </button>
                        </div>
                    </div>
                </form>
            </c:if>




        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/review/reviewDetail.js"></script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>