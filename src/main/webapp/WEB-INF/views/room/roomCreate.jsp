<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
    li {
        list-style: none;
    }
    .real-upload {
        display: none;
        width: 200px;
        height: 200px;
    }
    .upload {
        width: 400px;
        height: 300px;
        background-color: lightgray;
    }
    .image-preview {
        width: 500px;
        height: 400px;
        background-color: transparent;
        display: flex;
        gap: 10px;
    }

    /* 신규 추가 속성 */
    * {
        padding: 0;
        margin: 0;
    }

    .image-preview {
        flex-wrap: wrap;
    }
    .div_person1
    {
        float:left;
    }
    .div_person2
    {
        float: right;
    }

</style>
<div class="container mx-auto my-6">
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            객실 등록
        </h1>
    </div>
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10">
    <form name="roomCreateFrm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="0">
        <%--   <input type="hidden" name="campId" value="26">  아래의 히든 인풋 태그에 받아온 campId값을 대입하시면 됩니다. 현재는 7번으로 고정   --%>
        <input type="hidden" name="campId" value="${campId}">
        <div class="px-5">
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-start">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <%-- accept="image/*" 속성 지정시 이미지 파일만 선택할 수 있음.   --%>
                            <label class="block mb-2 text-xl font-medium text-gray-900 dark:text-white" for="roomImg">대표사진</label>
                        </div>
                        <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <input id="roomImg" name="roomImg" type="file" class="real-upload" accept="image/*"  multiple>
                            <div class="upload">
                                <p>대표사진</p>
                            </div>
                            <ul class="image-preview"></ul>
                        </div>
                    </dl>
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="roomName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">객실 이름</label>
                           <input type="text" id="roomName" name="roomName"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>

                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <%-- textarea -> selectbox로 수정 --%>
                            <label for="roomIntro" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">객실 타입</label>
                            <select name="roomType" id="roomType">
                                <option value="1">오토캠핑</option>
                                <option value="2">글램핑</option>
                                <option value="3">카라반</option>
                                <option value="4">룸</option>
                            </select>
                        </div>

                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="roomIntro" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">객실 소개</label>
                            <%-- textarea로 수정 필요..... --%>
                            <textarea type="text" id="roomIntro" name="roomIntro" rows="8"
                                   class="block w-full p-4 h-[200px] text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500
                                   focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white
                                   dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="객실 소개글을 작성해주세요."></textarea>

                        </div>
                        <div>
                            <div class="div_person1 px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                                <label for="roomDefaultPerson" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">기준 인원</label>
                                <input type="number" id="roomDefaultPerson" name="roomDefaultPerson"
                                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            </div>

                            <div class="div_person2 px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                                <label for="roomMaximumPerson" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">최대 인원</label>
                                <input type="number" id="roomMaximumPerson" name="roomMaximumPerson"
                                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            </div>
                        </div>

                        <div>
                            <div class="div_person1 px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                                <label for="roomDefaultPerson" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">기본 요금</label>
                                <input type="number" id="roomDefaultFee" name="roomDefaultFee"
                                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            </div>

                            <div class="div_person2 px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                                <label for="roomMaximumPerson" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">추가 요금</label>
                                <input type="number" id="roomOverFee" name="roomOverFee"
                                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            </div>
                        </div>

                    </dl>
                </div>
            </div>
            <button type="submit"
                    class="inline-flex items-center px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-black bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                등록
            </button>
        </div>
    </form>
</div>
    <script src="${pageContext.request.contextPath}/js/room/roomCreate.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>