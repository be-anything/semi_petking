<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-01-02
  Time: 오전 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10">
    <form action="${pageContext.request.contextPath}/room/roomUpdate" enctype="multipart/form-data" method="post">
        <input type="hidden" name="id" value="${room.id}">
        <div class="px-5">
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-start">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <%-- accept="image/*" 속성 지정시 이미지 파일만 선택할 수 있음.   --%>
                            <label class="block mb-2 text-xl font-medium text-gray-900 dark:text-white" for="roomImg">대표사진</label>
                            <input class="block w-full text-sm cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400" aria-describedby="user_avatar_help" id="roomImg" name="roomImg" type="file" accept="image/*">
                        </div>
                        <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <img class="w-[300px]" src="../images/room/room1.jpg" alt="">
<%--                            <img src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="">--%>
                        </div>
                    </dl>
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="roomName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">객실 이름</label>
                            <input type="text" id="roomName" name="roomName"
                                   value="${room.roomName}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>

                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                        <%-- textarea -> selectbox로 수정 --%>
                            <label for="roomIntro" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">객실 타입</label>
                            <select name="roomType" id="roomType">
                                <option value="1" ${room.roomType=='1'?'selected':''}>오토캠핑</option>
                                <option value="2" ${room.roomType=='2'?'selected':''}>글램핑</option>
                                <option value="3" ${room.roomType=='3'?'selected':''}>카라반</option>
                                <option value="4" ${room.roomType=='4'?'selected':''}>룸</option>
                            </select>

                        </div>

                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="roomIntro" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">객실 소개</label>
                            <%-- textarea로 수정 필요..... --%>
                            <input type="text" id="roomIntro" name="roomIntro"
                                   value="${room.roomIntro}"
                                   class="block w-full p-4 h-[200px] text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="roomDefaultPerson" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">기준 인원</label>
                            <input type="number" id="roomDefaultPerson" name="roomDefaultPerson"
                                   value="${room.roomDefaultPerson}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>

                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="roomMaximumPerson" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">최대 인원</label>
                            <input type="number" id="roomMaximumPerson" name="roomMaximumPerson"
                                   value="${room.roomDefaultPerson}"
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