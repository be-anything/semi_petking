<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<%--<div><img class="w-full mx-auto mb-[500px]" src="${pageContext.request.contextPath}/images/common/main.jpg" alt=""></div>--%>
<c:forEach items="${promotionVos}" var="promo" varStatus="vs">
    <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
    <td class="px-6 py-4">
    ${promo.camp.campName}
    </td>
        <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
            <img id="img-view1" class="img-view" src="${pageContext.request.contextPath}/upload/camp/${promo.camp.campRenamedImg}" alt="">
        </div>
    </tr>
</c:forEach>


<%--<script src="${pageContext.request.contextPath}/js/index.js"></script>--%>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />


