<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="mx-auto max-w-6xl mt-20">

<form>
    <ul>
        <li>
            <button type="button" class="text-black hover:text-white border border-gray2 hover:bg-black font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">오토캠핑</button>
            <button type="button" class="text-black hover:text-white border border-gray2 hover:bg-black font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">카라반</button>
            <button type="button" class="text-black hover:text-white border border-gray2 hover:bg-black font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">글램핑</button>
        </li>
    </ul>


    <label for="default-search" class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">검색</label>
    <div class="relative">
        <div class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
            </svg>
        </div>
        <input type="search" id="default-search" class="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg focus:ring-salmon focus:border-salmon" placeholder="키워드를 입력하세요..." required>
        <button type="submit" class="text-black absolute end-2.5 bottom-2.5 bg-gray2 hover:bg-black hover:text-white focus:outline-none font-medium rounded-lg text-sm px-4 py-2">검색하기</button>
    </div>
</form>
</div>


<c:forEach items="${camps}" var="camp" varStatus="vs">
    <div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mt-5 mb-5 hover:drop-shadow">
        <div class="w-full flex items-center m-8">
            <img class="w-80" src="${pageContext.request.contextPath}/images/camp/${camp.campImg}" />
            <div class="w-full ml-10">
                <span class="inline-flex items-center rounded-full bg-pink px-2 py-1 text-xs font-medium text-white ring-1 ring-inset ring-gray-500/10">리뷰 ${camp.reviewCount}</span>
                <span class="inline-flex items-center rounded-full bg-pink px-2 py-1 text-xs font-medium text-white ring-1 ring-inset ring-gray-500/10">찜 ${camp.wishCount}</span>
                <div class="w- text-black font-semibold text-2xl mt-5 flex items-center place-content-between relative">
                        <span>${camp.campName}</span>
<%--                        <i class="fa-solid fa-heart text-right"></i>--%>
                        <i class="fa-regular fa-heart absolute top-0 right-10 font-[90px]"></i>
                </div>
                <div class="text-black text-base font-normal mt-5">
                    <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑장 소개</span>
                    <span>${camp.campIntro}</span>
                </div>
                <div class="text-black text-base font-normal mt-5">
                    <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">찾아오는 길</span>
                    ${camp.campAddr}
                </div>
                <div class="text-black text-base font-normal mt-5">
                    <i class="pt-1 pb-1 pl-1.5 pr-1.5 fa-solid fa-phone bg-gray3 text-xs text-white rounded-full mr-2"></i>
                    <span class="pt-2 pb-1">${camp.campPhone}</span>
                </div>
                <div class="flex flex-wrap items-center tag-section w-full h-10 mt-5">
                    <c:forEach items="${camp.campWithTags}" var="campTag">
                        <span class="bg-green text-white text-m me-1 px-2.5 py-0.5 rounded mb-1"> #${campTag.tagName}</span>
                    </c:forEach>
                </div>
<%--                <div class="pt-2 pb-2 w-40 bg-neutral-500 rounded-2xl text-center text-white text-base font-semibold mt-5">상세보기</div>--%>
            </div>
        </div>
    </div>
</c:forEach>

<div class="flex justify-center mt-6 mb-20">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            ${pagebar}
        </ul>
    </nav>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>