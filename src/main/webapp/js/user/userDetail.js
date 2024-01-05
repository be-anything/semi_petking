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

document.userPetFrm.addEventListener('submit', (e) => {
   const frm = e.target;
   const name = frm.name;
   const age = frm.age;

   // 반려동물 이름 - 1글자이상
   if (!/^[가-힣A-Za-z]{1,}$/.test(name.value)) {
       alert('이름은 1글자 이상 작성해주세요.');
       e.preventDefault();
       return;
   }
   if (!/^[1-9]$/.test(age.number)) {
       alert('나이를 입력해주세요.');
       e.preventDefault();
       return;
   }
});