// options 버튼 클릭 이벤트
document.querySelectorAll(".options").forEach((option) => {
    option.addEventListener('click', (e) => {
        const selected = e.target;
        console.log(selected)
        selected.classList.toggle("bg-light-pink");
        selected.classList.toggle("text-white");
        selected.classList.toggle("selected");

        const input = selected.children[0];
        console.dir(input.checked);
        if(selected.classList.contains("selected")){
            input.checked = true;
        }
        else {
            input.checked = false;
        }
    });
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


// 메인 이미지 미리보기
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
// 추가 이미지 미리보기 및 업로드 메세지 제거하기
document.querySelectorAll(".campImg").forEach((img) => {
    img.addEventListener('change', (e) => {
        const btn = e.target;
        const img = btn.files[0];
        const imgView = btn.parentElement;
        console.log(imgView)
        const text = btn.previousElementSibling;

        const loadImg = (img) => {
            const imgReader = new FileReader();
            imgReader.onload = (e) => {
                imgView.style["backgroundImage"] =  "url('" + e.target.result + "')";
            };
            imgReader.readAsDataURL(img);
            text.style.display = 'none';
        }
        loadImg(img);
    });
})

// 부가정보 수정 비동기처리
document.querySelector("#updateDetailBtn").addEventListener('click', (e) => {
    const btn = e.target;
    console.log(btn);
    const frm = document.campDetailUpdateFrm;
    const frmData = new FormData(frm);

    for (var pair of frmData.entries()) {
        console.log(pair[0]+ ', ' + pair[1]);
    }

    $.ajax({
        url : `${contextPath}/camp/campDetailUpdate`,
        method: 'post',
        data: frmData,
        contentType: false,
        processData: false,
        success(response) {
            const {msg} = response;
            alert(msg);
        },
        complete() {
        }
    });
});













// 기본정보 수정 비동기처리
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