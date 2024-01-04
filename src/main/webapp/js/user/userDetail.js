document.userUpdateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const nickname = frm.nickname;

    // 닉네임 - 2글자 이상
    if(!/^[가-힣A-Za-z]{2,}$/.test(nickname.value)) {
        alert('닉네임은 2글자 이상 작성하세요.');
        e.preventDefault();
        return;
    }
});