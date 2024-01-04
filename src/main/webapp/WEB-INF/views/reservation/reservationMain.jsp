<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.Calendar"%>
<%@ page trimDirectiveWhitespaces="true" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%
    request.setCharacterEncoding("utf-8");

    Calendar cal = Calendar.getInstance();

    // 시스템 오늘날짜
    int ty = cal.get(Calendar.YEAR);
    int tm = cal.get(Calendar.MONTH)+1;
    int td = cal.get(Calendar.DATE);

    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH)+1;

    // 파라미터 받기
    String sy = request.getParameter("year");
    String sm = request.getParameter("month");

    if(sy!=null) { // 넘어온 파라미터가 있으면
        year = Integer.parseInt(sy);
    }
    if(sm!=null) {
        month = Integer.parseInt(sm);
    }

    cal.set(year, month-1, 1);
    year = cal.get(Calendar.YEAR);
    month = cal.get(Calendar.MONTH)+1;

    int week = cal.get(Calendar.DAY_OF_WEEK); // 1(일)~7(토)
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    <style type="text/css">
        *{
            margin: 0; padding: 0;
            box-sizing: border-box;
        }

        body {
            font-size: 14px;
            font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
        }

        a {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
        a:active, a:hover {
            text-decoration: underline;
            color: #F28011;
        }

        .calendar {
            width: 280px;
            margin: 30px auto;
        }
        .calendar .title{
            height: 37px;
            line-height: 37px;
            text-align: center;
            font-weight: 600;
        }

        .calendar table {
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
        }

        .calendar table thead tr:first-child{
            background: #f6f6f6;
        }

        .calendar table td{
            padding: 10px;
            text-align: center;
            border: 1px solid #ccc;
        }

        .calendar table td:nth-child(7n+1){
            color: red;
        }
        .calendar table td:nth-child(7n){
            color: blue;
        }
        .calendar table td.gray {
            color: #ccc;
        }
        .calendar table td.today{
            font-weight:700;
            background: deeppink;
        }

        .calendar .footer{
            height: 25px;
            line-height: 25px;
            text-align: right;
            font-size: 12px;
        }

        #myTable ,th ,td
        {
            border: 1px solid black;
        }

        .changeColor {
            background-color: #bff0ff;
        }

    </style>

</head>
<body>

<div class="calendar">
    <div class="title">
        <a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>"><</a>
        <label><%=year%>년 <%=month%>월</label>
        <a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">></a>
    </div>

    <table>
        <thead>
        <tr>
            <td>일</td>
            <td>월</td>
            <td>화</td>
            <td>수</td>
            <td>목</td>
            <td>금</td>
            <td>토</td>
        </tr>
        </thead>
        <tbody>
        <%
            // 1일 앞 달
            Calendar preCal = (Calendar)cal.clone();
            preCal.add(Calendar.DATE, -(week-1));
            int preDate = preCal.get(Calendar.DATE);

            out.print("<tr>");
            // 1일 앞 부분
            for(int i=1; i<week; i++) {
                //out.print("<td> </td>");
                out.print("<td class='gray'>"+(preDate++)+"</td>");
            }

            // 1일부터 말일까지 출력
            int lastDay = cal.getActualMaximum(Calendar.DATE);
            String cls;
            for(int i=1; i<=lastDay; i++) {
                cls = year==ty && month==tm && i==td ? "today":"";

                out.print("<td class='"+cls+"'>"+i+"</td>");
                if(lastDay != i && (++week)%7 == 1) {
                    out.print("</tr><tr>");
                }
            }

            // 마지막 주 마지막 일자 다음 처리
            int n = 1;
            for(int i = (week-1)%7; i<6; i++) {
                // out.print("<td> </td>");
                out.print("<td class='gray'>"+(n++)+"</td>");
            }
            out.print("</tr>");
        %>
        </tbody>
    </table>

    <div class="footer">
        <a href="calendar.jsp">오늘날짜로</a>
    </div>

</div>

<table id="myTable" border=1>	<thead>	    <tr>	        <th></th>	        <th>sample1</th>	        <th>sample2</th>	        <th>sample3</th>	        <th>sample4</th>	    </tr>	</thead>	<tbody>	    <tr>	        <th>sample1</th>	        <td>Text1</td>	        <td>Text2</td>	        <td>Text3</td>	        <td>Text4</td>	    </tr>	    <tr>	        <th>sample2</th>	        <td>Text11</td>	        <td>Text22</td>	        <td>Text33</td>	        <td>Text44</td>	    </tr>	    <tr>	        <th>sample3</th>	        <td>Text111</td>	        <td>Text222</td>	        <td>Text333</td>	        <td>Text444</td>	    </tr>	    <tr>	        <th>sample4</th>	        <td>Text1111</td>	        <td>Text2222</td>	        <td>Text3333</td>	        <td>Text4444</td>	    </tr>	</tbody></table>



</body>
</html>
<script>
    //:  DOM 객체만 로드 되자마자 처리됩니다. 즉 load 보다 먼저 일어납니다.
    $(document).ready(function(){
        changeColor();
        clickTd();
        clickTr();})

    function changeColor(){
        $('#myTable tr').mouseover(function() {
            $(this).addClass('changeColor');
        }).mouseout(function() {
            $(this).removeClass('changeColor');
        });

    }

    function clickTd(){
        $("#myTable tr td").click(function(){
            var text = $(this).text();
            alert(text);
        });  }
    function clickTr(){
        $("#myTable tr").click(function(){
            var text = $(this).text();
            alert(text);
        });
    }


    $("#table tr").click(function(){
        var tdArray = new Array();
        var td = $(this).children();
        //tr에 있는 모든 데이터
        console.log($(this).text());

        //만들어둔 배열에 td값을 하나하나 담아준다.
        td.each(function(i){
            tdArray.push(td.eq(i).text());	});
        console.log(tdArray);
        var td1 = tdArray[0];
        var td2 = tdArray[1];
        var td3 = tdArray[2];
        var td4 = tdArray[3];
        var td5 = tdArray[4];
        console.log(td1);
        console.log(td2);
        console.log(td3);
        console.log(td4);
        console.log(td5);
    });



</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>