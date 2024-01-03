document.memberUpdateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const name = frm.name;

    // 이름 - 한글 2글자 이상
    if(!/^[가-힣]{2,}$/.test(name.value)) {
        alert('이름은 한글 2글자 이상 작성하세요.');
        e.preventDefault();
        return;
    }
});