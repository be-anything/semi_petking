<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<div class="flex justify-center pt-[300px]">
    <div class="w-fit h-fit mr-1">
        <a href="${pageContext.request.contextPath}/user/userRegister" class="rounded-lg py-[120px] px-[100px] pt-[180px] bg-gray2 hover:bg-light-pink hover:drop-shadow-2xl">
            <i class="fa-solid fa-user text-9xl text-black"></i>
        </a>
        <p class="mt-[125px] text-lg text-center">캠핑러 사용자</p>
    </div>
    <div class="w-fit h-fit ml-1">
        <a href="${pageContext.request.contextPath}/camp/campRegister" class="rounded-lg py-[120px] px-[100px] pt-[180px] bg-gray2 hover:bg-light-pink hover:drop-shadow-2xl">
            <i class="fa-solid fa-campground text-9xl text-black"></i>
        </a>
        <p class="mt-[125px] text-lg text-center">캠핑장 사장님</p>
    </div>
</div>



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>