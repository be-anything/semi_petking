document.addEventListener('DOMContentLoaded', function () {
    new Splide('#main-slider', {
        type: 'loop',
        perPage: 3,
        pagination: false,
        gap: 2,
        breakpoints: {
            600: {
                perPage: 1,
            }
        },
        autoplay: true,
        interval: 3000,
        pauseOnHover: false,
        easing: 'cubic-bezier(.42,.65,.27,.99)', // 원하는 이징 함수로 설정
        speed: 800, // 전환 속도 (밀리초 단위)
    }).mount();
});

document.addEventListener('DOMContentLoaded', function () {
    new Splide('#main-slider1', {
        type: 'loop',
        perPage: 3,
        pagination: false,
        gap: 2,
        breakpoints: {
            600: {
                perPage: 1,
            }
        },
        autoplay: true,
        interval: 3000,
        pauseOnHover: false,
        easing: 'cubic-bezier(.42,.65,.27,.99)', // 원하는 이징 함수로 설정
        speed: 800, // 전환 속도 (밀리초 단위)
    }).mount();
});