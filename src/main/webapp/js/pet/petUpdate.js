document.petUpdateFrm.onsubmit = (e) => {
    const frm = e.target;
    const newPetName = e.target.newPetName.value;
    const newPetAge = e.target.newPetAge.value;
    const newGender = document.querySelector('input[name="newGender"]:checked');
    const newNeutered = document.querySelector('input[name="newNeutered"]:checked');

    // 반려동물 이름 - 1글자 이상
    if (!/^[가-힣A-Za-z]{1,}$/.test(newPetName)) {
        alert('이름은 1글자 이상 작성해주세요.(한글 & 영대/소문자');
        e.preventDefault();
        return false;
    }

    // 반려동물 나이 - 숫자만 입력
    if (!/^\d{1,2}$/.test(newPetAge)) {
        alert('숫자로 입력해주세요 (최대 두 자리).');
        e.preventDefault();
        return false;
    }

    // 반려동물 성별 선택 여부
    if (!newGender) {
        alert('반려동물 성별을 선택해주세요.');
        e.preventDefault();
        return false;
    }

    // 반려동물 중성화 여부
    if (!newNeutered) {
        alert('반려동물 중성화 여부를 선택해주세요.');
        e.preventDefault();
        return false;
    }
};