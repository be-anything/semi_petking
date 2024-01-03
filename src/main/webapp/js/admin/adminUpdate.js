const frm = document.userRoleUpdateFrm;
const id = frm.id;
const role = frm.role;

document.querySelectorAll(".user-role").forEach((select) => {
    select.addEventListener('change', (e) => {
        const {value} = e.target;
        role.value = value;
        const {id : idVal} = e.target.dataset;

        if(confirm(`${idVal}회원을 ${value == 'A' ? '관리자' : '일반회원'}로 변경하시겠습니까?`)){
            role.value = value
            id.value = idVal;
            frm.submit();
        }
        else {
            // 원래 선택했던 값(selected)으로 복귀
            e.target.querySelector("[selected]").selected = true;
        }

    });
});