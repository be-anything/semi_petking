//임시 전역변수
let today = new Date();
let date = new Date();

function beforem() //이전 달을 today에 값을 저장
{
    today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
    build(); //만들기
}

function nextm()  //다음 달을 today에 저장
{
    today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    build();
}




$(document).ready(function()
{
    //외부 리소스 및 이미지 로딩과는 상관없이 dom데이터만 로딩완료 되면 바로 실행이 되는 함수이다.
    // (window.onload보다 빠르게 실행된다)
    build();
    clickTd2();
    //clickEvent();
    $('#testTable').css('border','1px solid yellow');

})

function clickEvent()
{
    console.log("clickEvent()-----------");
        // let str ="";
        // let tdArr = new Array();//배열선언
        //
        // // 현재 클릭된 Row(<tr>)
        // let tr = $(this);
        // let td = tr.children();
        // console.log("클릭한 Row의 모든 데이터 : "+tr.text());
        // console.log("tdtdtdtdtd : "+td.text());
        // console.log("테이블 tr 클릭,,,,,,,,,,,,,,,,,");


        $("#calendarTest tr").click(function(){
            var tdArray = new Array();
            var td = $(this).children();
            //tr에 있는 모든 데이터
            console.log($(this).text());

            //만들어둔 배열에 td값을 하나하나 담아준다.
            td.each(function(i)
            {
                tdArray.push(td.eq(i).text());	});
            console.log(tdArray);
            var td1 = tdArray[0];
            var td2 = tdArray[1];
            var td3 = tdArray[2];
            var td4 = tdArray[3];
            var td5 = tdArray[4];
            var td6 = tdArray[5];
            var td7 = tdArray[6];
            console.log("td1/"+td1);
            console.log("td2/"+td2);
            console.log("td3/"+td3);
            console.log("td4/"+td4);
            console.log("td5/"+td5);
            console.log("td6/"+td5);
            console.log("td7/"+td5);
        });






    // $("#calendarTest tr td").click(function(){
    //     var text = $(this).text();
    //     alert(text+"클릭!!!!!");
    // });


}
function build()
{
    console.log("build.....");
    var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
    var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날
    var tbcal = document.getElementById("calendarTest"); // 테이블 달력을 만들 테이블
    var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
    yearmonth.innerHTML = today.getFullYear() + "년 "+ (today.getMonth() + 1) + "월"; //년도와 월 출력

    if(today.getMonth()+1==12) //  눌렀을 때 월이 넘어가는 곳
    {
        before.innerHTML=(today.getMonth())+"월";
        next.innerHTML="1월";
    }
    else if(today.getMonth()+1==1) //  1월 일 때
    {
        before.innerHTML="12월";
        next.innerHTML=(today.getMonth()+2)+"월";
    }
    else //   12월 일 때
    {
        before.innerHTML=(today.getMonth())+"월";
        next.innerHTML=(today.getMonth()+2)+"월";
    }

// 남은 테이블 줄 삭제
    while (tbcal.rows.length > 2)
    {
        tbcal.deleteRow(tbcal.rows.length - 1);
    }
    var row = null;
    row = tbcal.insertRow();
    var cnt = 0;

// 1일 시작칸 찾기
    for (i = 0; i < nMonth.getDay(); i++)
    {
        cell = row.insertCell();
        cnt = cnt + 1;
    }

// 달력 출력
    for (i = 1; i <= lastDate.getDate(); i++) // 1일부터 마지막 일까지
    {
        cell = row.insertCell();
        cell.innerHTML = i;
        cnt = cnt + 1;
        if (cnt % 7 == 1) {//일요일 계산
            cell.innerHTML = "<font color=#FF9090>" + i//일요일에 색
        }
        if (cnt % 7 == 0) { // 1주일이 7일 이므로 토요일 계산
            cell.innerHTML = "<font color=#7ED5E4>" + i//토요일에 색
            row = calendarTest.insertRow();// 줄 추가
        }
        if(today.getFullYear()==date.getFullYear()&&today.getMonth()==date.getMonth()&&i==date.getDate())
        {
            cell.bgColor = "#BCF1B1"; //오늘날짜배경색
        }
    }
}


//
document.querySelector("#btn-search").addEventListener('click',(e)=>{
    console.log("테스트용 - 14번 캠핑장 객실만 조회");
    //const celebId = document.querySelector("#id").value;
    //첫날
    const firstDay = document.querySelector("#datepicker1").value;
    //마지막날
    const lastDay = document.querySelector("#datepicker2").value;
    //임시, 객실 검색을 위한 특정 캠프 아이디를 넘겨준다.
    const campId = document.querySelector("#campId").value;
    console.log("조회할 날짜 첫날 : "+firstDay);
    console.log("조회할 날짜 마지막 날 : "+lastDay);
    console.log("객실 검색할 캠프 아이디 : "+campId);
    console.log(firstDay.length);
    console.log(lastDay.length);

    if(firstDay.length<1 || lastDay.length<1 )
    {
        alert(`날짜를 선택해주세요.`);
        return;
    }
    //혹은 lastDay가 firstDay와 같거나 작아서도(과거) 안된다. 무조건 1박이상이라 가정.
    var date1 = new Date($("#datepicker1").datepicker("getDate"));
    var date2 = new Date($("#datepicker2").datepicker("getDate"));
    if(date2 <= date1)
    {
        alert(`숙박기간을 올바르게 선택해주세요.(최소 1박이상)`);
        return;
    }


    $.ajax({
        url:`${contextPath}/reservation/reservationRoomSearch`,
        data:{
            campId:campId,
            firstDay:firstDay,
            lastDay:lastDay
        },
        success(rooms){
            //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
            console.log(rooms);

            if(rooms.length==0)
            {
                alert(`해당하는 객실은 0개입니다.`);
                return;
            }
            const tbody = document.querySelector("#roomSearchResult tbody");
            const thead =  document.querySelector("#roomSearchResult thead");
            thead.innerHTML= '';
            tbody.innerHTML= '';

            thead.innerHTML= ` 
           
                <tr>
             
            <tr>`;
            tbody.innerHTML= '';

            rooms.forEach(({id,roomName,roomIntro,roomType,roomDefaultPerson,roomMaximumPerson,roomDefaultFee,roomOverFee,roomRenamedImg})=>
            {

                tbody.innerHTML += `
                    <tr>
                        <td><img id="roomImage" class="w-[200px] h-[100px]" src="${contextPath}/upload/room/${roomRenamedImg}"></td>
                        <td>${id}</td>
                        <td>${roomName}</td>
                        <td>${roomType}</td>
                        <td>${roomDefaultPerson}명</td>
                        <td>${roomMaximumPerson}명</td>
                        <td>${roomDefaultFee}원</td>
                        <td>${roomOverFee}원</td>
                        <td><button id="btnReserve"
                        onclick="location.href = '${contextPath}/reservation/ReservationProgress?id=${id}'"
                        class="hover:text-white bg-white text-black border border-gray2 hover:bg-green font-medium rounded-full text-sm px-20 py-2.5 text-center me-2 mb-2"> 
                        예약</button></td>
                    </tr>`;
            });
        },
        error()
        {
            console.log("reservation room  - search error");
        }
        ,
        complete : function ()
        {
            //여기서 제이쿼리로 tbody 색상을 변경해본다.
            console.log("끝났음!");
            $("roomSearchResult").css("background",'red');
        }

    });
});
//


//https://www.phpschool.com/gnuboard4/bbs/board.php?bo_table=qna_html&wr_id=240919
//동적으로 생성된 버튼은 그 자체로 click이벤트가 먹히지 않기 때문에 미리 선언해둔 상위 객체에 바인딩[on]해야 한다고 한다.

//https://code-study.tistory.com/38 버튼 순서 찾기..
//https://www.phpschool.com/gnuboard4/bbs/board.php?bo_table=qna_html&wr_id=257125&page=600
$('#roomSearchResult').on('click','#btnReserve',function () {
    let num = $('roomSearchResult > tbody > tr').index(this);
    console.log("예약버튼누르기1.." + $(this).parent().parent().index());
    console.log("예약버튼누르기2.." + $(this).parent().parent().html());

})


function changeColor() {
    $('#myTable tr').mouseover(function () {
        $(this).addClass('changeColor');
    }).mouseout(function () {
        $(this).removeClass('changeColor');
    });

}

// function clickTd(){
//     $("#myTable td").click(function(){
//         var text = $(this).text();
//         alert(text+"클릭!");
//     });  }
// function clickTr(){
//     $("#myTable tr").click(function(){
//         var text = $(this).text();
//         //alert(text);
//     });
// }

function clickTd2(){
    $("#calendarTest td").click(function(){
        var text = $(this).text();
        console.log("날짜클릭::"+text);
        //모든 tr 배경색을 white로.
        //$("#calendarTest td").css("background","white");
        //if(parseInt(text))
        //$(this).css("background","red"); //선택한 셀 색상 변경
        // $(this).siblings()
    });
}

//https://www.codingfactory.net/10265
//https://tcpschool.com/jquery/jq_elementTraversing_SiblingTraversing


//
// $(function(){
//     $('#datepicker').datepicker();
// })

//https://jqueryui.com/datepicker/
$(function() {
    //input을 datepicker로 선언
    $("#datepicker1,#datepicker2").datepicker({
        dateFormat: 'yy-mm-dd' //달력 날짜 형태 년/월/일로 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
        ,changeYear: true //option값 년 선택 가능
        ,changeMonth: true //option값  월 선택 가능
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
        //,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
        //,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
        //,buttonText: "선택" //버튼 호버 텍스트
        ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
        ,minDate: 0 //"-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+2M" //2달 후 까지 막아둠 //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
    });
    //초기값을 오늘 날짜로 지정한다.
    $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
});