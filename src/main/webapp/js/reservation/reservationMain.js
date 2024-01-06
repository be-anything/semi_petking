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
document.querySelector("#btn-check").addEventListener('click',(e)=>{
    console.log("테스트용 조회 버튼 클릭!");
    console.log("테스트용 - 4번 캠핑장 객실만 조회");
    //const celebId = document.querySelector("#id").value;
    const campId=11;
    console.log("내가 검색할 캠핑장 아이디 값(campId) : "+campId);

    $.ajax({
        url:`${contextPath}/reservation/reservationRoomSearch`,
        data:{
            campId:campId.value
        },
        success(rooms){
            //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
            console.log(rooms);
            const tbody = document.querySelector("#celebs tbody");
            tbody.innerHTML= '';

            rooms.forEach(({roomName,roomType,roomDefaultPerson,roomMaximumPerson})=>
            {
                    tbody.innerHTML += `
                    <tr>
                        <td>${roomName}</td>
                        <td>${roomType}</td>
                        <td>${roomDefaultPerson}</td>
                        <td>${roomMaximumPerson}</td>
                    </tr>`;


            });
        },
        //<td><img src="${contextPath}/images/celeb/${profile}"></td>

        // success(rooms){
        //     //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
        //     console.log("------------------success------------------");
        //     console.log(rooms);
        //
        //     //numbers.forEach(function(number) {
        //     //     console.log(number);
        //     // });
        //     if(rooms)
        //     {
        //         // rooms.forEach(({
        //         //                    roomAttachs: {
        //         //                        id,
        //         //                        roomid,
        //         //                        origin,
        //         //                        rename
        //         //                    }
        //         //                     // ,
        //         //                     //  roominfo: {
        //         //                     //      rid,
        //         //                     //      rcampid,
        //         //                     //      rname
        //         //                     //  }
        //         //                  }) => {
        //         //     // console.log(`사진원본명 : ${origin}, 사진리네임명 : ${rename}, 방아이디 : ${rid}, 캠핑장번호 : ${rcampid},방이름 : ${rname}`);
        //         //     console.log(`사진원본명 : ${origin}, 사진리네임명 : ${rename}`);
        //         // });
        //         const {roomAttachs,id,roomName,roomInfo} = rooms;
        //         const table = document.querySelector("table#rooms");
        //         table.querySelector(".room-id").innerHTML=id;
        //         table.querySelector(".room-camp-id").innerHTML=roomInfo;
        //         //table.querySelector(".room-name").innerHTML = `<img src="${contextPath}/images/celeb/${profile}"/>`;
        //         table.querySelector(".room-name").innerHTML=roomName;
        //
        //     }
        //     else
        //     {
        //         alert(`해당하는 객실이 없습니다.`);
        //     }
        // },
        error()
        {
            console.log("reservation room search error");
        }

    });
});


function changeColor(){
    $('#myTable tr').mouseover(function() {
        $(this).addClass('changeColor');
    }).mouseout(function() {
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