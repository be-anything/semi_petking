//임시 전역변수
let today = new Date();
let date = new Date();
const tbody = document.querySelector("#roomSearchResult tbody");
const thead =  document.querySelector("#roomSearchResult thead");
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
        console.log('날짜를 제대로 선택 안했을 경우 alert을 띄워준다.');
        return;
    }
    console.log('==위에는 날짜가 텅 비었을 경우?');
    //혹은 lastDay가 firstDay와 같거나 작아서도(과거) 안된다. 무조건 1박이상이라 가정.
    var date1 = new Date($("#datepicker1").datepicker("getDate"));
    var date2 = new Date($("#datepicker2").datepicker("getDate"));
    if(date2 <= date1)
    {
        alert(`숙박기간을 올바르게 선택해주세요.(최소 1박이상)`);
        return;
    }

    //get방식으로 데이터 요청
    $.ajax({
        url:`${contextPath}/reservation/reservationRoomSearch`,
        data:{
            campId:campId,
            firstDay:firstDay,
            lastDay:lastDay
        },
        //위의 결과값을 받았을 경우.........
        success(rooms){
            //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
            console.log(rooms);

            if(rooms.length==0)
            {
                //사실은 예약이 겹치지않는, 즉 내가 선택한 구간에 예약이 있는 방이 0개인 결과이다.
                alert(`해당하는 객실은 0개입니다.`);
                return;
            }
            // const tbody = document.querySelector("#roomSearchResult tbody");
            // const thead =  document.querySelector("#roomSearchResult thead");
            // thead.innerHTML= '';
            // tbody.innerHTML= '';

            thead.innerHTML= ` 
           
                <tr>
             
            <tr>`;
            tbody.innerHTML= '';

            rooms.forEach(({id,roomName,roomIntro,roomType,roomDefaultPerson,roomMaximumPerson,roomDefaultFee,roomOverFee,roomRenamedImg})=>
            {
                tbody.innerHTML += `
                    <tr>
                        <td><img id="roomImage" class="w-[200px] h-[100px]" src="${contextPath}/upload/room/${roomRenamedImg}"></td>
                        <td id="roomId" hidden>${id}</td>
                        <td>${roomName}</td>
                        <td>${roomType == 1 ? '오토캠핑' :
                              roomType == 2 ? '글램핑' :
                              roomType == 3 ? '카라반' :
                              roomType == 4 ? '룸' : 'Invalid Type'}
                        </td>
                        <td>기본 ${roomDefaultPerson}명</td>
                        <td>최대 ${roomMaximumPerson}명</td>
                        <td>기본 ${roomDefaultFee}원</td>
                        <td>초과 인당 ${roomOverFee}원</td>
                        <td><button id="btnReserve"
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

//onclick="location.href = '${contextPath}/reservation/ReservationProgress?id=${id}'"

//https://www.phpschool.com/gnuboard4/bbs/board.php?bo_table=qna_html&wr_id=240919
//동적으로 생성된 버튼은 그 자체로 click이벤트가 먹히지 않기 때문에 미리 선언해둔 상위 객체에 바인딩[on]해야 한다고 한다.

//https://code-study.tistory.com/38 버튼 순서 찾기..
//https://www.phpschool.com/gnuboard4/bbs/board.php?bo_table=qna_html&wr_id=257125&page=600
$('#roomSearchResult').on('click','#btnReserve',function () {

    let num = $('roomSearchResult > tbody > tr').index(this);
    console.log("예약버튼누르기1.." + $(this).parent().parent().index());
    console.log("예약버튼누르기2.." + $(this).parent().parent().html());

    //document.getElementById('btnReserve').id = 'newId';
    const roomNumber = $(this).parent().parent().find('#roomId').html();
    console.log("내가 선택한 객실 번호:", roomNumber);
    const firstDay = document.querySelector("#datepicker1").value;
    //마지막날
    const lastDay = document.querySelector("#datepicker2").value;
    console.log("222222222222222내가 선택한 첫날:", firstDay);
    console.log("2222222222222222내가 선택한 마지막날:", lastDay);

    if(firstDay.length<1 || lastDay.length<1 )
    {
        alert(`날짜를 선택해주세요.`);
        return;
    }
    // thead.innerHTML= '';
    // tbody.innerHTML= '';
    // thead.innerHTML= `
    //             <tr>
    //         <tr>`;
    //id가 btnReserve인 버튼 찾기
    // id가 "button01"인 버튼에 속성 추가
    //button01.attr('onClick', '"location.href = \'${contextPath}/reservation/ReservationProgress?id=${id}\'"');
    //myElement.find('btnReserve').attr('onClick', '"location.href = \'${contextPath}/reservation/ReservationProgress?id=${id}\'"');
    // 수정된 HTML 문자열을 다시 설정

    // 확인을 위해 콘솔에 출력
    // console.log("수정된 html"+$(this).parent().parent().html());
    //
    //
    // tbody.innerHTML= '';
    //     tbody.innerHTML += $(this).parent().parent().html();
        // $('#data21').html('Updated Data 21');
        //버튼 btnReserve의 내용 바꾸기



    $.ajax({
        url: `${contextPath}/reservation/ReservationProgress`,  // 실제 서버 엔드포인트로 변경
        method: 'post',
        data:{
            roomId:roomNumber,
            firstDay:firstDay,
            lastDay:lastDay
        },
        success: function(response) {
            console.log("성공했나????????????????????")
            window.location.href = `${contextPath}/reservation/ReservationProgress`;
        },
        error: function(error) {
            console.error('Ajax request failed:', error);
        }
    });

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
    $("#datepicker1").datepicker({
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
        ,minDate: new Date(),
          onSelect: function(dateStr)
        {
            $("#datepicker2").val(dateStr);
            $("#datepicker2").datepicker("option",{ minDate: new Date(dateStr)})
        }

        ,maxDate: "+2M" //2달 후 까지 막아둠 //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
    });
    //초기값을 오늘 날짜로 지정한다.
    $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
});

$(function() {
    //input을 datepicker로 선언
    $("#datepicker2").datepicker({
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
        //,minDate: 0
        ,maxDate: "+2M" //2달 후 까지 막아둠 //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
    });

    //const firstDay = document.querySelector("#datepicker1").value;
    //초기값을 오늘 날짜로 지정한다.
    $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
});