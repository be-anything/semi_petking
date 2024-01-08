<%--
  Created by IntelliJ IDEA.
  User: min_j
  Date: 2024-01-07
  Time: PM 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="w-3/5 px-4 mx-auto">
    <h2 class="mb-4 text-xl font-bold mt-4 text-gray-900">리뷰 수정하기</h2>
    <form
            action="${pageContext.request.contextPath}/review/reviewUpdate"
            name="reviewCreateFrm" enctype="multipart/form-data" method="post">
        <div class="px-4 sm:px-0 my-4">
            <dl class="bg-gray1 p-4 border border-gray2 rounded-lg">
                <h1 class="block mb-2 text-lg font-medium text-gray-900 dark:text-white">캠핑장 한줄 후기 수정하기</h1>
                <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-2 sm:px-0">
                    <c:if test="${tags.toString().contains('청결해요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="청결해요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            청결해요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('청결해요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="청결해요"
                                   class="hidden peer tag-btn">
                            청결해요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('친절해요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="친절해요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            친절해요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('친절해요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="친절해요"
                                   class="hidden peer tag-btn">
                            친절해요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('재방문 의사있어요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="친절해요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            재방문 의사있어요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('재방문 의사있어요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="친절해요"
                                   class="hidden peer tag-btn">
                            재방문 의사있어요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('분위기 좋아요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="분위기 좋아요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            분위기 좋아요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('분위기 좋아요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="분위기 좋아요"
                                   class="hidden peer tag-btn">
                            분위기 좋아요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('매너타임')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="매너타임"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            매너타임
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('매너타임')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="매너타임"
                                   class="hidden peer tag-btn">
                            매너타임
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('주차 편해요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="주차 편해요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            주차 편해요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('주차 편해요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="주차 편해요"
                                   class="hidden peer tag-btn">
                            주차 편해요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('접근성 좋아요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="주차 편해요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            접근성 좋아요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('접근성 좋아요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="접근성 좋아요"
                                   class="hidden peer tag-btn">
                            접근성 좋아요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('산책하기 좋아요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="산책하기 좋아요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            산책하기 좋아요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('산책하기 좋아요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="산책하기 좋아요"
                                   class="hidden peer tag-btn">
                            산책하기 좋아요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('시설이 좋아요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="시설이 좋아요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            시설이 좋아요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('시설이 좋아요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="시설이 좋아요"
                                   class="hidden peer tag-btn">
                            시설이 좋아요
                        </div>
                    </c:if>
                    <c:if test="${tags.toString().contains('공기 좋아요')}">
                        <div class="options grid w-full gap-6 text-white bg-green selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            <input type="checkbox" checked name="reviewTag" value="공기 좋아요"
                                   class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                            공기 좋아요
                        </div>
                    </c:if>
                    <c:if test="${!tags.toString().contains('공기 좋아요')}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                            <input type="checkbox" name="reviewTag" value="공기 좋아요"
                                   class="hidden peer tag-btn">
                            공기 좋아요
                        </div>
                    </c:if>
                </div>
            </dl>
        </div>
        <div class="grid gap-4 sm:grid-cols-2 sm:gap-6">
            <div class="sm:col-span-2">
                <label for="reviewTitle" class="block mb-2 text-sm font-medium text-gray-900">제목</label>
                <input type="text" name="reviewTitle" id="reviewTitle" value="${review.reviewTitle}"
                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="제목을 작성하세요." required>
            </div>
            <div class="sm:col-span-2">
                <label for="userId" class="block mb-2 text-sm font-medium text-gray-900">작성자</label>
                <input type="text" name="userId" id="userId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                       value="${loginUser.id}" required readonly>
                <%--                       value="${loginMember.id}" required readonly>--%>
            </div>
            <div class="sm:col-span-2">
                <h1 class="block mb-2 text-sm font-medium text-gray-900">리뷰 사진</h1>
                <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                    <c:forEach items="${review.attachments}" var="attach" varStatus="vs">
                        <div class="flex items-center justify-center w-full relative">
                            <div class="delbtn drop-shadow-xl w-[80px] h-[80px] cursor-pointer absolute" style="background-size: cover; background-position: center; background-image: url('${pageContext.request.contextPath}/images/camp/remove.png')"></div>
                            <input hidden="hidden" value="${attach.id}">
                            <label for="reviewImg${vs.index + 1}"
                                   style="background-size: cover; background-position: center; background-image: url('${pageContext.request.contextPath}/upload/review/${attach.renamedName}')"
                                   class="delSelected flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                <input id="reviewImg${vs.index + 1}" disabled name="reviewImg" type="file" value=""
                                       class="reviewImg hidden reviewAttach"/>
                            </label>
                        </div>
                    </c:forEach>

                    <c:if test="${review.attachments.size() < 5}">
                        <c:forEach var="box" begin="${review.attachments.size()}" end="4" varStatus="vs">
                            <div class="flex items-center justify-center w-full">
                                <label for="reviewImg${vs.index + review.attachments.size()}"
                                       style="background-size: cover; background-position: center"
                                       class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                    <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                        <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                                             xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                  stroke-width="2"
                                                  d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                        </svg>
                                        <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span>
                                        </p>
                                    </div>
                                    <input id="reviewImg${vs.index + review.attachments.size()}" name="reviewImg"
                                           type="file" class="reviewImg hidden reviewAttach"/>
                                </label>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="sm:col-span-2">
                <label for="reviewContent" class="block mb-2 text-sm font-medium text-gray-900">내용</label>
                <textarea id="reviewContent" name="reviewContent"
                          rows="8" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500" required>${review.reviewContent}</textarea>
            </div>
            <input type="hidden" id="reviewId" name="reviewId" value="${review.id}">
        </div>
        <button type="submit"
                class="my-10 text-white mt-1 bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
            수정하기
        </button>
    </form>
</div>


<script src="${pageContext.request.contextPath}/js/review/reviewUpdate.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>