<%--
  Created by IntelliJ IDEA.
  User: besth
  Date: 2024-01-06
  Time: 오후 6:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 반려동물정보 --%>

<div class="flex flex-wrap w-full justify-right items-center mx-auto rounded-lg mb-10 relative">
    <%-- 프로필 사진 변경하기 레이아웃때문에 petDetail페이지에 있습니다  --%>
    <div class="w-full h-fit bg-white rounded-lg shadow my-4 sm:max-w-md xl:p-0 flex flex-wrap justify-center">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8 w-full">
            <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                프로필 사진
            </h1>
        </div>
        <form action="" name="profileUpdateFrm" class="w-1/2">
            <div class="flex items-center justify-center w-full">
                <input type="hidden" name="id" value="${loginUser.id}">
                <label for="profileImg"
                       style="background-size: cover; background-position: center; background-image: url('${pageContext.request.contextPath}/upload/user/${loginUser.renamedProfileName}')"
                       class="flex flex-col rounded-full items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                    <div class="flex flex-col bg-gray1 px-4 hover:bg-gray2 rounded-full items-center justify-center pt-5 pb-6">
                        <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                             xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                  stroke-width="2"
                                  d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                        </svg>
                        <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span>
                        </p>
                    </div>
                    <input id="profileImg" name="profileImg"
                           type="file" class="profileImg hidden profileAttach"/>
                </label>
            </div>
            <div class="w-full mr-0 ml-auto flex py-6">
                <button type="button" class="w-1/2 mx-auto profileUpdateBtn text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">사진 저장</button>
            </div>
        </form>
    </div>
    <div class="w-full bg-white rounded-lg shadow my-4 sm:max-w-md xl:p-0">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                반려동물 정보
            </h1>
            <div>
                <p><b>${loginUser.id}</b>님의 반려동물 정보입니다</p>
                <p>반려동물 이름 : ${pet.petName}</p>
                <p>반려동물 나이 : ${pet.petAge}</p>
                <p>성별 : ${pet.petGender}</p>
                <p>중성화여부 : ${pet.neutered}</p>
            </div>

            <%-- 정보가 없으면 추가하기 --%>
            <c:if test="${empty pet}">
                <form name="InsertPetForm" action="${pageContext.request.contextPath}/pet/petInsert" method="post">
                    <div>
                        <label for="petName" class="block mb-2 text-sm font-medium text-gray-900">반려동물 이름</label>
                        <input type="text" name="petName" id="petName" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <label for="petAge" class="block mb-2 text-sm font-medium text-gray-900">반려동물 나이</label>
                        <input type="number" name="petAge" id="petAge" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <fieldset>
                            <legend class="mb-3 text-sm">반려동물 성별</legend>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-gender-option-1" type="radio" name="petGender" value="M" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-gender-option-1" class="block ms-2 text-sm font-medium text-gray-900">남</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-gender-option-2" type="radio" name="petGender" value="F" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-gender-option-2" class="block ms-2 text-sm font-medium text-gray-900">여</label>
                            </div>
                        </fieldset>
                    </div>
                    <div>
                        <fieldset>
                            <legend class="mb-3 text-sm">반려동물 중성화여부</legend>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-neutered-option-1" type="radio" name="neutered" value="Y" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-neutered-option-1" class="block ms-2 text-sm font-medium text-gray-900">O</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-neutered-option-2" type="radio" name="neutered" value="N" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-neutered-option-2" class="block ms-2 text-sm font-medium text-gray-900">X</label>
                            </div>
                        </fieldset> <br>
                    </div>
                    <div>
                        <button type="submit" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">반려동물 정보추가하기</button>
                    </div>
                </form>
            </c:if>

            <%-- 정보수정하기 --%>
            <jsp:include page="/WEB-INF/views/pet/petUpdate.jsp"/>

            <%-- 정보삭제하기 --%>
            <c:if test="${not empty pet}">
                <p class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                    <a href="javascript:confirm('정말 삭제하시겠습니까?') && document.petDeleteFrm.submit();" class="font-medium">반려동물 정보 삭제</a>
                </p>
            </c:if>

            <form action="${pageContext.request.contextPath}/pet/petDelete" method="post" name="petDeleteFrm"></form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/pet/petDetail.js"></script>
<script src="${pageContext.request.contextPath}/js/user/userProfileUpdate.js"></script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
