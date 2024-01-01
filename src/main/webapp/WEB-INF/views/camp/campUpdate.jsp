<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg mt-10">
    <ul class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 ">
        <li class="me-2">
            <a href="#" aria-current="page" class="inline-block p-4 text-white bg-green rounded-t-lg active px-5">기본 정보</a>
        </li>
        <li class="me-2">
            <a href="#" class="inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 hover:text-gray-300 px-5">부가 정보</a>
        </li>
        <li class="me-2">
            <a href="#" class="inline-block p-4 rounded-t-lg hover:text-gray-600 hover:bg-gray-50 hover:text-gray-300 px-5">캠핑존 정보</a>
        </li>
    </ul>
</div>
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10">
    <form action="${pageContext.request.contextPath}/camp/campUpdate" enctype="multipart/form-data" method="post">
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
                            <img src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="">
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


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>