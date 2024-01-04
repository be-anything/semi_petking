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
    <script>
        const contextPath = "${pageContext.request.contextPath}";
        <c:if test="${msg != null}">
        alert(`${msg}`); // 여러줄 입력이 가능하도록 `(백틱)으로 감싸야한다. 0104 test 용으로 복붙했어요
        <c:remove var="msg" scope="session" />
        </c:if>
    </script>

    <script
            src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@3.6.12/dist/js/splide.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/@splidejs/splide@3.6.12/dist/css/splide.min.css" rel="stylesheet">
</head>
<body>
<div class="3xl:container">
<div class="bg-salmon">
    <div class="flex justify-between items-center mx-auto max-w-6xl relative">
        <header>
            <nav class="flex flex-row pt-7 pb-7 items-center">
                <div class="text-[#000] text-2xl font-bold mr-20"><a href="#">펫킹.</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/camp/campList">캠핑장 찾기</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/board/boardList">커뮤니티</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="#">이벤트</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/room/roomList">객실목록(임시)</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/ask/askList">문의하기(임시)</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/reservation/reservationMain">예약하기(임시)</a></div>
                <c:if test="${loginUser.role eq 'A' }">
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/user/userList">전체회원목록(임시)</a></div>
                <div class="text-black text-base font-normal mr-10 hover:font-bold"><a href="${pageContext.request.contextPath}/admin/reviewList">전체리뷰보기</a></div>
                </c:if>
                <c:if test="${loginUser == null}">
                    <div class="text-black text-right font-normal mr-7 absolute right-0 hover:font-bold"><a href="${pageContext.request.contextPath}/user/userLogin">로그인</a></div>
                    <i class="fa-regular fa-clock ml-10 absolute right-0 text-black hover:text-green hover:font-bold"></i>
                </c:if>
                    <i class="fa-regular fa-clock ml-10 absolute right-0 text-black hover:text-green hover:font-bold"></i>
                <c:if test="${loginUser != null}">
                    <div class="text-black text-right font-normal mr-20 absolute right-2 hover:font-bold"><a href="${pageContext.request.contextPath}/user/userDetail">${loginUser.id}님의 페이지</a></div>
                    <div class="text-black text-right font-normal mr-3 absolute right-2 hover:font-bold"><a href="${pageContext.request.contextPath}/user/userLogout">로그아웃</a></div>
                </c:if>
            </nav>
        </header>
    </div>
</div>
    <main class="w-full min-h-[83.9vh]">