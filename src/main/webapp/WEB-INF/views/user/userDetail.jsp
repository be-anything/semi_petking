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

<%-- 메뉴탭 --%>
<div class="text-center flex flex-col items-center justify-center px-6 py-6 mx-auto lg:py-0">
    <div class="bg-salmon rounded-3xl shadow my-4 sm:max-w-md xl:p-0">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
            <div>
                <label for="photo-container" class="w-full flex flex-col items-center md:text-md">프로필사진</label>
                <a  href="#" class="md:text-sm">사진 변경하기</a>
            </div>
                <div>
                    <button type="button" class="focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                        <a href="${pageContext.request.contextPath}"></a>
                        보유포인트 ${loginUser.resultPoint}점</button>
                </div>
                <div>
                    <button type="button" class="focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                        <a href="${pageContext.request.contextPath}"></a>
                        내 동아리</button>
                </div>
                <div>
                    <button type="button" class="focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                        <a href="${pageContext.request.contextPath}"></a>
                        찜 목록</button>
                </div>
                <div>
                    <button type="button" class="focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                        <a href="${pageContext.request.contextPath}"></a>
                        예약내역</button>
                </div>
                <div>
                    <button type="button" class="focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                        <a href="${pageContext.request.contextPath}"></a>
                        리뷰내역</button>
                </div>
                <div>
                    <button type="button" class="focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                        <a href="${pageContext.request.contextPath}"></a>
                        문의내역</button>
                </div>
        </div>
    </div>
            <p class="text-sm font-light text-red-500">
                <a href="javascript:confirm('정말 탈퇴하시겠습니까?') && document.userDeleteFrm.submit();" class="font-medium text-red-600 hover:underline">회원탈퇴</a>
            </p>
</div>

        <%-- 회원정보 --%>
        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
            <div class="w-full bg-white rounded-lg shadow my-4 sm:max-w-md xl:p-0">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">마이페이지</h1>
            <form name="userUpdateFrm" action="${pageContext.request.contextPath}/user/userUpdate" method="post" class="space-y-4 md:space-y-6">
                <div id="photo-container" class="w-full flex flex-col items-center"></div>
                <div>
                    <label for="id" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">아이디</label>
                    <input type="text" name="id" id="id" value="${loginUser.id}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" readonly>
                </div>
                <div>
                    <label for="name" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">이름</label>
                    <input type="text" name="name" id="name" value="${loginUser.name}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                </div>
                <div>
                    <label for="gradeId" class="block mb-2 text-sm font-medium text-gray-900">회원등급 ${loginUser.gradeId}</label>
                    <input type="text" name="gradeId" id="gradeId" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" readonly>
                </div>
                <div>
                    <label for="nickname" class="block mb-2 text-sm font-medium text-gray-900">닉네임</label>
                    <input type="text" name="nickname" id="nickname" value="${loginUser.nickname}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="name@naver.com" required>
                </div>
                <div>
                    <label for="phone" class="block mb-2 text-sm font-medium text-gray-900">전화번호</label>
                    <input type="text" name="phone" id="phone" value="${loginUser.phone}" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="(-) 없이 입력하세요.">
                </div>
                <button type="button" onclick="location.href = '${pageContext.request.contextPath}/user/userPasswordUpdate';" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">비밀번호 변경하기</button>
                <button type="submit" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">정보 저장</button>
            </form>

            <%-- 펫정보 --%>
            <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
                <div class="w-full bg-white rounded-lg shadow my-4 sm:max-w-md xl:p-0">
                    <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                        <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                            반려동물 정보
                        </h1>
                        <form name="userUpdateFrm" action="${pageContext.request.contextPath}/user/userUpdate" method="post" class="space-y-4 md:space-y-6">
                            <div>
                                <label for="petName" class="block mb-2 text-sm font-medium text-gray-900 ">이름</label>
                                <input type="text" name="petName" id="petName" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                            </div>
                            <div>
                                <label for="nickname" class="block mb-2 text-sm font-medium text-gray-900">나이</label>
                                <input type="number" name="petAge" id="petAge" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                            </div>
                            <div>
                                <fieldset>
                                    <legend class="mb-3">성별</legend>
                                    <div class="inline-flex items-center mr-4">
                                        <input id="pet-gender-option-1" type="radio" name="gender" value="M" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                        <label for="pet-gender-option-1" class="block ms-2  text-sm font-medium text-gray-900">남</label>
                                    </div>
                                    <div class="inline-flex items-center mr-4">
                                        <input id="pet-gender-option-2" type="radio" name="gender" value="F" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                        <label for="pet-gender-option-2" class="block ms-2 text-sm font-medium text-gray-900">여</label>
                                    </div>
                                </fieldset>
                            </div>
                            <div>
                                <fieldset>
                                    <legend class="mb-3">중성화여부</legend>
                                    <div class="inline-flex items-center mr-4">
                                        <input id="pet-neutered-option-1" type="radio" name="neutered" value="O" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                        <label for="pet-neutered-option-1" class="block ms-2  text-sm font-medium text-gray-900">O</label>
                                    </div>
                                    <div class="inline-flex items-center mr-4">
                                        <input id="pet-neutered-option-2" type="radio" name="neutered" value="X" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                        <label for="pet-neutered-option-2" class="block ms-2 text-sm font-medium text-gray-900">X</label>
                                    </div>
                                </fieldset>
                            </div>
                            <button type="submit" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">반려동물정보 저장</button>
                        </form>

    <form action="${pageContext.request.contextPath}/user/userDelete" method="post" name="userDeleteFrm"></form>
    <script src="${pageContext.request.contextPath}/js/user/userDetail.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>