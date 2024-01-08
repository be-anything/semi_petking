<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-01-02
  Time: 오후 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/> <%-- 메뉴탭 --%>

        <%-- 회원정보 --%>
        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
            <div class="w-full bg-white rounded-lg shadow my-4 sm:max-w-md xl:p-0">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">마이페이지</h1>
            <form name="userUpdateFrm" action="${pageContext.request.contextPath}/user/userUpdate" method="post" class="space-y-4 md:space-y-6">
                <div id="photo-container" class="w-full flex flex-col items-center"></div>
                <div>
                    <label class="block mb-2 text-sm font-medium text-gray-900">아이디</label>
                    <input type="text" name="id" id="id" value="${loginUser.id}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" readonly>
                </div>
                <div>
                    <label class="block mb-2 text-sm font-medium text-gray-900">이름</label>
                    <input type="text" name="name" id="name" value="${loginUser.name}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" readonly>
                </div>
                <div>
                    <label class="block mb-2 text-sm font-medium text-gray-900">회원등급</label>
                    <input type="text" name="gradeId" id="gradeId" value="${loginUser.gradeId}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" readonly>
                </div>
                <div>
                    <label class="block mb-2 text-sm font-medium text-gray-900">이메일</label>
                    <input type="text" name="email" id="email" value="${loginUser.email}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" readonly>
                </div>
                <div>
                    <label for="nickname" class="block mb-2 text-sm font-medium text-gray-900">닉네임</label>
                    <input type="text" name="nickname" id="nickname" value="${loginUser.nickname}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                </div>
                <div>
                    <label for="phone" class="block mb-2 text-sm font-medium text-gray-900">전화번호</label>
                    <input type="text" name="phone" id="phone" value="${loginUser.phone}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="(-) 없이 입력하세요.">
                </div>
                <button type="button" onclick="location.href = '${pageContext.request.contextPath}/user/userPasswordUpdate';" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">비밀번호 변경하기</button>
                <button type="submit" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">정보 저장</button>
            </form>

    <form action="${pageContext.request.contextPath}/user/userDelete" method="post" name="userDeleteFrm"></form>
    <script src="${pageContext.request.contextPath}/js/user/userDetail.js"></script>
<jsp:include page="/WEB-INF/views/pet/petDetail.jsp"/> <%-- 펫정보 --%>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>