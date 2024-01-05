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


document.querySelector("#findAddr").addEventListener('click', () => {
    let addr = ''; // 주소 변수
    let extraAddr = ''; // 참고항목 변수
    new daum.Postcode({
        oncomplete: function(data) {
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("addr-extra").value = extraAddr;

            } else {
                document.getElementById("addr-extra").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('addr-postcode').value = data.zonecode;
            document.getElementById("addr-address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr-detail").focus();

            const campAddr = document.campTotalRegisterFrm.campAddr;
            const detailAddr = document.getElementById("addr-detail").value;
            campAddr.value = `(${document.getElementById('addr-postcode').value}) ${addr}${detailAddr}${extraAddr}`;
        }
    }).open();

})




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

    // totalFrm.campAddr.value = registCampFrm.businessAddr.value;
    // totalFrm.campLcLa.value = registUserFrm.id.value;
    // totalFrm.campLcLo.value = registUserFrm.id.value;

    console.log(totalFrm);
    totalFrm.submit();
});
