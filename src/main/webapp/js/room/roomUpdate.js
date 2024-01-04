//0103 객실 수정 관련 js
console.log("roomUpdate.js.....");

//사진관련
function getImageFiles(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;
    const imagePreview = document.querySelector('.image-preview');
    const docFrag = new DocumentFragment();

    if ([...files].length >= 6) {
        alert('이미지는 최대 5개 까지 업로드가 가능합니다.');
        return;
    }

    // 파일 타입 검사
    [...files].forEach(file => {
        if (!file.type.match("image/.*")) {
            alert('이미지 파일만 업로드가 가능합니다.');
            return
        }

        // 파일 갯수 검사
        if ([...files].length < 6) {
            uploadFiles.push(file);
            const reader = new FileReader();
            reader.onload = (e) => {
                const preview = createElement(e, file);
                imagePreview.appendChild(preview);
            };
            reader.readAsDataURL(file);
        }
    });
}

function roomUpdateFrm(e, file) {
    const li = document.createElement('li');
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('data-file', file.name);
    li.appendChild(img);
    console.log(img);
    return li;
}

const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.upload');

upload.addEventListener('click', () => realUpload.click());

realUpload.addEventListener('change', getImageFiles);


/**
 * 0102
 * 등록하기 버튼으로 제출 시 발생하는 이벤트
 * 게시글과 동일하게 입력란에 스크립트 태그 방지.
 */

document.roomUpdateFrm.addEventListener('submit',(e)=> {
    const frm = e.target;
    const roomName = frm.roomName;
    const roomIntro = frm.roomIntro;

    //제목 유효성 검사
    if(!/^.+$/.test(roomName.value.trim()))
    {
        alert('객실명을 반드시 작성해주세요.');
        e.preventDefault();
        return; //제출막기..
    }

    //내용 유효성 검사
    //정규표현식의 .에는 \n이 포함되지 않는다.
    if(!/^(.|\n)+$/.test(roomIntro.value.trim()))
    {
        alert('객실소개를  반드시 작성해주세요.');
        e.preventDefault();
        return; //제출막기..
    }
    // alert('제출막기');
    // e.preventDefault();
    // return; //제출막기..
});