document.userUpdateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const phone = frm.phone;

    // 전화번호 - 2글자 이상
    if(!/^\d{11,}$/.test(phone.value)) {
        alert('전화번호는 11개의 숫자로만 입력해주세요');
        e.preventDefault();
        return;
    }
});