const regExpId = /^\w{4,}$/;
const regExpsPwd = [
    {
        re: /[a-zA-Z]/
    },
    {
        re: /\d/
    },
    {
        re: /[!@#$%^&*]/
    }, {
        re: /^.{4,}$/
    }
]
const regExpPwd = /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
// const regExpName =  /^[가-힣]{2,}$/g;


// input tag change 이벤트용
document.querySelectorAll(".registInputs").forEach((input) => {
    input.addEventListener('keyup', (e) => {
        const defaultMsg = input.nextElementSibling.children[0];
        const okMsg = input.nextElementSibling.children[1];
        const noMsg = input.nextElementSibling.children[2];

        console.log(defaultMsg);
        console.log(okMsg);
        console.log(noMsg);

        switch (input.id) {
            case "id" :
                if (input.value === '') {
                    defaultMsg.classList.remove('hidden')
                    noMsg.classList.add("hidden");
                    okMsg.classList.add('hidden');
                    document.querySelector(".idCheck").classList.add('hidden');
                } else {
                    if (regExpId.test(input.value)) {
                        noMsg.classList.add("hidden");

                        $.ajax({
                            url: `${contextPath}/camp/checkCampId`,
                            data: {
                                id: input.value
                            },
                            success(response) {
                                const {result} = response;
                                if (result) {
                                    // 사용 가능한 경우
                                    okMsg.classList.remove('hidden');
                                    document.querySelector(".idCheck").classList.add('hidden');
                                } else {
                                    okMsg.classList.add('hidden');
                                    document.querySelector(".idCheck").classList.remove('hidden');
                                }
                            }
                        })
                    } else {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.add('hidden');
                        noMsg.classList.remove("hidden");
                    }
                }
                ;
                break;
            case "password" :
                console.log(input.id);

                if (input.value === '') {
                    defaultMsg.classList.remove('hidden')
                    noMsg.classList.add("hidden");
                    okMsg.classList.add('hidden');
                    document.querySelector(".idCheck").classList.add('hidden');
                } else {
                    if (!regExpPwd.test(input.value)) {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.add('hidden');
                        noMsg.classList.remove("hidden");
                    } else {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.remove('hidden');
                        noMsg.classList.add("hidden");
                    }
                }
                ;
                break;
            case "confirm-password" :
                const password = document.querySelector("#password");

                if (input.value === '') {
                    defaultMsg.classList.remove('hidden')
                    noMsg.classList.add("hidden");
                    okMsg.classList.add('hidden');
                } else {
                    if (password.value !== input.value) {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.add('hidden');
                        noMsg.classList.remove("hidden");
                    } else {
                        defaultMsg.classList.add('hidden');
                        okMsg.classList.remove('hidden');
                        noMsg.classList.add("hidden");
                    }
                }
                ;
                break;
        }
    });
});


// 유효성 검사 - 캠핑장 사업자 정보 검사
document.querySelector(".continueBtn").addEventListener('click', (e) => {
    const btn = e.target;
    const frm = document.campUserRegisterFrm;
    const id = frm.id;
    const password = frm.password;
    const confirmPassword = frm["confirm-password"];
    const name = frm.name;
    const phone = frm.phone;

    // 아이디 - 영문자/숫자 4글자 이상
    if (!regExpId.test(id.value)) {
        e.preventDefault();
        return;
    }
    // 비밀번호 - 영문자/숫자/특수문자!@#$% 포함 4글자이상
    ;
    for (let i = 0; i < regExpsPwd.length; i++) {
        const {re: RegExp} = regExpsPwd[i];
        if (!RegExp.test(password.value)) {
            e.preventDefault();
            return;
        }
    }
    // 비밀번호 확인
    if (password.value !== confirmPassword.value) {
        e.preventDefault();
        return;
    }


    const campInfoRegister = document.querySelector(".campInfoRegister");
    campInfoRegister.classList.remove('hidden');
    const campUserRegister = document.querySelector(".campUserRegister");
    campUserRegister.classList.add('hidden');
});


document.querySelectorAll("input[name=businessNumber]").forEach((input) => {
    input.addEventListener('keyup',(e) =>{
        const _businessNumber = [...document.querySelectorAll("input[name=businessNumber]")];
        const businessNumberMsg = document.querySelector("#businessNumberMsg");
        const noMsg = businessNumberMsg.children[0];
        const okMsg = businessNumberMsg.children[1];
        console.log(noMsg);
        console.log(okMsg);
        // 사업자 번호 중복 확인
        console.log(_businessNumber);
        const businessNumber = _businessNumber[0].value + "-" + _businessNumber[1].value + "-" + _businessNumber[2].value;
        console.log(businessNumber);

        console.log(_businessNumber[0].value);
        console.log(_businessNumber[1].value);
        console.log(_businessNumber[2].value);

        if(_businessNumber[0].value === '') {
            okMsg.classList.add('hidden');
            noMsg.classList.add('hidden');
        }
        else if(_businessNumber[1].value === '') {
            okMsg.classList.add('hidden');
            noMsg.classList.add('hidden');
        }
        else if(_businessNumber[2].value === '') {
            okMsg.classList.add('hidden');
            noMsg.classList.add('hidden');
        }
        else {
            $.ajax({
                url: `${contextPath}/camp/checkBusinessId`,
                data: {
                    businessNumber: businessNumber
                },
                success(response) {
                    const {result} = response;
                    if (result) {
                        // 사용 가능한 경우
                        okMsg.classList.add('hidden');
                        noMsg.classList.remove('hidden');
                    } else {
                        okMsg.classList.remove('hidden');
                        noMsg.classList.add('hidden');
                    }
                }
            })
        }
    });
})


// 유효성 검사 - 캠핑장 정보 검사
document.querySelector(".submitBtn").addEventListener('click', (e) => {
    const registCampFrm = document.campInfoRegisterFrm;
    const btn = e.target;
    const campName = registCampFrm.campName;
    const businessIntro = registCampFrm.businessIntro;
    const businessAddr = registCampFrm.businessAddr;
    const _businessNumber = [...document.querySelectorAll("input[name=businessNumber]")];
    const businessNumber = _businessNumber[0].value + "-" + _businessNumber[1].value + "-" + _businessNumber[2].value;

    // 사업자 정보 저장
    // 캠핑장 정보 저장
    if(!/^.+$/.test(campName.value.trim()))
    {
        e.preventDefault();
        return;
    }
    if(!/^.+$/.test(businessAddr.value.trim()))
    {
        e.preventDefault();
        return;
    }
    if(!/^.+$/.test(businessNumber))
    {
        e.preventDefault();
        return;
    }
    //내용 유효성 검사
    if(!/^(.|\n)+$/.test(businessIntro.value.trim()))
    {
        e.preventDefault();
        return; //제출막기..
    }



    const registUserFrm = document.campUserRegisterFrm;
    const totalFrm = document.campTotalRegisterFrm;
    totalFrm.businessId.value = registUserFrm.id.value;
    totalFrm.businessPassword.value = registUserFrm.password.value;
    totalFrm.businessNumber.value = businessNumber;
    totalFrm.businessName.value = registUserFrm.name.value;
    totalFrm.campName.value = registCampFrm.campName.value;
    totalFrm.campIntro.value = registCampFrm.businessIntro.value;

    const _phone = [...document.campUserRegisterFrm.phone];
    totalFrm.campPhone.value = _phone[0].value + "-" + _phone[1].value + "-" + _phone[2].value;

    totalFrm.campAddr.value = registCampFrm.businessAddr.value;
    // totalFrm.campLcLa.value = registUserFrm.id.value;
    // totalFrm.campLcLo.value = registUserFrm.id.value;

    console.log(totalFrm);

});
