
document.querySelectorAll(".roleUpdateBtn").forEach((btn) => {

    btn.addEventListener('click', (e) => {
        const btn = e.target;
        const frm = document.userRoleUpdateFrm;
        const{id} = btn.dataset;
        const{role} = btn.dataset;
        console.log(id,role);
        frm.id.value = id;
        frm.role.value = role;
        frm.submit();



    })
});