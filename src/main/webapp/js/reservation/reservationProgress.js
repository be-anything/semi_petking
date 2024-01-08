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
function showMeTheMoney()
{
    let selectedValue = document.getElementById('personSelect').value;
    console.log("셀렉트박스 선택 : "+ selectedValue);
    document.getElementById('totalFee').innerText = roomDefaultFee + ((selectedValue-roomDefaultPerson) * roomOverFee) +` 원`;
}

//예약버튼을 눌렀을때.
document.querySelector(".reservationBtn").addEventListener('click', (e) => {
    console.log("예약하기버튼클릭,,,,");
});