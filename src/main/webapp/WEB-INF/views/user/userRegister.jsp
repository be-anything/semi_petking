<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2023-12-30
  Time: 오후 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<section>
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
        <div class="w-full bg-white rounded-lg shadow my-6 sm:max-w-md xl:p-0">
            <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">회원가입</h1>
                <form name="userRegisterFrm" method="post" class="space-y-4 md:space-y-6">
                    <div class="relative">
                        <label for="id" class="block mb-2 text-sm font-medium text-gray-900">아이디<span style="color: red;"> *</span></label>
                        <input type="text" name="id" id="id" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                            <span class="guide ok text-xs text-green1 sticky hidden top-10 end-1">사용 가능한 아이디 입니다.</span>
                            <span class="guide error text-xs text-red sticky hidden top-10 end-1">이미 사용중인 아이디 입니다.</span>
                            <input type="hidden" id="idValid" value="1">
                    </div>
                    <div>
                        <label for="password" class="block mb-2 text-sm font-medium text-gray-900">비밀번호<span style="color: red;"> *</span></label>
                        <input type="password" name="password" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <label for="confirm-password" class="block mb-2 text-sm font-medium text-gray-900">비밀번호 확인<span style="color: red;"> *</span></label>
                        <input type="password" name="confirm-password" id="confirm-password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <label for="name" class="block mb-2 text-sm font-medium text-gray-900">이름<span style="color: red;"> *</span></label>
                        <input type="text" name="name" id="name" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <label for="email" class="block mb-2 text-sm font-medium text-gray-900">이메일<span style="color: red;"> *</span></label>
                        <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="name@naver.com" required>
<%--                            <span class="guide ok text-xs text-green1 sticky hidden top-10 end-1">사용 가능한 이메일 입니다.</span>--%>
<%--                            <span class="guide error text-xs text-red sticky hidden top-10 end-1">이미 사용중인 이메일 입니다.</span>--%>
<%--                            <input type="hidden" id="emailVal" value="1">--%>
                    </div>
                    <div>
                        <label for="phone" class="block mb-2 text-sm font-medium text-gray-900">전화번호<span style="color: red;"> *</span></label>
                        <input type="text" name="phone" id="phone" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="(-) 없이 입력해주세요." required>
                    </div>

<%--<div>--%>
<%--    <label for="email" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">이메일</label>--%>
<%--            <input type="email" id="email" name="email" placeholder="이메일">--%>
<%--            <button onclick="checkEmail()">중복 확인</button>--%>
<%--            <div id="emailResult"></div>--%>
<%--</div>--%>
                    <div>
                        <button type="submit" class="flex w-full justify-center rounded-md bg-green px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-pink-600">회원가입</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>

    <script src="${pageContext.request.contextPath}/js/user/userRegister.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>