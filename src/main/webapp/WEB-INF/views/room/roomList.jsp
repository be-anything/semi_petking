<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-29
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
  객실 리스트 페이지 일단 고객입장에서 보이는 페이지로 출력하기.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container mx-auto my-6">
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            객실 리스트
        </h1>
    </div>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">번호</th>
                <th scope="col" class="px-6 py-3">이미지</th>
                <th scope="col" class="px-6 py-3">객실 이름</th>
                <th scope="col" class="px-6 py-3">객실 타입</th>
                <th scope="col" class="px-6 py-3">객실 설명</th>
                <th scope="col" class="px-6 py-3">기준인원</th>
                <th scope="col" class="px-6 py-3">최대인원</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${rooms}" var="room" varStatus="vs">
                <tr class="odd:bg-white even:bg-gray-50 border-b ">
                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${room.id}</th>
                    <td class="px-6 py-4"><img class="w-[300px]" src="../images/room/room1.jpg" alt=""></td>
                    <td class="px-6 py-4">
                        <a href="${pageContext.request.contextPath}/room/roomDetail?id=${room.id}"
                           class="hover:underline">${room.roomName}</a>
                            <%--  1221 해당 게시글에 달린 댓글 갯수 보여주기 select count(*) from board_comment where board_id=97; --%>

                    </td>
                    <td class="px-6 py-4">${room.roomType}</td>
                    <td class="px-6 py-4">${room.roomIntro}</td>
                    <td class="px-6 py-4">${room.roomDefaultPerson}</td>
                    <td class="px-6 py-4">${room.roomMaximumPerson}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="flex justify-center mt-6">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            ${pagebar}
        </ul>
    </nav>
</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>