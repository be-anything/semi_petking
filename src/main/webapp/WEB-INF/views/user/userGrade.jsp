<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/>

<div class="w-3/4">
    <div class="container mx-auto my-6 w-fit">
        <h1 class="text-2xl text-center font-bold text-gray-900 md:text-3xl">
            ${userVo.name} 님의 등급은 ${userVo.userGrade.name} 입니다. 🐶
        </h1>
        <p class="text-center mt-2">
            다음 등급까지 000포인트 남았습니다.
        </p>
    </div>
    <div>
        <div class="w-1/2 mx-auto">
            <div class="flex justify-around">
                <div class="text-gray-900 bg-gradient-to-r from-red-200 via-red-300 to-yellow-200 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-red-100 dark:focus:ring-red-400 font-medium rounded-full text-sm px-5 py-2.5 text-center mx-4 w-[300px]">누적 포인트 : ${userVo.resultPoint}점</div>
                <div class="text-gray-900 bg-gradient-to-r from-red-200 via-red-300 to-yellow-200 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-red-100 dark:focus:ring-red-400 font-medium rounded-full text-sm px-5 py-2.5 text-center mx-4 w-[300px]">사용 가능 포인트 : ${pointSum}</div>
            </div>
        </div>
    </div>
    <div class="container mx-auto mt-8">
        <div class="overflow-x-auto">
            <table class="w-full text-sm text-left text-gray-500 border-collapse">
                <thead class="text-xs text-white bg-green">
                <tr>
                    <th scope="col" class="px-6 py-3">번호</th>
                    <th scope="col" class="px-6 py-3">사용자 아이디</th>
                    <th scope="col" class="px-6 py-3">포인트</th>
                    <th scope="col" class="px-6 py-3">적립내용</th>
                    <th scope="col" class="px-6 py-3">적립일시</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${points}" var="point" varStatus="vs">
                    <tr class="bg-white border-b">
                        <td class="px-6 py-4 font-medium text-gray-900">${vs.index + 1}</td>
                        <td class="px-6 py-4">${point.userId}</td>
                        <td class="px-6 py-4">${point.point}</td>
                        <td class="px-6 py-4 break-all">${point.pointLog}</td>
                        <td class="px-6 py-4">
                            <fmt:parseDate value="${point.regDate}" pattern="yyyy-MM-dd" var="regDate" />
                            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    