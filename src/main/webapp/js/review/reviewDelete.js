document.querySelector(".del-btn").addEventListener('click', (e) => {
    const frm = document.reviewDeleteFrm;
    if(confirm("리뷰를 삭제하시겠습니까? ")){

        frm.submit();
    }
});