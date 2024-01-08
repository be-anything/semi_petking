window.addEventListener('DOMContentLoaded', (e) => {
    // ajaxResev();

});

// // 탭 메뉴 이동
const tabBtns = document.querySelectorAll(".tabBtn");
tabBtns.forEach((tab, index) => {
    tab.addEventListener('click', (e) => {
        const tabBtn = e.target;
        const tabBtnA = [...tabBtn.children];

        // 기존 클래스 지우기
        tabBtns.forEach((btn) => {
            btn.children[0].classList.remove("text-white");
            btn.children[0].classList.remove("bg-green");
        });

        // 활성화 디자인 추가하기
        // inline-block p-4 rounded-t-lg text-white bg-green active px-5
        tabBtn.classList.add("text-white");
        tabBtn.classList.add("bg-green");

        const formList = [...document.querySelectorAll(".forms")];
        formList.forEach((form) => {
            form.classList.remove("active");
        });
        formList[index].classList.add("active");
    })
});


// 진행중인 예약 ajax 처리
// const ajaxResev = () => {
//     const content = document.querySelector(".procesReserv");
//     console.log(content);
//     const pagebar = document.querySelector(".processPagebar");
//
//     $.ajax({
//         url : `${contextPath}/user/userProcessReservation`,
//         method : 'get',
//         success(response){
//             console.log(response);
//
//             // 불러온 reserv json 출력하기
//             const { reservations } = response;
//
//             reservations.forEach(({campName, campRenamedImg, roomName, id, campId, startDate, endDate}, index) => {
//                 console.log(campName, campRenamedImg, roomName, id, campId, startDate, endDate, index);
//
//                 const resev = `<div class="flex justify-between items-center mx-auto max-w-6xl rounded-lg bg-white mt-5 mb-5 hover:drop-shadow">
//             <div class="w-full flex items-center m-8">
//                 <a href="${contextPath}/camp/campDetail?id=${campId}">
//                     <img class="w-[300px]" src="${contextPath}/upload/camp/${campRenamedImg}" />
//                 </a>
//                 <div class="w-full ml-10 relative">
//                     <div class="text-black text-base font-normal mt-5">
//                         <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">예약번호</span>
//                         petking - 000${id}
//                     </div>
//                     <div class="text-black text-base font-normal mt-5">
//                         <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑장</span>
//                             ${campName}
//                     </div>
//                     <div class="text-black text-base font-normal mt-5">
//                         <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">캠핑존</span>
//                             ${roomName}
//                     </div>
//                     <div class="text-black text-base font-normal mt-5">
//                         <span class="py-2 px-4 me-2 mb-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200">이용일시</span>
//                             ${startDate} ~ ${endDate}
//                     </div>
//                     <div class="text-black text-base font-normal absolute right-0 bottom-[-1.75rem]">
//                         <button type="button" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">예약내역 상세보기</button>
//                         <button type="button" class="text-white bg-green hover:bg-pink focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2">리뷰 작성하기</button>
//                     </div>
//                 </div>
//             </div>
//         </div>`;
//
//                 content.insertAdjacentHTML("afterbegin", resev);
//             });
//
//             const {processPagebar} = response;
//             pagebar.innerHTML = processPagebar;
//             console.log("왜 안나오니");
//         }
//     }).done(()=> {
//     })
// }