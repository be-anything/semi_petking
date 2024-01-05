document.addEventListener('submit',(e)=> {

    //분기
    //정적으로 생성된 폼 과 동적으로 생성된 폼을 가리지 않고 모두 적용.

    if(e.target.matches("[name=askCommentCreateFrm]")) //is:jQuery, js:matches
    {
        const frm = e.target;
        const comment = frm.askComment;
        if (!/^(.|\n)+$/.test(askComment.value.trim()))
        {
            alert('답변 내용을 작성해주세요.');
            e.preventDefault();
            return;
        }
    }


});