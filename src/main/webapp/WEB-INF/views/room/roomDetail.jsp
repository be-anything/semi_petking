<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mt-10 mb-10">
    <div>
        <div class="py-5 px-5">
            <div class="px-4 sm:px-0">
                <h3 class="text-2xl font-semibold leading-7 text-black">${room.roomName}</h3>
            </div>
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-center">
                <%--  첨부 사진이 여러개 있다면 첫번째 사진만 긁어와서 출력함 ${room.roomAttachs[0].roomAttachRenamedName}
                -> room.roomRenamedImg 대표사진으로 수정--%>
                <c:if test="${!empty room.roomRenamedImg}">
                    <img src="${pageContext.request.contextPath}/upload/room/${room.roomRenamedImg}">
                </c:if>
                    <c:if test="${empty room.roomRenamedImg}">
                        <img class="w-[300px]" src="../images/room/room_default_img.jpg" alt="대표이미지없을경우기본이미지사용">
                    </c:if>
                </div>
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">객실 소개</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${room.roomIntro}</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">기본 인원</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${room.roomDefaultPerson} 명</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">최대 인원</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${room.roomMaximumPerson} 명</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">기본 요금</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${room.roomDefaultFee} 원</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">초과 요금</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${room.roomOverFee} 원</dd>
                        </div>

                        <c:if test="${room.roomAttachs != null}">
                            <c:forEach items="${room.roomAttachs}" var="attach">
                            <%--                                <p>11111111111111111111111111111111111111</p>--%>
                            <%--                            <p>${pageContext.request.contextPath}/upload/room/${attach.roomAttachRenamedName}</p>--%>
                            <img src="${pageContext.request.contextPath}/upload/room/${attach.roomAttachRenamedName}">
                            </c:forEach>
                        </c:if>
                    </dl>
                </div>

            </div>
        </div>




        <%--img slider--%>
        <div id="gallery" class="relative w-full" data-carousel="slide">
            <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
                <div class="hidden duration-700 ease-in-out" data-carousel-item>
                    <img src="" class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2" alt="">
                </div>

            </div>
            <!-- Slider controls -->
            <button type="button" class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-prev>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
            </svg>
            <span class="sr-only">Previous</span>
        </span>
            </button>
            <button type="button" class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none" data-carousel-next>
        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
            </svg>
            <span class="sr-only">Next</span>
        </span>
            </button>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/js/camp/campDetail.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>