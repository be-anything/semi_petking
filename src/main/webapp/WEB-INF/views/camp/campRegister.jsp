<%--
  Created by IntelliJ IDEA.
  User: min_j
  Date: 2024-01-04
  Time: PM 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<section class="campUserRegister">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
        <div class="w-full bg-white rounded-lg shadow my-6 sm:max-w-md xl:p-0">
            <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">캠핑장 사장님 회원가입</h1>
                <form name="campUserRegisterFrm" method="post" class="space-y-4 md:space-y-6">
                    <div class="relative mt-4">
                        <label for="id" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">아이디</label>
                        <input type="text" name="id" id="id" class="registInputs bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                        <div class="confirm-msg relative mt-1">
                            <p class="hidden text-xs absolute right-0">아이디는 영문자, 숫자로 입력해주세요.</p>
                            <p class="hidden text-xs text-blue absolute right-0">사용 가능한 아이디입니다.</p>
                            <p class="hidden text-xs text-red absolute right-0">사용 불가한 아이디입니다.</p>
                            <p class="idCheck hidden text-xs text-red absolute right-0">이미 사용중인 아이디입니다.</p>
                        </div>
                    </div>
                    <div class="mt-4">
                        <label for="password" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">비밀번호</label>
                        <input type="password" name="password" id="password" placeholder="••••••••" class="registInputs bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                        <div class="confirm-msg relative mt-1">
                            <p class="hidden text-xs absolute right-0">비밀번호는 특수문자(!@#$%^&*), 영문자, 숫자로 입력해주세요.</p>
                            <p class="hidden text-xs text-blue absolute right-0">사용 가능한 비밀번호입니다.</p>
                            <p class="hidden text-xs text-red absolute right-0">사용 불가한 비밀번호입니다.</p>
                        </div>
                    </div>
                    <div class="mt-4">
                        <label for="confirm-password" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">비밀번호 확인</label>
                        <input type="password" name="confirm-password" id="confirm-password" placeholder="••••••••" class="registInputs bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                        <div class="confirm-msg relative mt-1">
                            <p class="hidden text-xs absolute right-0">비밀번호 확인은 비밀번호와 동일하게 입력해주세요.</p>
                            <p class="hidden text-xs text-blue absolute right-0">비밀번호가 일치합니다.</p>
                            <p class="hidden text-xs text-red absolute right-0">비밀번호가 일치하지 않습니다.</p>
                        </div>
                    </div>
                    <div class="mt-4">
                        <label for="name" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">이름</label>
                        <input type="text" name="name" id="name" class="registInputs bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                        <div class="confirm-msg relative mt-1">
                            <p class="hidden text-xs absolute right-0">이름은 한글 2글자 이상으로 입력해주세요.</p>
                            <p class="hidden text-xs text-blue absolute right-0">사용 가능한 이름입니다.</p>
                            <p class="hidden text-xs text-red absolute right-0">사용 불가한 이름입니다.</p>
                        </div>
                    </div>
                    <div class="mt-4">
                        <label for="phone-1" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">전화번호</label>
                        <div class="flex justify-around items-center">
                            <select name="phone" id="phone-1" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5">
                                <option name="phone" id="010">010</option>
                                <option name="phone" id="02">02</option>
                                <option name="phone" id="031">031</option>
                                <option name="phone" id="033">033</option>
                            </select>
<%--                            <input type="number" name="phone" id="phone-1" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5" required>--%>
                            <span class="px-2">―</span>
                            <input type="number" name="phone" id="phone-2" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5" required>
                            <span class="px-2">―</span>
                            <input type="number" name="phone" id="phone-3" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5" required>
                        </div>
                    </div>
                    <div>
                        <button type="button" class="continueBtn flex w-full justify-center rounded-md bg-green px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-pink-600">계속하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<section class="campInfoRegister hidden">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
        <div class="w-full bg-white rounded-lg shadow my-6 sm:max-w-md xl:p-0">
            <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">캠핑장 등록 신청</h1>
                <form name="campInfoRegisterFrm" method="post" class="space-y-4 md:space-y-6">
                    <div class="relative mt-4">
                        <label for="campName" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">캠핑장 이름</label>
                        <input type="text" name="campName" id="campName" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div class="mt-4">
                        <label for="businessNumber" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">사업자 번호</label>
                        <div class="flex justify-around items-center">
                            <input type="number" name="businessNumber" id="businessNumber" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5" required>
                            <span class="px-2">―</span>
                            <input type="number" name="businessNumber" id="businessNumber-2" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5" required>
                            <span class="px-2">―</span>
                            <input type="number" name="businessNumber" id="businessNumber-3" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-1/4 p-2.5" required>
                        </div>
                        <div id="businessNumberMsg" class="confirm-msg relative mt-1">
                            <p class="hidden text-xs text-blue absolute right-0">사용 가능한 사업자 번호입니다.</p>
                            <p class="hidden text-xs text-red absolute right-0">이미 등록된 사업자 번호입니다.</p>
                        </div>
                    </div>
                    <div class="mt-4">
                        <label for="businessIntro" class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">캠핑장 소개</label>
                        <textarea name="businessIntro" id="businessIntro" rows="10" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required></textarea>
                    </div>
                    <div class="mt-4">

                        <label class="block mb-2 text-sm font-medium text-gray-900 after:content-['*'] after:ml-0.5 after:text-red-500">캠핑장 주소</label>
                        <div class="flex justify-around items-center mb-1">
                            <input type="text" name="addr-postcode" placeholder="우편번호" id="addr-postcode" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block mr-1 w-full p-2.5" required>
                            <input type="button" name="findAddr" id="findAddr" class="cursor-pointer bg-gray3 w-[100px] border border-gray-300 text-white sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block p-2.5" value="우편번호 찾기" required>
                        </div>
                        <input type="text" name="addr-address" id="addr-address" placeholder="주소" class="mb-1 bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block mr-1 w-full p-2.5" required>
                        <input type="text" name="addr-detail" id="addr-detail" placeholder="상세주소" class="mb-1 bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block mr-1 w-full p-2.5" required>
                        <input type="text" name="addr-extra" id="addr-extra" placeholder="참고항목" class="mb-1 bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block mr-1 w-full p-2.5" required>
                    </div>
                    <div class="mt-4">
                    </div>
                    <div>
                        <button type="button" class="submitBtn flex w-full justify-center rounded-md bg-green px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-pink-600">등록하기</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<form action="${pageContext.request.contextPath}/camp/campRegister" method="post" name="campTotalRegisterFrm">
    <input type="hidden" name="businessId">
    <input type="hidden" name="businessPassword">
    <input type="hidden" name="businessNumber">
    <input type="hidden" name="businessName">
    <input type="hidden" name="campName">
    <input type="hidden" name="campIntro">
    <input type="hidden" name="campPhone">
    <input type="hidden" name="campAddr">
    <input type="hidden" name="campLcLa">
    <input type="hidden" name="campLcLo">
</form>



<script src="${pageContext.request.contextPath}/js/camp/campRegister.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>