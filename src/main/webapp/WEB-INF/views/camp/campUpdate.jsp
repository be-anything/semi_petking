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
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg mt-10">
    <ul class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 ">
        <li class="tabBtn">
            <a href="#" aria-current="page" class="inline-block p-4 rounded-t-lg text-white bg-green active px-5">기본 정보</a>
        </li>
        <li class="tabBtn">
            <a href="#" class="inline-block p-4 rounded-t-lg px-5">부가 정보</a>
        </li>
        <li class="tabBtn">
            <a href="#" class="inline-block p-4 rounded-t-lg px-5">캠핑존 정보</a>
        </li>
    </ul>
</div>
<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">
    <form name="campUpdateFrm" class="">
        <input type="hidden" name="id" value="${camp.id}">
        <div class="px-5">
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-start">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <label class="block mb-2 text-xl font-medium text-gray-900 dark:text-white" for="campImg">대표사진</label>
                            <input class="block w-full text-sm cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400" aria-describedby="user_avatar_help" id="campImg" name="campImg" type="file">
                        </div>
                        <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <img id="img-view" class="img-view" src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="">
                        </div>
                    </dl>
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 이름</label>
                            <input type="text" id="campName" name="campName"
                                   value="${camp.campName}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campIntro" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 소개</label>
                            <%-- textarea로 수정 필요..... --%>
                            <input type="text" id="campIntro" name="campIntro"
                                   value="${camp.campIntro}"
                                   class="block w-full p-4 h-[200px] text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campAddr" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 주소</label>
                            <input type="text" id="campAddr" name="campAddr"
                                   value="${camp.campAddr}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campPhone" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 전화번호</label>
                            <input type="text" id="campPhone" name="campPhone"
                                   value="${camp.campPhone}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <div></div>
                            <button id="updateBtn" type="submit" class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">수정하기</button>
                        </div>
                    </dl>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
    <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-10 sm:px-0 items-start">
        <form name="campDetailUpdateFrm">
            <input type="hidden" name="campId" value="${camp.id}">
            <div class="px-4 sm:px-0">
                <dl class="mt-10 mx-4">
                    <h1 class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">부가서비스</h1>
                    <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                        <c:forEach items="${campServices}" var="service">
                            <c:if test="${camp.campWithServices.toString().contains(service.name)}">
                                <div class="options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" checked name="serviceId" value="${service.id}" class="hidden peer tag-btn options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5" >
                                        ${service.name}
                                </div>
                            </c:if>
                            <c:if test="${!camp.campWithServices.toString().contains(service.name)}">
                                <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" name="serviceId" value="${service.id}" class="hidden peer tag-btn" >
                                        ${service.name}
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <h1 class="block mb-2 mt-5 text-xl font-medium text-gray-900 dark:text-white">캠핑장 소개 사진</h1>
                    <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                        <c:forEach items="${camp.campAttaches}" var="campAttach" varStatus="vs">
                            <div class="flex items-center justify-center w-full">
                                <label for="campImg${vs.index}"
                                       style="background-size: cover; background-position: center; background-image: url('${pageContext.request.contextPath}/upload/camp/${campAttach.campAttachRenamedName}')"
                                       class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                    <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                        <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                        </svg>
                                        <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span></p>
                                    </div>
                                    <input id="campImg${vs.index}" name="campDetailImg" type="file" class="campImg hidden campAttach" />
                                </label>
                            </div>
                        </c:forEach>
                        <c:forEach var="box" begin="0" end="${9 - camp.campAttaches.size()}" varStatus="vs">
                            <div class="flex items-center justify-center w-full">
                                <label for="campImg${vs}"
                                       style="background-size: cover; background-position: center"
                                       class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                    <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                        <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                        </svg>
                                        <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span></p>
                                    </div>
                                    <input id="campImg${vs}" type="file" class="campImg hidden campAttach" />
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <%-- 캠핑장 태그 --%>
                    <h1 class="mt-5 block mb-2 text-xl font-medium text-gray-900 dark:text-white">태그를 선택해주세요</h1>
                    <div class="px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                        <c:forEach items="${campTags}" var="tag">
                            <c:if test="${camp.campWithTags.toString().contains(tag.name)}">
                                <div class="options grid w-full gap-6 text-white bg-light-pink selected border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" checked name="tagId" value="${tag.id}" class="hidden peer tag-btn" >
                                        ${tag.name}
                                </div>
                            </c:if>
                            <c:if test="${!camp.campWithTags.toString().contains(tag.name)}">
                                <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-5">
                                    <input type="checkbox" name="tagId" value="${tag.id}" class="hidden peer tag-btn" >
                                        ${tag.name}
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <div class="px-4 py-4 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                        <!-- 레이아웃용 빈 div -->
                        <div></div>
                        <div></div>
                        <div></div>
                        <button id="updateDetailBtn" type="button" class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">수정하기</button>
                    </div>
                </dl>
            </div>
        </form>
    </div>
</div>



<%--객실정보 수정--%>
<div class="justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
    <div class="px-4 py-6 sm:grid sm:grid-cols-1 sm:gap-10 sm:px-0 items-start">
        <form name="campRoomUpdateFrm" class="">

        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/camp/campUpdate.js"></script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>