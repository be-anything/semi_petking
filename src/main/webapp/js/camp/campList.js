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

document.querySelectorAll(".fa-heart").forEach((heart) => {
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