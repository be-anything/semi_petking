document.petUpdateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const newPetName = e.target.newPetName.value;
    const newPetAge = e.target.newPetAge.value;
    const newGender = document.querySelector('input[name="newGender"]:checked');
    const newNeutered = document.querySelector('input[name="newNeutered"]:checked');

    // 모두 작성했는지 확인
    if (!newPetName || !newPetAge || !newGender || !newNeutered) {
        alert('모든 항목을 입력해주세요.');
        e.preventDefault();
        return;
    }

    // 반려동물 이름 - 1글자이상
    if (!/^[가-힣A-Za-z]{1,}$/.test(newPetName)) {
        alert('이름은 1글자 이상 작성해주세요.');
        e.preventDefault();
        return;
    }

    // 반려동물 나이 - 숫자만 입력
    if (!/^[0-9][0-9]+$/.test(newPetAge)) {
        alert('숫자로 입력해주세요.');
        e.preventDefault();
        return;
    }

    // 반려동물 성별 선택 여부
    if (!newGender) {
        alert('반려동물 성별을 선택해주세요.');
        e.preventDefault();
        return;
    }

    // 반려동물 중성화 여부
    if (!newNeutered) {
        alert('반려동물 중성화 여부를 선택해주세요.');
        e.preventDefault();
        return;
    }
    console.log('New Gender:', newGender.value);
    console.log('New Neutered:', newNeutered.value);
});

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
    if (!/^[0-9][0-9]+$/.test(petAge)) {
        alert('숫자로 입력해주세요.');
        e.preventDefault();
        return;
    }
});