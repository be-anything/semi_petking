<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/>

    <div class="w-full flex justify-end">
        <table class="w-3/4">
            <tbody>
                <tr>
                    <c:if test="${wishes.size() == 0}">
                        <td class="flex justify-center flex-col items-center">
                            <div>
                                <i class="fa-solid fa-heart-crack text-red text-9xl pt-36"></i>
                            </div>
                            <p class="text-3xl pt-12"> 내가 찜한 캠핑장이 없습니다.</p>
                            <a class="mb-36 mt-8 py-1 px-3 rounded-lg border border-gray2 hover:bg-light-pink" href="${pageContext.request.contextPath}/camp/campList">맘에 드는 캠핑장 찾으러가기</a>
                        </td>
                    </c:if>
                </tr>
                <tr class="flex flex-wrap">
                    <c:forEach items="${wishes}" var="wish" varStatus="vs">
                        <c:forEach items="${wish.camps}" var="camp" varStatus="vs">
                            <td class="m-1">
                                <div class="w-[250px] max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                                    <a href="${pageContext.request.contextPath}/camp/campDetail?id=${camp.id}">
                                        <div class="w-[250px] h-[250px] bg-gray2 overflow-hidden flex items-center">
                                            <img class="p-4 w-full rounded-t-lg" src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="image" />
                                        </div>
                                    </a>
                                    <div class="px-5 pb-4 mt-4">
                                        <div class="flex items-center justify-between">
                                            <span class="text-xl w-[150px] h-[28px] overflow-hidden font-bold text-gray-900">${camp.campName}</span>
                                            <span class="inline-flex items-center rounded-full bg-pink px-2 py-1 text-xs font-medium text-white ring-1 ring-inset ring-gray-500/10">찜 ${wish.wishCount}</span>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </c:forEach>
                    </c:forEach>
                </tr>
            </tbody>
        </table>


    </div>
</div> <!--sidebar와 이 페이지를 감싸서 닫는 태그-->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>