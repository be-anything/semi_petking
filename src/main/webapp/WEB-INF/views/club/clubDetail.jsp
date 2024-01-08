<%--
  Created by IntelliJ IDEA.
  User: 82104
  Date: 2024-01-05
  Time: 오후 7:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="xl:container p-8">
    <div class="w-full p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${loginUser.id}님 반가워요🤗</h5>
    </div>
</div>
<div class="flex flex-wrap justify-between items-center mx-auto max-w-6xl rounded-lg bg-gray1 mt-10 mb-10">
    <div>
        <div class="py-5 px-5">
            <div class="px-4 sm:px-0">
                <h3 class="text-2xl font-semibold leading-7 text-black">${club.clubName}</h3>
                <p class="mt-1 max-w-2xl text-md leading-6 text-gray-500">동아리 이름</p>
            </div>
            <div class="flex justify-end">
                <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-10 sm:px-0 items-center">
                    <dl class="divide-y divide-gray-100">
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900"></dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${club.clubIntroTitle}</dd>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-4 sm:gap-4 sm:px-0">
                            <dt class="text-sm font-medium leading-6 text-gray-900">내용</dt>
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-3 sm:mt-0">${club.clubIntroContent}</dd>
                        </div>
                        <div class="h-[300px] font-medium leading-6 text-gray-900 mt-20 my-4 text-center">
                            <h1 class="mb-4 text-3xl font-extrabold leading-none tracking-tight text-gray-900"><span
                                    class="underline underline-offset-3 decoration-8 decoration-green">동아리 소개</span></h1>
                            <p class="text-lg font-normal text-gray3 lg:text-xl mt-10">${club.clubIntroContent}</p>
                        </div>
                        <div class="px-4 py-6 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-1 sm:mt-0 mx-auto">
                                <div class="flex justify-end">
                                <button
                                        type="button"
                                        onclick="location.href = '${pageContext.request.contextPath}/club/clubUpdate';"
                                        class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
                                    수정
                                </button>
                                </div>
                                <button id="deleteBtn" type="button"
                                        onclick="confirm('정말 삭제하시겠습니까? 😯') && document.clubDeleteFrm.submit()"
                                        class="hover:text-white bg-white text-black border border-gray2 hover:bg-black font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2">
                                    삭제
                                </button>
                                <form
                                        action="${pageContext.request.contextPath}/club/clubDelete"
                                        method="post"
                                        name="clubDeleteFrm">
                                    <input type="hidden" name="id" value="${club.clubName}">
                                </form>
                            </dd>
                        </div>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/club/clubDetail.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>