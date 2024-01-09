<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
    #roomInfoTable{
        border: 1px solid green;
    }

</style>
<%--<c:if test="${loginUser != null}">--%>
<%--    <div class="text-black text-right font-normal mr-20 absolute right-2 hover:font-bold">${loginUser.id}님, 예약을 진행하실건가요?</div>--%>
<%--</c:if>--%>
<%--<c:if test="${firstDay != null && lastDay != null }">--%>
<%--    <div class="text-black text-right font-normal mr-20 absolute right-2 hover:font-bold">${firstDay}부터 ${lastDay} </div>--%>
<%--</c:if>--%>
<div id="myElement1" data-my-value1="<c:out value='${room.roomDefaultPerson}' />"></div>
<div id="myElement2" data-my-value2="<c:out value='${room.roomMaximumPerson}' />"></div>
<div id="myElement3" data-my-value3="<c:out value='${room.roomDefaultFee}' />"></div>
<div id="myElement4" data-my-value4="<c:out value='${room.roomOverFee}' />"></div>
<div class="container mx-auto my-6">
    <div class="flex justify-start">
        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
            예약 하기
        </h1>
    </div>
    <div class="flex-grow border-t border-gray-400"></div>
    <div class="my-10">

        <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-xl">
            🏠객실 정보
        </h1>
        <table name="roomInfoTable" style="border: 1px solid; width: 70%;">
            <colgroup>
                <col style="width: 40%;"/>
                <col style="width: 20%;"/>
                <col style="width: 40%;"/>
            </colgroup>
            <tbody>
            <tr>
                <td rowspan="6">
                    <c:if test="${!empty room.roomRenamedImg}">
                        <img src="${pageContext.request.contextPath}/upload/room/${room.roomRenamedImg}">
                    </c:if>
                    <c:if test="${empty room.roomRenamedImg}">
                        <img class="w-[300px]" src="../images/room/room_default_img.jpg" alt="대표이미지없을경우기본이미지사용">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>객실 명:</td>
                <td>${room.roomName}</td>
            </tr>
            <tr>
                <td>객실 타입:</td>

                <td>${room.roomType == 1 ? '오토캠핑' :
                        room.roomType == 2 ? '글램핑' :
                                room.roomType == 3 ? '카라반' :
                                        room.roomType == 4 ? '룸' : 'Invalid Type'}</td>
            </tr>
            <tr>
                <td>예약 인원 :</td>
                <td>기본 : ${room.roomDefaultPerson}명 / 최대 : ${room.roomMaximumPerson}명</td>
            </tr>
            <tr>
                <td>이용 금액 :</td>
                <td>기본 : ${room.roomDefaultFee}원 / 추가(인당) : ${room.roomOverFee}원</td>
            </tr>
            <tr>
                <td>숙박 기간 :</td>
                <td>${firstDay} 부터 ${lastDay} 까지</td>
            </tr>

            </tbody>
        </table>
        <form class="space-y-4 md:space-y-6"
                action="${pageContext.request.contextPath}/reservation/reservationCreate"
                name="reservationRoomFrm" method="post">

            <%--  첨부 사진이 여러개 있다면 첫번째 사진만 긁어와서 출력함 ${room.roomAttachs[0].roomAttachRenamedName}
            -> room.roomRenamedImg 대표사진으로 수정--%>
            <%--    0108 혜진 객실 정보 간단하게 테이블로 보여주기--%>
                <h1 class="m-4 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-xl">
                    👨‍👩‍👧‍👦예약 정보
                </h1>

        <table name="reservationInfoTable" style="border: 1px solid; width: 70%;">
            <colgroup>
                <col style="width: 30%;"/>
                <col style="width: 70%;"/>
            </colgroup>
            <tbody>
            <tr>
                <td>객실 타입:</td>

                <td>${room.roomType == 1 ? '오토캠핑' :
                        room.roomType == 2 ? '글램핑' :
                                room.roomType == 3 ? '카라반' :
                                        room.roomType == 4 ? '룸' : 'Invalid Type'}</td>
            </tr>
            <tr>
                <td>예약 인원:</td>
                <td><label for="personSelect"></label>
                    <select id="personSelect" onchange="showMeTheMoney()">
                        <!-- JavaScript로 옵션을 동적으로 생성 -->
                    </select>
                    명
                </td>
            </tr>
            <tr>
            <%-- 셀렉트박스를 조작하지 않는다면 기본금액으로 출력한다.--%>
                <td>이용 금액 :</td>
                <td><p id="totalFee" name="totalFee">${room.roomDefaultFee}</p>원</td>
            </tr>
            <tr>
                <td>예약자 명:</td>
                <td>${loginUser.name}</td>
            </tr>
            <tr>
                <td>예약자 전화번호:</td>
                <td>${loginUser.phone}</td>
            </tr>
            <tr>
                <td>예약자 이메일:</td>
                <td>${loginUser.email}</td>
            </tr>
            <tr>
                <td>사용가능한 포인트:</td>
                <td>${loginUser.resultPoint} <input id="usePoint" name="usePoint" placeholder="사용하실 포인트를 입력해주세요."></td>
            </tr>
            </tbody>
        </table>
                <div class="justify-end">
                    <%-- button->submit --%>
                    <button type="button" class="reservationBtn flex w-1/4 justify-center rounded-md bg-green px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-pink-600">예약 하기</button>
                    <div>
                    </div>
                </div>
        </form>

    </div>
</div>

</div>
</div>
<script src ="${pageContext.request.contextPath}/js/reservation/reservationProgress.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>