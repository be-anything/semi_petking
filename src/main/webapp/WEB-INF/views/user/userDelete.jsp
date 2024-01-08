<%--
  Created by IntelliJ IDEA.
  User: besth
  Date: 2024-01-08
  Time: 오전 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>탈퇴하기</title>
</head>
<body>
<form id="reasonForm">
    <h3>탈퇴 사유를 남겨주시면 서비스 개선에 참고하도록 하겠습니다!</h3>
    <input type="checkbox" id="reason1" name="reason" value="이용 불편함">
    <label for="reason1">맘에 드는 캠핑장이 없어요</label><br>

    <input type="checkbox" id="reason2" name="reason" value="서비스 만족도 낮음">
    <label for="reason2">할인 혜택이 없어요</label><br>

    <input type="checkbox" id="reason3" name="reason" value="다른 서비스 이용 중">
    <label for="reason3">자주 사용하지 않아요</label><br>

    <input type="checkbox" id="reason4" name="reason" value="다른 서비스 이용 중">
    <label for="reason5">홍보성 정보가 너무 많아요</label><br>

    <input type="checkbox" id="reason5" name="reason" value="다른 서비스 이용 중">
    <label for="reason6">개인정보를 삭제하고 싶어요</label><br>

    <input type="checkbox" id="reason6" name="reason" value="기타">
    <label for="reason6">기타(직접입력)</label><br>
    <div id="additionalReason" style="display: none;">
        <label for="otherReason">기타 : </label><br>
        <textarea id="otherReason" placeholder="기타사유를 입력해주세요."></textarea><br>
    </div><br>
    <button type="submit">계속하기</button>
</form>
</body>
</html>
