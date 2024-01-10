<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/campSidebar.jsp"/>


<div class="w-full mb-8 ml-auto mr-0 flex justify-end">
<div class="w-4/5">
    <div class="flex items-end justify-between">
    <div class="rounded-lg mt-10">
        <h1 class="w-fit p-4 rounded-t-lg text-white bg-green px-5">
        내 캠핑존 정보
        </h1>
    </div>
    <div class="flex justify-end">

        <%-- 0109 : 캠핑장 아이디 받아와야. --%>
        <button type="button"
                onClick="location.href='${pageContext.request.contextPath}/room/roomCreate?camp_id=${roomCampId}';"
                class="w-fit h-fit text-white bg-gradient-to-r from-rose-200 via-rose-300 to-rose-400 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-rose-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">객실 추가</button>
    </div>
    </div>
    <div class="w-full overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">번호</th>
                <th scope="col" class="px-6 py-3">섬네일</th>
                <th scope="col" class="px-6 py-3">객실 이름</th>
                <th scope="col" class="px-6 py-3">객실 타입</th>
                <th scope="col" class="px-6 py-3">객실 설명</th>
                <th scope="col" class="px-6 py-3">기준인원</th>
                <th scope="col" class="px-6 py-3">최대인원</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${rooms}" var="room" varStatus="vs">
                <div class="odd:bg-white even:bg-gray-50 border-b ">

                    <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">${room.id}</th>
                    <c:if test="${empty room.roomRenamedImg}">
                    <td class="px-6 py-4"><img class="w-[300px] h-[200px]" src="../images/room/room_default_img.jpg" alt=""></td>
                    </c:if>
                    <c:if test="${!empty room.roomRenamedImg}">
                        <td class="px-6 py-4"><img class="w-[300px] h-[200px]"
                          src="${pageContext.request.contextPath}/upload/room/${room.roomRenamedImg}" alt=""></td>
                    </c:if>
                    <td class="px-6 py-4">
                        <a href="${pageContext.request.contextPath}/room/roomDetail?id=${room.id}"
                           class="hover:underline">${fn:escapeXml(room.roomName)}</a>
                            <%--  1221 해당 게시글에 달린 댓글 갯수 보여주기 select count(*) from board_comment where board_id=97; --%>

                    </td>
                    <td class="px-6 py-4">${room.roomType}</td>
                    <td class="px-6 py-4">${room.roomIntro}</td>
                    <td class="px-6 py-4">${room.roomDefaultPerson}</td>
                    <td class="px-6 py-4">${room.roomMaximumPerson}</td>
                    <td class="px-6 py-4">
                        <div class="flex justify-end">
                    <button type="button"
                            onclick="location.href = '${pageContext.request.contextPath}/room/roomUpdate?id=${room.id}'"
                            class="px-5 py-2.5 mt-4 mr-4 sm:mt-6 text-sm font-medium text-center text-black bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                        수정
                    </button>
                        <button type="button"
                                onClick="confirm('${room.id} 번 객실을 삭제하시겠습니까?') &&
                                        document.roomDeleteFrm${room.id}.submit();"
                                class="px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-black bg-red-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                            ${vs.index}번째 삭제
                        </button>
                        </div>
                    </td>
                </div>
                </tr>
                <form
                        name="roomDeleteFrm${room.id}"
                        action="${pageContext.request.contextPath}/room/roomDelete"
                      method="post" >
                    <input type = "hidden" name="id" value="${room.id}">
                    <input type = "hidden" name="campId" value="${room.campId}">
                </form>
            </c:forEach>


            </tbody>
        </table>
    </div>
</div>


</div>
</div>

<div class="flex justify-center mt-6">
    <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
            ${pagebar}
        </ul>
    </nav>
</div>
<script src="${pageContext.request.contextPath}/js/room/roomList.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>