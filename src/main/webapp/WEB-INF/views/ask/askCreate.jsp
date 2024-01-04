<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2024-01-03
  Time: 오후 3:07
  문의하기 작성 페이지
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="py-8 px-4 mx-auto max-w-2xl lg:py-16">
    <h2 class="mb-4 text-xl font-bold text-gray-900">문의 작성하기</h2>
    <form
            action="${pageContext.request.contextPath}/ask/askCreate"
            name="askCreateFrm" method="post">
        <div class="grid gap-4 sm:grid-cols-2 sm:gap-6">
            <div class="sm:col-span-2">
                <label for="askTitle" class="block mb-2 text-sm font-medium text-gray-900">제목</label>
                <input type="text" name="askTitle" id="askTitle"
                       onclick="'${loginUser.id}' || alert('로그인 후 문의 작성하세요1.(임시)');"
                       class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5" placeholder="제목을 작성하세요." required>
            </div>
            <div class="sm:col-span-2">
                <label for="userId" class="block mb-2 text-sm font-medium text-gray-900">작성자</label>
                <input type="text" name="userId" id="userId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                       value="${loginUser.id}" required readonly>
<%--                       value="${loginMember.id}" required readonly>--%>
            </div>
            <div class="sm:col-span-2">
                <label for="askContent" class="block mb-2 text-sm font-medium text-gray-900">내용</label>
                <textarea id="askContent" name="askContent"
                          onclick="'${loginUser.id}' || alert('로그인 후 문의 작성하세요2.(임시)');"
                          rows="8" class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500" placeholder="내용을 작성하세요." required></textarea>
            </div>
        </div>
        <button type="submit"
                class="text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
                등록
        </button>
    </form>
</div>

<%--<script src="${pageContext.request.contextPath}/js/board/boardCreate.js"></script>--%>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>