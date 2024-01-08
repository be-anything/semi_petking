<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="${pageContext.request.contextPath}/user/userDelete" method="post" name="userDeleteFrm" >
    <input type="hidden" name="id" value="${loginUser.id}">
    <input type="hidden" name="password" value="${loginUser.password}">
    <input type="submit" value="회원탈퇴" onclick="return confirm('정말 탈퇴하시겠습니까?')">
</form>