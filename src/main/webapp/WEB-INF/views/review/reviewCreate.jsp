<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/>

<div class="w-3/5 px-4 mx-auto mr-48">
    <h2 class="mb-4 text-xl font-bold mt-4 text-gray-900">리뷰 작성하기</h2>
    <form
            action="${pageContext.request.contextPath}/review/reviewCreate"
            name="reviewCreateFrm" enctype="multipart/form-data" method="post">
        <div class="px-4 sm:px-0 my-4">
            <dl class="bg-gray1 p-4 border border-gray2 rounded-lg">
                <h1 class="block mb-2 text-lg font-medium text-gray-900 dark:text-white">캠핑장 이용은 어떠셨나요?</h1>
                <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-2 sm:px-0">
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="청결해요"
                               class="hidden peer tag-btn">
                        청결해요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="친절해요"
                               class="hidden peer tag-btn">
                        친절해요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="재방문 의사있어요"
                               class="hidden peer tag-btn">
                        재방문 의사있어요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="분위기 좋아요"
                               class="hidden peer tag-btn">
                        분위기 좋아요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="매너타임"
                               class="hidden peer tag-btn">
                        매너타임
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="주차 편해요"
                               class="hidden peer tag-btn">
                        주차 편해요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="접근성 좋아요"
                               class="hidden peer tag-btn">
                        접근성 좋아요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="산책하기 좋아요"
                               class="hidden peer tag-btn">
                        산책하기 좋아요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="시설이 좋아요"
                               class="hidden peer tag-btn">
                        시설이 좋아요
                    </div>
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-2">
                        <input type="checkbox" name="reviewTag" value="공기 좋아요"
                               class="hidden peer tag-btn">
                        공기 좋아요
                    </div>
                </div>
            </dl>
        </div>
        <div class="grid gap-4 sm:grid-cols-2 sm:gap-6">
            <div class="sm:col-span-2">
                <label for="reviewTitle" class="block mb-2 text-sm font-medium text-gray-900">제목</label>
                <input type="text" name="reviewTitle" id="reviewTitle"
                       onclick="'${loginUser.id}' || alert('로그인 후 문의 작성하세요1.(임시)');"
                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="제목을 작성하세요." required>
            </div>
            <div class="sm:col-span-2">
                <label for="userId" class="block mb-2 text-sm font-medium text-gray-900">작성자</label>
                <input type="text" name="userId" id="userId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                       value="${loginUser.id}" required>
                <%--                       value="${loginMember.id}" required readonly>--%>
            </div>
            <div class="sm:col-span-2">
                <h1 class="block mb-2 text-sm font-medium text-gray-900">리뷰 사진</h1>
                <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                    <div class="flex items-center justify-center w-full">
                            <label for="reviewImg1"
                                   style="background-size: cover; background-position: center"
                                   class="flex flex-col items-center justify-center w-full h-48 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
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
                                <input id="reviewImg1" name="reviewImg"
                                       type="file" class="reviewImg hidden reviewAttach"/>
                            </label>
                    </div>
                    <div class="flex items-center justify-center w-full">
                            <label for="reviewImg2"
                                   style="background-size: cover; background-position: center"
                                   class="flex flex-col items-center justify-center w-full h-48 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
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
                                <input id="reviewImg2" name="reviewImg"
                                       type="file" class="reviewImg hidden reviewAttach"/>
                            </label>
                    </div>
                    <div class="flex items-center justify-center w-full">
                            <label for="reviewImg3"
                                   style="background-size: cover; background-position: center"
                                   class="flex flex-col items-center justify-center w-full h-48 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
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
                                <input id="reviewImg3" name="reviewImg"
                                       type="file" class="reviewImg hidden reviewAttach"/>
                            </label>
                    </div>
                    <div class="flex items-center justify-center w-full">
                            <label for="reviewImg4"
                                   style="background-size: cover; background-position: center"
                                   class="flex flex-col items-center justify-center w-full h-48 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
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
                                <input id="reviewImg4" name="reviewImg"
                                       type="file" class="reviewImg hidden reviewAttach"/>
                            </label>
                    </div>
                    <div class="flex items-center justify-center w-full">
                            <label for="reviewImg5"
                                   style="background-size: cover; background-position: center"
                                   class="flex flex-col items-center justify-center w-full h-48 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
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
                                <input id="reviewImg5" name="reviewImg"
                                       type="file" class="reviewImg hidden reviewAttach"/>
                            </label>
                    </div>
                </div>
            </div>
            <div class="sm:col-span-2">
                <label for="reviewContent" class="block mb-2 text-sm font-medium text-gray-900">내용</label>
                <textarea id="reviewContent" name="reviewContent"
                          onclick="'${loginUser.id}' || alert('로그인 후 문의 작성하세요2.(임시)');"
                          rows="8" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500" placeholder="내용을 작성하세요." required></textarea>
            </div>
            <input type="hidden" name="campId" value="${campId}">
        </div>
        <button type="submit"
                class="text-white mt-1 bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
            등록
        </button>
    </form>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/review/reviewCreate.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>