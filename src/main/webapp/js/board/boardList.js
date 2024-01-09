// 탭 메뉴 이동
const tabBtns =  document.querySelectorAll(".tabBtn");
tabBtns.forEach((tab, index) => {
    tab.addEventListener('click', (e) => {
        const tabBtn = e.target;
        const tabBtnA = [...tabBtn.children];

        // 기존 클래스 지우기
        tabBtns.forEach((btn) => {
            btn.children[0].classList.remove("text-amber-700");
            btn.children[0].classList.remove("bg-amber-300");
        });

        // 활성화 디자인 추가하기
        // inline-block p-4 rounded-t-lg text-white bg-green active px-5
        tabBtn.classList.add("text-amber-700");
        tabBtn.classList.add("bg-amber-300");

        const formList = [...document.querySelectorAll(".forms")];
        formList.forEach((form) => {
            form.classList.remove("active");
        });
        formList[index].classList.add("active");
    })
});