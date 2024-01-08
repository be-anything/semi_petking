<%@ page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true" %>
<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2024-01-08
  Time: PM 8:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="${pageContext.request.contextPath}/js/tailwind.config.js"></script>
</head>
<body>
<div class="flex min-h-full flex-col items-center px-6 py-12">
    <h1 class="text-[300px]">🐶</h1>
    <p class="text-red">해당 페이지를 찾을 수 없습니다.</p>
    <p><a href="${pageContext.request.contextPath}" class="hover:underline text-blue">메인페이지로 돌아가기</a></p>
</div>
</body>
</html>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    