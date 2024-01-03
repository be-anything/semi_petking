<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=378fbb0293da7feaa0ce71d64debff24"></script>

<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mt-10 mb-10">
    <div>
        <div class="py-5 px-5">
            <div class="px-4 sm:px-0">
                <h3 class="text-2xl font-semibold leading-7 text-black">${camp.campName}</h3>
                <%--<p class="mt-1 max-w-2xl text-md leading-6 text-gray-500">캠핑장 소개</p>--%>
            </div>
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-center">
                    <img src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">주소</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${camp.campAddr}</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">전화번호</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${camp.campPhone}</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">캠핑존</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">
                                <c:if test="${empty camp.rooms}">
                                    등록된 캠핑존이 없습니다
                                </c:if>
                                <c:if test="${not empty camp.rooms}">
                                    <c:forEach items="${camp.rooms}" var="room" varStatus="vs">
                                        ${room.roomName}
                                        <c:if test="${!(vs.last)}">, </c:if>
                                    </c:forEach>
                                </c:if>
                            </dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-1 sm:mt-0 flex flex-wrap">
                                <c:forEach items="${camp.campWithTags}" var="campTag">
                                    <span class="bg-green text-white text-m me-1 px-2.5 py-0.5 rounded mb-1"> #${campTag.tagName}</span>
                                </c:forEach>
                            </dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-1 sm:mt-0 mx-auto relative">
                                <button type="button"
                                        class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
                                    찜하기
                                </button>
                                <%--<i class="fa-solid fa-heart text-right"></i>--%>
                                <%-- ajax wish insert/delete -> loginUser 기능 완성되면 data-user-id 하드코딩 부분 수정 필요 --%>
                                <i data-camp-id="${camp.id}" data-user-id="goyoung12"
                                   class="fa-regular fa-heart absolute text-xl top-[7px] right-12 text-gray2 cursor-pointer"></i>
                            </dd>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-1 sm:mt-0 mx-auto">
                                <button id="updateBtn" type="button"
                                        class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
                                    수정하기
                                </button>
                                <%-- 임시 수정폼 --%>
                                <form name="campUpdateFrm" action="${pageContext.request.contextPath}/camp/campUpdate">
                                    <input type="hidden" name="id" value="${camp.id}">
                                </form>
                            </dd>
                        </div>
                    </dl>
                </div>
                <div class="h-[300px] font-medium leading-6 text-gray-900 mt-20 my-4 text-center">
                    <h1 class="mb-4 text-3xl font-extrabold leading-none tracking-tight text-gray-900"><span
                            class="underline underline-offset-3 decoration-8 decoration-green">캠핑장 소개</span></h1>
                    <p class="text-lg font-normal text-gray3 lg:text-xl mt-10">${camp.campIntro}</p>
                </div>


                <%--img slider--%>
                <div id="gallery" class="relative w-full bg-pink" data-carousel="slide">
                    <div class="relative h-56 overflow-hidden rounded-lg md:h-96">
                        <div class="hidden duration-700 ease-in-out" data-carousel-item>
                            <img src=""
                                 class="absolute block max-w-full h-auto -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2"
                                 alt="">
                        </div>

                    </div>
                    <!-- Slider controls -->
                    <button type="button"
                            class="absolute top-0 start-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
                            data-carousel-prev>
                            <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                                <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true"
                                     xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M5 1 1 5l4 4"/>
                                </svg>
                                <span class="sr-only">Previous</span>
                            </span>
                    </button>
                    <button type="button"
                            class="absolute top-0 end-0 z-30 flex items-center justify-center h-full px-4 cursor-pointer group focus:outline-none"
                            data-carousel-next>
                        <span class="inline-flex items-center justify-center w-10 h-10 rounded-full bg-white/30 dark:bg-gray-800/30 group-hover:bg-white/50 dark:group-hover:bg-gray-800/60 group-focus:ring-4 group-focus:ring-white dark:group-focus:ring-gray-800/70 group-focus:outline-none">
                            <svg class="w-4 h-4 text-white dark:text-gray-800 rtl:rotate-180" aria-hidden="true"
                                 xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                      d="m1 9 4-4-4-4"/>
                            </svg>
                        <span class="sr-only">Next</span>
                        </span>
                    </button>
                </div>


                <%-- 부가서비스 --%>
                <div class="text-xl font-medium leading-6 text-gray-900 mt-20 my-4 text-center">
                    <h1 class="mb-4 text-3xl font-extrabold leading-none tracking-tight text-gray-900"><span
                            class="underline underline-offset-3 decoration-8 decoration-green">캠핑장 부가서비스 안내</span></h1>
                </div>
                <c:if test="${empty camp.campWithServices}">
                    <div class="max-w-ful grid grid-cols-1 relative items-center justify-items-center">
                        <p class="text-lg font-normal text-gray3 lg:text-xl mt-10">등록된 부가서비스가 없습니다.</p>
                    </div>
                </c:if>
                <div class="mt-10 mb-100 grid grid-cols-2 md:grid-cols-${camp.campWithServices.size()} gap-4 justify-items-center">
                    <c:forEach items="${camp.campWithServices}" var="service">
                        <div class="w-[100px] h-[100px] max-w-full rounded-full bg-gray2 grid grid-cols-1 relative items-center justify-items-center">
                            <img class="w-[60px]"
                                 src="${pageContext.request.contextPath}/images/camp/${service.serviceImg}" alt="">
                            <div class="text-center absolute top-full my-3">${service.serviceName}</div>
                        </div>
                    </c:forEach>
                </div>

            <%-- 카카오지도 --%>
                <div class="mt-[150px]">
                    <h1 class="mb-10 text-3xl font-extrabold leading-none tracking-tight text-gray-900 text-center">
                    <span
                        class="underline underline-offset-3 decoration-8 decoration-green">찾아오는 길 </span></h1>
                    <div id="map" class="h-[500px]">
                        <div class="mx-4 my-6">
                            <input type="hidden" value="${camp.campLcLa}" name="campLcLa" id="campLcLa">
                            <input type="hidden" value="${camp.campLcLo}" name="campLcLo" id="campLcLo">
                        </div>
                    </div>
                </div>

        </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/camp/campDetail.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>