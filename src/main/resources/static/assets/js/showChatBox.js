

function scrollHandler(e) {
    console.log('gd')
    //스크롤이 최하단부로 내려갔을 때만 이벤트를 발생시켜야 함
    //현재창에 보이는 세로길이 + 스크롤을 내린 길이 == 브라우저 전체 세로 길이
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight + 100){
        console.log('gd')
        const chatBox = document.querySelector('.chat')
        chatBox.classList.remove('slide-down')
        chatBox.classList.add('slide-up')
    }
}

//무한 스크롤 이벤트 생성 함수ㄴㄴ
function setupInfiniteScroll() {
    window.addEventListener('scroll', scrollHandler)
}
setupInfiniteScroll();