<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <title>Petking</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/10d89693f5.js" crossorigin="anonymous"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="${pageContext.request.contextPath}/js/tailwind.config.js"></script>
</head>
<body>
<div class="3xl:container">
<div class="bg-salmon">
    <div class="flex justify-between items-center mx-auto max-w-6xl relative">
        <header>
            <nav class="flex flex-row pt-7 pb-7 items-center">
                <div class="text-[#000] text-2xl font-bold mr-20"><a href="#">펫킹.</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/camp/campList">캠핑장 찾기</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="#">커뮤니티</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="#">이벤트</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/room/roomList">객실목록(임시)</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/user/userList">전체회원목록(임시)</a></div>
                <div class="text-black text-right font-normal mr-10 absolute right-0 hover:font-bold"><a href="#">로그인</a></div>
                <div class="text-black text-right font-normal mr-10 absolute right-0 hover:font-bold"><a href="${pageContext.request.contextPath}/user/userLogin">로그인</a></div>
                <i class="fa-regular fa-clock ml-10 absolute right-0 text-black hover:text-green hover:font-bold"></i>
            </nav>
        </header>
    </div>
</div>
    <main class="w-full min-h-[83.9vh]">