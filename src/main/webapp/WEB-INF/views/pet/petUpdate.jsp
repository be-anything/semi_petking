<%--
  Created by IntelliJ IDEA.
  User: besth
  Date: 2024-01-08
  Time: 오후 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 정보수정하기 --%>
<c:if test="${not empty pet}">
    <p class="text-base text-green"><b>정보 수정을 원하시면 작성 후 수정버튼을 눌러주세요😊</b></p>
    <form name="petUpdateFrm" action="${pageContext.request.contextPath}/pet/petUpdate" method="post">
        <input type="hidden" name="userId" value="${pet.userId}">
        <div>
            <label for="newPetName" class="block mb-2 text-sm font-medium text-gray-900">반려동물 이름</label>
            <input type="text" name="newPetName" id="newPetName" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
        </div>
        <div>
            <label for="newPetAge" class="block mb-2 text-sm font-medium text-gray-900">반려동물 나이</label>
            <input type="number" name="newPetAge" id="newPetAge" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" required>
        </div>
        <div>
            <fieldset>
                <legend class="mb-3 text-sm">반려동물 성별</legend>
                <div class="inline-flex items-center mr-8">
                    <input class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300" id="pet-newGender-option-1" type="radio" name="newGender" value="M">
                    <label for="pet-newGender-option-1" class="block ms-2 text-sm font-medium text-gray-900">남</label>
                </div>
                <div class="inline-flex items-center mr-4">
                    <input class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300" id="pet-newGender-option-2" type="radio" name="newGender" value="F">
                    <label for="pet-newGender-option-2" class="block ms-2 text-sm font-medium text-gray-900">여</label>
                </div>
            </fieldset>
        </div>
        <fieldset>
            <legend class="mb-3 text-sm">반려동물 중성화여부</legend>
            <div class="inline-flex items-center mr-8">
                <input class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300" id="pet-newNeutered-option-1"  type="radio" name="newNeutered" value="Y">
                <label for="pet-newNeutered-option-1" class="block ms-2 text-sm font-medium text-gray-900">O</label>
            </div>
            <div class="inline-flex items-center mr-4">
                <input class="w-4 h-4 border-gray-300 focus:ring-2 focus:ring-blue-300" id="pet-newNeutered-option-2" type="radio" name="newNeutered" value="N">
                <label for="pet-newNeutered-option-2" class="block ms-2 text-sm font-medium text-gray-900">X</label>
            </div>
        </fieldset> <br>
        <div>
            <button type="submit" class="text-white w-full bg-purple-400 hover:bg-purple-500 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">반려동물 정보 수정</button>
        </div>
    </form>
</c:if>

<script src="${pageContext.request.contextPath}/js/pet/petUpdate.js"></script>