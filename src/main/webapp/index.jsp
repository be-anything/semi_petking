<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div class="h-[600px] w-full bg-gray1">
    <div class="pt-80">
        <%--검색하기--%>
        <form name="campSearchFrm" action="${pageContext.request.contextPath}/camp/campList" method="get">
        <div class="mx-auto max-w-6xl w-fit">
            <div class="bg-white flex">
                <select id="search-type" name="search-type" required class="text-black border border-gray2 rounded-lg px-5">
                    <option value="" disabled selected>선택</option>
                    <option value="camp_name" ${param['search-type'] eq 'camp_name' ? 'selected' : ''}>캠핑장 이름</option>
                    <option value="camp_intro" ${param['search-type'] eq 'camp_intro' ? 'selected' : ''}>캠핑장 소개</option>
                    <option value="camp_addr" ${param['search-type'] eq 'camp_addr' ? 'selected' : ''}>캠핑장 주소</option>
                </select>
                <div class="ml-1">
                    <input type="search" id="search-keyword" name="search-keyword" value="${param['search-keyword']}" required placeholder="검색어를 입력하세요..." class="text-black border border-gray2 font-medium rounded-lg text-sm py-2.5 px-5 w-[500px]" >
                    <button type="submit" class="search-btn text-black end-2.5 bottom-2.5 bg-gray2 hover:bg-black hover:text-white focus:outline-none font-medium rounded-lg text-sm px-4 py-3">검색하기</button>
                </div>
            </div>
        </div>
        <div class="px-4 py-6 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
            <div class="w-3/4 mx-auto px-4 py-4 sm:grid sm:grid-cols-5 sm:gap-4 sm:px-0">
                <c:forEach items="${tags}" var="tag" varStatus="vs">
                    <div class="options grid w-full gap-6 text-gray-500 border-2 border-gray-200 rounded-lg cursor-pointer inline-flex items-center justify-center w-full p-3">
                        <input type="checkbox" name="tagId${vs.index + 1}" value="${tag.id}" class="hidden peer tag-btn">
                        #${tag.name}
                    </div>
                </c:forEach>
            </div>
        </div>
        </form>
    </div>
</div>


<link rel="stylesheet" href="https://unpkg.com/splide@3.0.0/dist/css/splide.min.css">
<style>
    .splide__slide {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 10px;
        border-radius: 8px;
        background-color: #f8f8f8;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease-in-out;
    }

    .thumbnail {
        width: 300px;
        height: 300px;
        overflow: hidden;
        cursor: pointer;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .thumbnail img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .splide__slide p {
        font-size: 20px;
        margin-top: 10px;
        color: #333;
    }

    .splide__slide:hover {
        transform: scale(1.05);
    }

    h2 {
        color: #4a5568;
    }
</style>

<div class="text-center my-24">
    <h2 class="text-2xl font-bold mb-3 mx-auto">홍보 중인 캠핑장</h2>
    <div id="main-slider1" class="splide mx-auto">
        <div class="splide__track">
            <ul class="splide__list">
                <c:forEach items="${promotionVos}" var="promo" varStatus="vs">
                    <li class="splide__slide">
                        <a href="${pageContext.request.contextPath}/camp/campDetail?id=${promo.camp.id}">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/upload/camp/${promo.camp.campRenamedImg}" alt="" />
                        </div>
                        <p>${promo.camp.campName}</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<div class="my-24 text-center">
    <h2 class="text-2xl font-bold mb-3 mx-auto">신규 캠핑장 소개</h2>
    <div id="main-slider" class="splide mx-auto">
        <div class="splide__track">
            <ul class="splide__list">
                <c:forEach items="${camps}" var="camp" varStatus="vs">
                    <li class="splide__slide">
                        <a href="${pageContext.request.contextPath}/camp/campDetail?id=${camp.id}">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="" />
                        </div>
                        <p>${camp.campName}</p>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>





<script src="${pageContext.request.contextPath}/js/index.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
