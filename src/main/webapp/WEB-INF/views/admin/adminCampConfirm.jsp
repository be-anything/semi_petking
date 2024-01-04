<%--
  Created by IntelliJ IDEA.
  User: cksgm
  Date: 2024-01-03
  Time: PM 5:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
    .forms {
        display: none;
    }
    .active {
        display: block;
    }
</style>
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg mt-10">
<ul class="flex flex-wrap text-sm font-medium text-center text-gray-500 border-b border-gray-200 ">
    <li class="tabBtn">
        <a href="#" aria-current="page" class="inline-block p-4 rounded-t-lg text-white bg-green active px-5">캠핑장 신규등록 요청</a>
    </li>
    <li class="tabBtn">
        <a href="#" class="inline-block p-4 rounded-t-lg px-5">캠핑장 삭제 요청</a>
    </li>

</ul>
</div>
<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms active">
    <form name="campUpdateFrm" action="${pageContext.request.contextPath}/admin/CampConfirm" method="post">
        <input type="hidden" name="id" value="${camp.id}">
        <input type="hidden" name="campState" value="${camp.campState}">
        <div class="px-5">
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-start">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <label class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">대표사진</label>
                        </div>
                        <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <img id="img-view" class="img-view" src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="">
                        </div>
                    </dl>
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 이름</label>
                            <input type="text" id="campName" name="campName"
                                   value="${camp.campName}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">사업자 이름</label>
                            <input type="text" id="businessName" name="businessName"
                                   value="${camp.businessName}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">사업자 아이디</label>
                            <input type="text" id="businessId" name="businessName"
                                   value="${camp.businessId}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campIntro" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 소개</label>
                            <%-- textarea로 수정 필요..... --%>
                            <input type="text" id="campIntro" name="campIntro"
                                   value="${camp.campIntro}"
                                   class="block w-full p-4 h-[200px] text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campAddr" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 주소</label>
                            <input type="text" id="campAddr" name="campAddr"
                                   value="${camp.campAddr}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campPhone" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 전화번호</label>
                            <input type="text" id="campPhone" name="campPhone"
                                   value="${camp.campPhone}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campPhone" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">등록요청일</label>
                            <input type="text" id="regDate" name="regDate"
                                   value="${camp.regDate}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campMsg"  class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">반려사유</label>
                            <input type="text"
                                   id="campMsg"
                                   name="campMsg"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <button id="updateBtn" value="1" type="button" class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">승인</button>
                            <button id="CompanionBtn" value="-1" type="submit" class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">반려</button>
                        </div>
                    </dl>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mb-10 forms">
    <form name="campdeleteFrm" action="${pageContext.request.contextPath}/admin/CampConfirm" method="post">
        <input type="hidden" name="id" value="${camp.id}">
        <input type="hidden" name="campState" value="${camp.campState}">
        <div class="px-5">
            <div class="">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-start">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <label class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">대표사진</label>
                        </div>
                        <div class="px-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <img id="img-view1" class="img-view" src="${pageContext.request.contextPath}/upload/camp/${camp.campRenamedImg}" alt="">
                        </div>
                    </dl>
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 이름</label>
                            <input type="text" id="campName1" name="campName"
                                   value="${camp.campName}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">사업자 이름</label>
                            <input type="text" id="businessName1" name="businessName"
                                   value="${camp.businessName}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campName" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">사업자 아이디</label>
                            <input type="text" id="businessId1" name="businessName"
                                   value="${camp.businessId}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campAddr" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 주소</label>
                            <input type="text" id="campAddr1" name="campAddr"
                                   value="${camp.campAddr}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campPhone" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">캠핑장 전화번호</label>
                            <input type="text" id="campPhone1" name="campPhone"
                                   value="${camp.campPhone}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <label for="campPhone" class="block mb-2 text-xl font-medium text-gray-900 dark:text-white">삭제요청일</label>
                            <input type="text" id="regDate1" name="regDate"
                                   value="${camp.regDate}"
                                   class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" readonly>
                        </div>
                        <div class="px-4 py-4 sm:grid sm:grid-cols-1 sm:gap-4 sm:px-0">
                            <button id="deleteBtn" value="2" type="submit" class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">삭제승인</button>
                        </div>
                    </dl>
                </div>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/admin/adminRegist.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>    