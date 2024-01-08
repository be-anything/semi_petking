<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>예약 진행 상세 페이지 여기서 객실 상세 정보 보여주고 인원수 선택 등등 함</p>
<c:if test="${loginUser != null}">
    <div class="text-black text-right font-normal mr-20 absolute right-2 hover:font-bold">${loginUser.id}님, 예약을 진행하실건가요?</div>
</c:if>











</body>
</html>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>