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


// 이미지 미리보기
document.querySelector("#campImg").addEventListener('change', (e) => {
    const btn = e.target;
    const img = btn.files[0];
    const imgView = document.querySelector("#img-view");

    // console.dir(e.target);
    // console.dir(img);

    const loadImg = (img) => {
        const imgReader = new FileReader();
        imgReader.onload = (e) => {
            imgView.src = e.target.result;
            console.log(imgView.src);
        };
        imgReader.readAsDataURL(img);
    }
    loadImg(img);
});

// 내용 수정 비동기처리
document.campUpdateFrm.addEventListener('submit', (e) => {
    // 폼 제출 막기
    e.preventDefault();

    const frm = e.target;
    console.log(frm)
    const frmData = new FormData(frm);
    console.log(frmData);
    $.ajax({
        url : `${contextPath}/camp/campUpdate`,
        method: 'post',
        data: frmData,
        contentType: false,
        processData: false,
        success(response) {
            const {msg} = response;
            alert(msg);
        },
        complete() {
            // frm.reset();
        }
    })
});