// 추가 이미지 미리보기 및 업로드 메세지 제거하기
window.addEventListener('DOMContentLoaded', () => {
    previewImg();
});

const previewImg = () => {
    document.querySelectorAll(".profileImg").forEach((img) => {
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

// 부가정보 수정 비동기처리
document.querySelector(".profileUpdateBtn").addEventListener('click', (e) => {
    const btn = e.target;
    const frm = document.profileUpdateFrm;
    const frmData = new FormData(frm);
    const img = btn.parentElement;
    const sideProfile = document.querySelector("#sideProfile");
    for (var pair of frmData.entries()) {
        console.log(pair[0]+ ', ' + pair[1]);
    }

    $.ajax({
        url : `${contextPath}/user/userProfileUpdate`,
        method: 'post',
        data: frmData,
        contentType: false,
        processData: false,
        success(response) {
            const {msg} = response;
            alert(msg);
            const { renamedProfileName } = response;
            // img.style.backgroundImage = `url(${contextPath}/upload/user/${renamedProfileName})`;
            // sideProfile.style.backgroundImage = `url(${contextPath}/upload/user/${renamedProfileName})`;
            // ajax reload
            location.reload();
        },
        complete() {

        }
    });
});