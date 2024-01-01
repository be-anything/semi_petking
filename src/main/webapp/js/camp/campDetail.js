document.querySelector("#updateBtn").addEventListener('click', (e) => {
    const btn = e.target;
    console.log(btn);
    const frm = document.campUpdateFrm;
    frm.submit();
})