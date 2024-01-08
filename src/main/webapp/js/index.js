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




