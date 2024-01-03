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

document.querySelector("#updateBtn").addEventListener('click', (e) => {
    const btn = e.target;
    console.log(btn);
    const frm = document.campUpdateFrm;
    frm.submit();
})