console.log("roomList js....");


document.querySelector("#btnRoomDelete").addEventListener('click',()=>
{
    console.log("삭제버튼 클릭!....");
    const deleteTF = confirm('객실을 삭제하시겠습니까?');
    console.log(deleteTF);
    if(deleteTF)
    {
        const form = document.getElementById("#roomDeleteFrm");

        const id = document.querySelector("#myForm #id");
        const campId = document.querySelector("#myForm #campId");

        console.log(id.value);
        console.log(campId.value);
    }
});