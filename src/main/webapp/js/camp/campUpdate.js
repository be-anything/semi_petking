// 추가 이미지 미리보기 및 업로드 메세지 제거하기
window.addEventListener('DOMContentLoaded', () => {
   previewImg();
});

const previewImg = () => {
    document.querySelectorAll(".campImg").forEach((img) => {
        img.addEventListener('change', (e) => {
            const btn = e.target;
            const img = btn.files[0];
            const imgView = btn.parentElement;
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
}




// options 버튼 클릭 이벤트
document.querySelectorAll(".options").forEach((option) => {
    option.addEventListener('click', (e) => {
        const selected = e.target;
        selected.classList.toggle("bg-green");
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
const tabBtns = document.querySelectorAll(".tabBtn");
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

//
document.querySelectorAll(".delbtn").forEach((btn, _index) =>{
   btn.addEventListener('click', (e) => {
       const index = _index + 1;
       const delBtn = e.target;
       const resetInput = delBtn.parentElement;
       const attachId = e.target.nextElementSibling;
       const reset = attachId.nextElementSibling;
       console.log(attachId);

       if(confirm("삭제하시겠습니까?")){
            $.ajax({
                url: `${contextPath}/camp/campAttachDelete`,
                data: {
                    attachId : attachId.value
                },
                method: 'post',
                success(response){
                    // reset.style.backgroundImage = '';
                    // delBtn.classList.add('hidden');
                    // ajax reload
                    // location.reload();
                    resetInput.innerHTML = '';
                    resetInput.innerHTML = `<label for="campImg${index}"
                                           style="background-size: cover; background-position: center"
                                           class="flex flex-col items-center justify-center w-full h-60 border-2 border-gray-300 border-dashed rounded-lg cursor-pointer bg-gray-50 dark:hover:bg-bray-800 dark:bg-gray-700 hover:bg-gray-100 dark:border-gray-600 dark:hover:border-gray-500 dark:hover:bg-gray-600">
                                        <div class="flex flex-col items-center justify-center pt-5 pb-6">
                                            <svg class="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400" aria-hidden="true"
                                                 xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                                      stroke-width="2"
                                                      d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                            </svg>
                                            <p class="mb-2 text-sm text-gray-500 text-center"><span class="font-semibold">사진 업로드<br></span>
                                            </p>
                                        </div>
                                        <input id="campImg${index}" name="campDetailImg"
                                               type="file" class="campImg hidden campAttach"/>
                                    </label>`;
                    previewImg();
                },
                error(jqXHR, textStatus, errorThrown) {
                    console.error("AJAX 오류 발생: ", textStatus, errorThrown);
                }
            })
       }
   });
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
        };
        imgReader.readAsDataURL(img);
    }
    loadImg(img);
});


// 부가정보 수정 비동기처리
document.querySelector("#updateDetailBtn").addEventListener('click', (e) => {
    const btn = e.target;
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
            // ajax reload
            // location.reload();
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
    const frmData = new FormData(frm);
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