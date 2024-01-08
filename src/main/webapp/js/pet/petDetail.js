document.InsertPetForm.addEventListener('submit', (e) => {
    const frm = e.target;
    const petName = e.target.petName.value;
    const petAge = e.target.petAge.value;
    const petGender = e.target.petGender.value;
    const neutered = e.target.neutered.value;

    // 반려동물 이름 - 1글자이상
    if (!/^[가-힣A-Za-z]{1,}$/.test(petName)) {
        alert('이름은 1글자 이상 작성해주세요.');
        e.preventDefault();
        return;
    }

    // 반려동물 나이 - 숫자만 입력
    if (!/^\d{1,2}$/.test(petAge)) {
        alert('숫자로 입력해주세요 (최대 두 자리).');
        e.preventDefault();
        return;
    }
});