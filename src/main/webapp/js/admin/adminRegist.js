const frm = document.campUpdateFrm;
const id = frm.id;
const campState = frm.campState;
const campMsg = frm.campMsg;
const delfrm = document.campdeleteFrm;
document.querySelector("#updateBtn").addEventListener('click', (e) => {
    const {value} = e.target;
    campState.value = value;
    console.log(e.target);
    console.log(value);

    frm.submit();

});
document.querySelector("#deleteBtn").addEventListener('click',(e) => {
    const {value} = e.target;
    campState.value = value;
    console.log(e.target);
    console.log(value);
    frm.submit();
});
document.querySelector("#CompanionBtn").addEventListener('click', (e) => {
    const {value} = e.target;
    campState.value = value;
    console.log(e.target);
    console.log(value);
    if(!campMsg.value){
        alert('반려사유를 입력해주세요');
        e.preventDefault();
        return;
    }

    frm.submit();
});

// 탭 메뉴 이동
const tabBtns =  document.querySelectorAll(".tabBtn");
tabBtns.forEach((tab, index) => {
    tab.addEventListener('click', (e) => {
        const tabBtn = e.target;
        const tabBtnA = [...tabBtn.children];

        // 기존 클래스 지우기
        tabBtns.forEach((btn) => {
            btn.children[0].classList.remove("text-white");
            btn.children[0].classList.remove("bg-green");
        });

        // 활성화 디자인 추가하기
        // inline-block p-4 rounded-t-lg text-white bg-green active px-5
        tabBtn.classList.add("text-white");
        tabBtn.classList.add("bg-green");

        const formList = [...document.querySelectorAll(".forms")];
        formList.forEach((form) => {
            form.classList.remove("active");
        });
        formList[index].classList.add("active");
    })
});