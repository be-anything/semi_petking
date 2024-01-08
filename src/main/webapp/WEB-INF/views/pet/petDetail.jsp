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
<div class="flex flex-col items-center justify-center px-6 py-8 mx-auto lg:py-0">
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
            <c:if test="${not empty pet}">
                <p class="text-sm text-green"><b>정보 수정을 원하시면 아래에 작성 후 수정버튼을 눌러주세요😊</b></p>
                <form name="petUpdateFrm" action="${pageContext.request.contextPath}/pet/petUpdate" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="userId" value="${pet.userId}">
                    <div>
                        <label for="newPetName" class="block mb-2 text-sm font-medium text-gray-900">반려동물 이름</label>
                        <input type="text" name="newPetName" id="newPetName" value="" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <label for="newPetAge" class="block mb-2 text-sm font-medium text-gray-900">반려동물 나이</label>
                        <input type="number" name="newPetAge" id="newPetAge" value="" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
                    </div>
                    <div>
                        <fieldset>
                            <legend class="mb-3 text-sm">반려동물 성별</legend>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-newGender-option-1" type="radio" name="newGender" value="M" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-newGender-option-1" class="block ms-2 text-sm font-medium text-gray-900">남</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-newGender-option-2" type="radio" name="newGender" value="F" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-newGender-option-2" class="block ms-2 text-sm font-medium text-gray-900">여</label>
                            </div>
                        </fieldset>
                    </div>
                        <fieldset>
                            <legend class="mb-3 text-sm">반려동물 중성화여부</legend>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-newNeutered-option-1" type="radio" name="newNeutered" value="Y" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-newNeutered-option-1" class="block ms-2 text-sm font-medium text-gray-900">O</label>
                            </div>
                            <div class="inline-flex items-center mr-4">
                                <input id="pet-newNeutered-option-2" type="radio" name="newNeutered" value="N" class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300">
                                <label for="pet-newNeutered-option-2" class="block ms-2 text-sm font-medium text-gray-900">X</label>
                            </div>
                        </fieldset> <br>
                    <div>
                        <button type="submit" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">반려동물 정보 수정</button>
                    </div>
                </form>
            </c:if>

            <%-- 정보삭제하기 --%>
            <c:if test="${not empty pet}">
                <p class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">
                    <a href="javascript:confirm('정말 삭제하시겠습니까?') && document.petDeleteFrm.submit();" class="font-medium">반려동물 정보 삭제</a>
                </p>
            </c:if>

            <form action="${pageContext.request.contextPath}/pet/petDelete" method="post" name="petDeleteFrm"></form>
            <form action="${pageContext.request.contextPath}/pet/petUpdate" method="post" name="petUpdateFrm"></form>
        </div>
    </div>
</div>

