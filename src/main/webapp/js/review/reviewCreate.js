// 미리보기 js
document.querySelectorAll(".reviewImg").forEach((img) => {
    img.addEventListener('change', (e) => {
        const btn = e.target;
        const img = btn.files[0];
        const imgView = btn.parentElement;
        const text = btn.previousElementSibling;

        const loadImg = (img) => {
            const imgReader = new FileReader();
            imgReader.onload = (e) => {
                imgView.style["backgroundImage"] =  "url('" + e.target.result + "')";
            };
            imgReader.readAsDataURL(img);
            text.style.display = 'none';
        }
        loadImg(img);
    });
})

// options 버튼 클릭 이벤트
document.querySelectorAll(".options").forEach((option) => {
    option.addEventListener('click', (e) => {
        const selected = e.target;
        selected.classList.toggle("bg-green");
        selected.classList.toggle("text-white");
        selected.classList.toggle("selected");

        const input = selected.children[0];
        console.dir(input.checked);
        if(selected.classList.contains("selected")){
            input.checked = true;
        }
        else {
            input.checked = false;
        }
    });
});

document.addEventListener('submit',(e)=> {
    const frm = e.target;
    const title = frm.reviewTitle;
    const content = frm.reviewContent;

    if (!/^(.|\n)+$/.test(reviewContent.value.trim()))
    {
        alert('내용을 작성해주세요.');
        e.preventDefault();
        return;
    }
    if (!/^(.|\n)+$/.test(reviewTitle.value.trim()))
    {
        alert('제목을 작성해주세요.');
        e.preventDefault();
        return;
    }
});