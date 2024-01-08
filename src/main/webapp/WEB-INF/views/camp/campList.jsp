<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="mx-auto max-w-6xl mt-20">
    <%--검색하기--%>
    <form name="campSearchFrm" action="${pageContext.request.contextPath}/camp/campList" method="get">
        <div class="mx-auto max-w-6xl w-fit">
            <div class="bg-white flex">
                <select id="search-type" name="search-type" class="text-black border border-gray2 rounded-lg px-5">
                    <option value="" disabled selected>선택</option>
                    <option value="camp_name" ${param['search-type'] eq 'camp_name' ? 'selected' : ''}>캠핑장 이름</option>
                    <option value="camp_intro" ${param['search-type'] eq 'camp_intro' ? 'selected' : ''}>캠핑장 소개</option>
                    <option value="camp_addr" ${param['search-type'] eq 'camp_addr' ? 'selected' : ''}>캠핑장 주소</option>
                </select>
                <div class="ml-1">
                    <input type="search" id="search-keyword" name="search-keyword" value="${param['search-keyword']}" placeholder="검색어를 입력하세요..." class="text-black border border-gray2 font-medium rounded-lg text-sm py-2.5 px-5 w-[500px]" >
                    <button type="submit" class="search-btn text-black end-2.5 bottom-2.5 bg-gray2 hover:bg-black hover:text-white focus:outline-none font-medium rounded-lg text-sm px-4 py-3">검색하기</button>
                </div>
            </div>
        </div>
        <div class="px-4 py-6 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
            <div class="w-3/4 mx-auto px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                <c:forEach items="${tags}" var="tag" varStatus="vs">
                    <c:if test="${tagName != tag.name}">
                        <div class="options grid w-full gap-6 text-gray-500 border-2 cursor-pointer border-gray-200 rounded-lg inline-flex items-center justify-center w-full p-3">
                            <input type="checkbox" name="tagName" value="${tag.name}" class="hidden peer tag-btn">
                            #${tag.name}
                        </div>
                    </c:if>
                    <c:if test="${tagName eq tag.name}">
                        <div class="options selected grid w-full gap-6 text-white border-2 bg-green border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-3">
                            <input type="checkbox" name="tagName" value="${tag.id}" class="hidden peer tag-btn">
                            #${tag.name}
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </form>
</div>

<div class="mx-auto max-w-6xl mt-10" >
    <h1 class="text-black text-xl">총 <strong>${totalCount}</strong>개의 캠핑장이 검색되었습니다.</h1>
</div>
<c:forEach items="${camps}" var="camp" varStatus="vs">
    <div class="flex justify-between items-center mx-auto max-w-6xl h-fit rounded-lg bg-gray1 mt-5 mb-5 hover:drop-shadow">
        <div class="w-full flex items-center m-8 h-fit">
            <img class="w-80" src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" />
            <div class="w-full ml-10">
                <span class="inline-flex items-center rounded-full bg-pink px-2 py-1 text-xs font-medium text-white ring-1 ring-inset ring-gray-500/10">리뷰 ${camp.reviewCount}</span>
                <span class="inline-flex items-center rounded-full bg-pink px-2 py-1 text-xs font-medium text-white ring-1 ring-inset ring-gray-500/10">찜 ${camp.wishCount}</span>
                <div class="w- text-black font-semibold text-2xl mt-5 flex items-center place-content-between relative">
                        <span id="campBtn" data-id="${camp.id}" class="hover:underline cursor-pointer">${camp.campName}</span>

                    <%--<i class="fa-solid fa-heart text-right"></i>--%>
                    <%-- ajax wish insert/delete -> loginUser 기능 완성되면 data-user-id 하드코딩 부분 수정 필요 --%>
                    <c:if test="${loginUser != null}">
<%--                        <c:if test="${wishes.toString().contains('campId=' + camp.id)}">--%>
                        <c:if test="${camp.wish}">
                        <i data-camp-id="${camp.id}" data-user-id="${loginUser.id}" class="z-50 wish-btn fa-solid fa-heart absolute bottom-full right-10 text-5xl text-red cursor-pointer"></i>
                        </c:if>
                        <c:if test="${!camp.wish}">
                            <i data-camp-id="${camp.id}" data-user-id="${loginUser.id}" class="wish-btn fa-regular fa-heart absolute bottom-full right-10 text-5xl text-gray2 cursor-pointer"></i>
                        </c:if>
                    </c:if>
                    <c:if test="${loginUser == null}">
                        <i class="fa-regular cursor-not-allowed fa-heart absolute bottom-full right-10 text-5xl text-gray2"></i>
                    </c:if>
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
                <c:if test="${camp.tagNameList != null}">
                    <div class="flex flex-wrap items-center tag-section w-full h-10 mt-5">
                        <c:forEach items="${camp.tagNameList}" var="campTag">
                            <c:if test="${campTag != tagName}">
                                <button class="relative inline-flex items-center justify-center p-0.5 mb-2 me-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-teal-300 to-lime-300 group-hover:from-teal-300 group-hover:to-lime-300 dark:text-white dark:hover:text-gray-900 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-lime-800">
                                <span class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                                    #${campTag}
                                </span>
                                </button>
                            </c:if>
                            <c:if test="${campTag == tagName}">
                                <button type="button" class="text-gray-900 bg-gradient-to-r from-teal-200 to-lime-200 hover:bg-gradient-to-l hover:from-teal-200 hover:to-lime-200 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-teal-700 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
                                    #${campTag}
                                </button>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
<%--                <div class="pt-2 pb-2 w-40 bg-neutral-500 rounded-2xl text-center text-white text-base font-semibold mt-5">상세보기</div>--%>
            </div>
        </div>
    </div>
</c:forEach>
<%-- campDetail로 이동하기 위한 form --%>
<form name="campDetailFrm" action="${pageContext.request.contextPath}/camp/campDetail">
    <input type="hidden" name="id">
</form>

<div class="flex justify-center mt-6 mb-20">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            ${pagebar}
        </ul>
    </nav>
</div>
<script src="${pageContext.request.contextPath}/js/camp/campList.js"></script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>