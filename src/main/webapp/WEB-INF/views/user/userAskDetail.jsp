
<%--
    0104 문의글 상세보기 페이지

--%>
<%!
    int one = 1;
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.sh.petking.camp.model.entity.Camp" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<jsp:include page="/WEB-INF/views/common/userSidebar.jsp"/>

<div class="w-4/5 ml-auto mr-[-9rem] mb-8">
<div class="xl:container snap-x p-8">
    <div class="w-9/12 p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-2 text-2xl font-semibold tracking-tight text-gray-900 ">${fn:escapeXml(ask.askTitle)}</h5>
        <div class="flex-grow border-t border-gray-400"></div>
        <p class="mb-3 font-normal text-gray-500">작성자 : ${ask.userId}</p>
             <p class="mb-3 font-normal text-gray-500">작성일 :
            <fmt:parseDate value="${ask.askRegDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
            <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
        </p>
        <h6 class="mb-2 text-xl tracking-tight text-gray-900 ">${ask.askContent}</h6>

        <%-- 작성자 본인과 관리자에게만 노출이 되어야 한다.--%>
        <c:if test="${loginUser.role eq Role.A or loginUser.id eq ask.userId}">
            <div class="flex justify-end">
                <button type="button"
                        onclick="location.href = '${pageContext.request.contextPath}/ask/BoardUpdate?id=${ask.id}'"
                        class="px-5 py-2.5 mt-4 mr-4 sm:mt-6 text-sm font-medium text-center text-white bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                    수정
                </button>
                <button type="button"
                        onClick="confirm('정말 삭제하시겠습니까?💦') && document.boardDeleteFrm.submit()"
                        class="px-5 py-2.5 mt-4 sm:mt-6 text-sm font-medium text-center text-white bg-red-700 rounded-lg focus:ring-4 focus:ring-primary-200">
                    삭제
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/board/boardDelete"
                  name="boardDeleteFrm"
                  method="post" >
                <input type = "hidden" name="id" value="${board.id}">
            </form>
        </c:if>
    </div>

    <%-- 답변이 이미 존재한다면  답변을 보여줄 div 영역 --%>
    <c:if test="${ask.askComment eq null}">
        <div class="w-9/12 p-6 bg-white border border-gray-200 rounded-lg shadow">
            <h1>아직 캠핑장 사장님 답변이 등록되지 않았습니다. 조금만 기다려주세요.</h1>
        </div>
    </c:if>
    <c:if test="${ask.askComment ne null}">
        <div class="w-9/12 p-6 bg-white border border-gray-200 rounded-lg shadow">
            <h5 class="mb-2 text-base font-semibold tracking-tight text-gray-900 ">${ask.camp.campName}</h5>
            <p class="mb-3 font-normal text-gray-500">작성일 :
                <fmt:parseDate value="${ask.askCommentRegDate}" pattern="yyyy-MM-dd'T'HH:mm" var="regDate"/>
                <fmt:formatDate value="${regDate}" pattern="yy/MM/dd HH:mm"/>
            </p>
            <p class="mb-2 text-base tracking-tight text-gray-900 ">${ask.askComment}</p>
        </div>
    </c:if>

    <%-- 목록으로 돌아가는 버튼..--%>
    <div class="w-9/12 my-2">
        <div class="flex items-center justify-end px-3 py-2">
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/user/userAskList?userId=${loginUser.id}'"
                class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white bg-rose-200 rounded-lg focus:ring-4 focus:ring-blue-200 hover:bg-blue-800">
                목록
            </button>
        </div>
    </div>
</div>
</div>
</div>
<script src ="${pageContext.request.contextPath}/js/ask/askDetail.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>