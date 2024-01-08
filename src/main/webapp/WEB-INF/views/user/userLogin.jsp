<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-28
  Time: 오후 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <div class="border-b-2 border-green flex justify-center mb-10">
            <h1 class="block mb-2 mt-5 text-xl font-medium text-black dark:text-white">사용자 로그인</h1>
        </div>
        <form name="userLoginFrm" class="space-y-6" method="POST">
            <div>
                <label for="id" class="block text-sm font-medium leading-6 text-gray-900">아이디</label>
                <div class="mt-2">
                    <input id="id" name="id" type="text" autocomplete="id" required class="px-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                </div>
            </div>
            <div>
                <div class="flex items-center justify-between">
                    <label for="password" class="block text-sm font-medium leading-6 text-gray-900">비밀번호</label>
                </div>
                <div class="mt-2">
                    <input id="password" name="password" type="password" autocomplete="password" required class="px-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                </div>
            </div>
            <div class="flex items-center justify-between">
                <div>
                    <input id="saveId" name="saveId" type="checkbox" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500">
                    <label for="saveId" class="ms-2 text-sm font-medium text-gray-700">아이디 저장</label>
                </div>
                <a href="${pageContext.request.contextPath}/user/userRegisterType" class="ms-2 text-sm font-medium text-gray-700 hover:underline">회원가입</a>
            </div>

            <div class="flex flex-col">
                <button type="submit" class="flex w-full justify-center rounded-md bg-green px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-pink-600">로그인</button>
                <a href="${pageContext.request.contextPath}/camp/campLogin" class="text-center text-sm text-green hover:text-blue hover:underline mt-5">사업자 회원로그인은 여기를 눌러주세요</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/user/userLogin.js"></script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>