const selectElement = document.getElementById('personSelect');

//인원
const roomDefaultPerson = parseInt(document.getElementById('myElement1').getAttribute('data-my-value1'));
const roomMaximumPerson = parseInt(document.getElementById('myElement2').getAttribute('data-my-value2'));

//요금
const roomDefaultFee = parseInt(document.getElementById('myElement3').getAttribute('data-my-value3'));
const roomOverFee = parseInt(document.getElementById('myElement4').getAttribute('data-my-value4'));

for (let i = roomDefaultPerson; i <= roomMaximumPerson; i++) {
    let optionElement = document.createElement('option');
    optionElement.value = i;
    optionElement.text = i;
    selectElement.add(optionElement);
}
//personSelect 셀렉트박스 값에 따라 요금을 변경한다. showMeTheMoney
let selectedValue = document.getElementById('personSelect').value;
function showMeTheMoney()
{
    selectedValue = document.getElementById('personSelect').value;
    console.log("셀렉트박스 선택 : "+ selectedValue);
    document.getElementById('totalFee').innerText = roomDefaultFee + ((selectedValue-roomDefaultPerson) * roomOverFee);
}

//예약버튼을 눌렀을때.
document.querySelector(".reservationBtn").addEventListener('click', (e) => {
    console.log("============예약하기버튼클릭=============");
    const totalFee_before = document.getElementById('totalFee').innerText; //최종요금 포인트 적용 전
    const campId = document.getElementById('campId').innerText; //캠핑장 아이디
    const roomId = document.getElementById('roomId').innerText; //룸 아이디
    const userId = document.getElementById('userId').innerText; //유저 아이디
    const firstDay = document.getElementById('firstDay').innerText; //숙박 첫날
    const lastDay = document.getElementById('lastDay').innerText; //숙박 마지막날

    console.log("totalFee_before : "+ totalFee_before);
    console.log("personSelect : "+ selectedValue);
    $.ajax({
    url: `${contextPath}/reservation/reservationCreate`,  // 실제 서버 엔드포인트로 변경
    method: 'post',
    data:{
        campId:campId,
        roomId:roomId,
        userId:userId,
        firstDay:firstDay,
        lastDay:lastDay,
        totalFee_before:totalFee_before, //최종요금(인원고려함)
        personSelect:selectedValue, //선택한 인원수
    },
    success(response){
        //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
            console.log("~~~~예약 성공~~~~");
            alert(`예약이 완료 되었습니다!`);
            window.location.href = `${contextPath}/user/userReservation`;
    },
    error(error) {
        console.error('Ajax request failed:', error);
    }
});
});

//포인트 버튼 클릭시
document.querySelector("#btn_usePoint").addEventListener('click', (e) => {
    const userPoint = parseInt(document.getElementById('userPoint').innerText);
    const totalFee_before = document.getElementById('totalFee').innerText; //최종요금 포인트 적용 전
    const usePoint = document.getElementById('usePoint').value;

    if (parseInt(usePoint.value) > userPoint) {
        usePoint.value = userPoint;
        alert(userPoint + ' 까지만 가능합니다.');
    }
    console.log("totalFee_before - key up" + totalFee_before);
    console.log("usePoint - key up" + usePoint);
    const totalFee_after = parseInt(totalFee_before) - parseInt(usePoint); //최종요금 포인트 적용 후
    console.log("totalFee_after - key up" + totalFee_after);
    document.getElementById('totalFee').innerText = totalFee_after + ` 원`;
});