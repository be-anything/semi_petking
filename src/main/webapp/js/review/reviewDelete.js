document.querySelectorAll(".del-btn").forEach((e) => {

    e.addEventListener('click', (btn) => {
        const frm = btn.target.nextElementSibling;
        console.log(frm.id);
        console.log(frm);
        if(confirm("리뷰를 삭제하시겠습니까? ")){
        frm.submit();
        }})
});