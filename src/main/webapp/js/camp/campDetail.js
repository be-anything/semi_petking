document.querySelector("#btn-campToRoom").addEventListener('click', (e) =>{
    console.log("btn_campToRoom click!");
    const $div = document.getElementById('campDiv');
    const campId = $div.getAttribute('data-campId');
    console.log("campId : "+campId);
    $.ajax({
        url: `${contextPath}/reservation/reservationMain`,
        method : 'get',
        data : {
            campId
        },
        success(result) {
            console.log("campDetail - >success");
            window.location.href = `${contextPath}/reservation/reservationMain?campId=${campId}`;
        },
        error()
        {
            console.log("campDetail - >error");
        }
    });


});
// slider js
const splide = new Splide( '#main-slider', {
    width: '80%',
    pagination: false,
} );

const thumbnails = document.getElementsByClassName( 'thumbnail' );
let current;

for ( let i = 0; i < thumbnails.length; i++ ) {
    initThumbnail( thumbnails[ i ], i );
}

function initThumbnail( thumbnail, index ) {
    thumbnail.addEventListener( 'click', function () {
        splide.go( index );
    } );
}

splide.on( 'mounted move', function () {
    let thumbnail = thumbnails[ splide.index ];

    if ( thumbnail ) {
        if ( current ) {
            current.classList.remove( 'is-active' );
        }

        thumbnail.classList.add( 'is-active' );
        current = thumbnail;
    }
} );

splide.mount();


document.querySelector("#updateBtn").addEventListener('click', (e) => {
    const btn = e.target;
    console.log(btn);
    const frm = document.campUpdateFrm;
    frm.submit();
})
document.querySelector(".wish-btn").addEventListener('click', (e) =>{
    const wish = e.target.nextElementSibling;
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
});

(() => {
    const container  = document.querySelector("#map");
    const campLcLa = document.querySelector("#campLcLa");
    const campLcLo = document.querySelector("#campLcLo");
    const options = {
        center: new kakao.maps.LatLng(campLcLa.value, campLcLo.value),
        level: 2
    };
    const map = new kakao.maps.Map(container, options);

    // 마커가 표시될 위치입니다
    const markerPosition  = new kakao.maps.LatLng(campLcLa.value, campLcLo.value);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
})();

