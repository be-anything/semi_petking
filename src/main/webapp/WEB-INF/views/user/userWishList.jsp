<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/>

    <div class="w-full">
        <table>
            <tbody>
                <tr class="flex flex-wrap">
                    <c:forEach items="${wishes}" var="wish" varStatus="vs">
                        <c:forEach items="${wish.camps}" var="camp" varStatus="vs">
                            <td>
                                <div class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                                    <a href="${pageContext.request.contextPath}/camp/campDetail?id=${camp.id}">
                                        <div class="w-[250px] h-[250px] bg-gray2">
                                            <img class="p-8 rounded-t-lg" src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="product image" />
                                        </div>
                                    </a>
                                    <div class="px-5 pb-4 mt-4">
                                        <div class="flex items-center justify-between">
                                            <span class="text-xl font-bold text-gray-900">${camp.campName}</span>
                                            <a href="#" class="text-white bg-red font-medium rounded-lg text-xs px-3 py-1.5 text-center">${wish.wishCount}</a>
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