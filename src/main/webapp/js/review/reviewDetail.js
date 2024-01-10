document.querySelector("#img-modal-div").addEventListener('click', (e) => {
    console.log(e.target)
    const img = document.querySelector("#img-modal img");
    console.log(img);
    img.src = e.target.src;
});


// // 댓글 삭제 ajax
// document.commentDeleteFrm.addEventListener('submit', (e) => {
//     e.preventDefault();
//     const frm = e.target;
//     const id = frm.commentId;
//
//     $.ajax({
//         url: `${contextPath}/review/reviewCommentDelete`,
//         data : {
//             id : commentId.value
//         },
//         type : 'post',
//         success(response) {
//             console.log('댓글 삭제 성공');
//             console.log(response);
//         }
//     })
// });
// 댓글 등록 ajax
// document.commentCreateFrm.addEventListener('submit', (e) => {
//     e.preventDefault();
//     const frm = e.target;
//     const content = frm.content;
//     const userId = frm.userId;
//     const reviewId = frm.reviewId;
//
//     console.log(content.value);
//     console.log(userId.value);
//     console.log(reviewId.value);
//
//     $.ajax({
//         url: `${contextPath}/review/reviewCommentCreate`,
//         data : {
//             userId : userId.value,
//             reviewId : reviewId.value,
//             content : content.value
//         },
//         method : 'post',
//         success(response) {
//             console.log('댓글등록 성공');
//             console.log(response);
//         }
//     })
// });