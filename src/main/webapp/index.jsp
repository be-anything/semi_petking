<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

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

<div class="mb-5 text-center">
    <h2 class="text-2xl font-bold mb-3 mx-auto">홍보 중인 캠핑장</h2>
    <div id="main-slider1" class="splide mx-auto">
        <div class="splide__track">
            <ul class="splide__list">
                <c:forEach items="${promotionVos}" var="promo" varStatus="vs">
                    <li class="splide__slide">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/upload/camp/${promo.camp.campRenamedImg}" alt="" />
                        </div>
                        <p>${promo.camp.campName}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<div class="mb-5 text-center">
    <h2 class="text-2xl font-bold mb-3 mx-auto">신규 캠핑장 소개</h2>
    <div id="main-slider" class="splide mx-auto">
        <div class="splide__track">
            <ul class="splide__list">
                <c:forEach items="${camps}" var="camp" varStatus="vs">
                    <li class="splide__slide">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="" />
                        </div>
                        <p>${camp.campName}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>





<script src="${pageContext.request.contextPath}/js/index.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
