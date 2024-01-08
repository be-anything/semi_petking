
// options 버튼 클릭 이벤트
document.querySelectorAll(".options").forEach((option) => {
    option.addEventListener('click', (e) => {
        const selected = e.target;
        console.log(e.target);

        const input = selected.children[0];
        const otherTags = selected.children;
        document.querySelectorAll(".options").forEach((btn) => {
            btn.classList.remove("bg-green");
            btn.classList.remove("text-white");
            btn.classList.remove("selected");
            btn.children[0].checked = false;

            if(btn === selected){
                selected.classList.add("bg-green");
                selected.classList.add("text-white");
                selected.classList.add("selected");
                input.checked = true;
            }
        });
    });
});

document.querySelectorAll("#campBtn").forEach((camp) => {
    camp.addEventListener('click',(e) => {
        const btn = e.target;
        const frm = document.campDetailFrm;
        const {id} = btn.dataset;
        console.log(id)
        frm.id.value = id;
        frm.submit();
    });
})

document.querySelectorAll(".wish-btn").forEach((heart) => {
    heart.addEventListener('click', (e) => {
        const wish = e.target;
        const {campId, userId} = wish.dataset;
        // console.log(campId, userId);

        // console.log(wish)
        if(wish.classList.contains("fa-regular")) {
            if(confirm("나의 캠핑장 찜 목록에 추가합니다.")){
                $.ajax({
                    url: `${contextPath}/wish/wishInsert`,
                    method : 'post',
                    data : {
                        campId, userId
                    },
                    success(result) {
                        wish.classList.remove("fa-regular");
                        wish.classList.remove("text-gray2");
                        wish.classList.add("fa-solid");
                        wish.classList.add("text-red");
                    }
                    }
                )
            }
        }
        else {
            if(confirm("나의 캠핑장 찜 목록에서 제거합니다.")) {
                $.ajax({
                        url: `${contextPath}/wish/wishDelete`,
                        method : 'post',
                        data : {
                            campId, userId
                        },
                        success(result) {
                            wish.classList.remove("fa-solid");
                            wish.classList.remove("text-red");
                            wish.classList.add("fa-regular");
                            wish.classList.add("text-gray2");
                        }
                    }
                )
            }
        }
    })
});