// 아이디 중복 검사
document.querySelector("#id").addEventListener('keyup', (e) => {
    const value = e.target.value;
    console.log(value);

    const guideOk = document.querySelector(".guide.ok");
    const guideError = document.querySelector(".guide.error");
    const idValid = document.querySelector("#idValid");

    if (/^\w{4,}$/.test(value)) {
        $.ajax({
            url : `${contextPath}/user/UserCheckIdDuplicate`,
            data : {
                id : value
            },
            success(response) {
                const {result} = response;
                if (result) {
                    // 아이디 사용가능한 경우
                    guideError.classList.add('hidden');
                    guideOk.classList.remove('hidden');
                    idValid.value = 1;
                } else {
                    // 아이디 이미 사용중인 경우
                    guideOk.classList.add('hidden');
                    guideError.classList.remove('hidden');
                    idValid.value = 0;
                }
            }
        });
    } else {
        // 다시쓰기하는 경우
        guideOk.classList.add('hidden');
        guideError.classList.add('hidden');
        idValid.value = 0; // 리셋해줌
    }
});

// 이메일 중복검사
document.querySelector("#email").addEventListener('keyup', (e) => {
    const value = e.target.value;
    console.log(value);

    const guideOk = document.querySelector(".guide.ok");
    const guideError = document.querySelector(".guide.error");
    const emailValid = document.querySelector("#emailValid");

    if (!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(value)) {
        $.ajax({
            type : post,
            url : `${contextPath}/user/UserCheckEmailDuplicateController`,
            data : {
                email : value
            },
            success(response) {
                const {result} = response;
                if (result) {
                    // 이메일 사용가능한 경우
                    guideError.classList.add('hidden');
                    guideOk.classList.remove('hidden');
                    emailValid.value = 1;
                } else {
                    // 이메일 이미 사용중인 경우
                    guideOk.classList.add('hidden');
                    guideError.classList.remove('hidden');
                    emailValid.value = 0;
                }
            }
        });
    } else {
        // 다시쓰기하는 경우
        guideOk.classList.add('hidden');
        guideError.classList.add('hidden');
        emailValid.value = 0; // 리셋해줌
    }
});

/**
 * 회원가입 유효성검사
 */
document.userRegisterFrm.addEventListener('', (e) => {
    const frm = e.target;
    const id = frm.id;
    const password = frm.password;
    const confirmPassword = document.querySelector("#confirm-password");
    const name = frm.name;
    const email = frm.email;
    const idValid = frm.idValid;
    const emailValid = frm.emailValid;
    // console.log(idValid);

    // 아이디 - 영문자/숫자 4글자 이상
    if (!/^\w{4,}$/.test(id.value)) {
        alert('아이디는 영문자/숫자를 포함해 4글자 이상 작성해주세요.');
        e.preventDefault();
        return;
    }

    // 아이디 중복 검사
    if (idValid.value !== "1") {
        alert('이미 사용중인 아이디입니다.');
        e.preventDefault();
        return;
    }

    // 비밀번호 - 영문자/숫자/특수문자(!@#$%)포함 4글자 이상
    const regExps = [
        {
            re: /[A-Za-z]/,
            msg: '비밀번호는 영문자를 하나이상 포함해야 합니다.'
        },
        {
            re: /\d/,
            msg: '비밀번호는 숫자를 하나이상 포함해야 합니다.'
        },
        {
            re: /[!@#$%]/,
            msg: '비밀번호는 특수문자 !@#$%중에 하나이상 포함해야 합니다.'
        },
        {
            re: /^.{4,}$/,
            msg: '비밀번호는 4글자 이상 작성해야 합니다.'
        }

    ];
    for (let i = 0; i < regExps.length; i++) {
        const {re, msg} = regExps[i];
        if(!re.test(password.value)) {
            alert(msg);
            e.preventDefault();
            console.log('비밀번호 가능');
            return;
        }
    }
    if (!/^\w{4,}/.test(password.value)) {
        alert('비밀번호는 영대/소문자, 숫자, 특수문자가 4글자이상 포함되어야 합니다.')
        e.preventDefault();
    }


    // 비밀번호 확인
    if (password.value !== confirmPassword.value) {
        alert('두 비밀번호가 다릅니다.');
        e.preventDefault();
        return;
    }

    // 이름 - 한글 2글자 이상
    if (!/^[가-힣]{2,}$/.test(name.value)) {
        alert('이름은 한글 2글자 이상 작성해주세요.');
        e.preventDefault();
        console.log('이름 가능');
        return;
    }

    // 이메일
    if (!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert('사용가능한 이메일을 작성해주세요.');
        e.preventDefault();
        console.log('이메일 가능');
        return;
    }

    // 이메일 중복 검사
    if (emailValid.value !== "1") {
        alert('이미 사용중인 이메일입니다.');
        e.preventDefault();
        return;
    }
});