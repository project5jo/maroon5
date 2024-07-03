

function scrollHandler(e) {
    console.log('gd');
    
    // 마우스 휠 이벤트의 deltaY 값을 사용하여 스크롤 방향을 감지합니다.
    // deltaY가 양수일 때는 아래로 스크롤, 음수일 때는 위로 스크롤
    if (e.deltaY > 0) {
        console.log('gd');
        const chatBox = document.querySelector('.chat');
        chatBox.classList.remove('slide-down');
        chatBox.classList.add('slide-up');
    }
}

// // wheel 이벤트 리스너를 추가합니다.
// window.addEventListener('wheel', scrollHandler);

//무한 스크롤 이벤트 생성 함수ㄴㄴ
function setupInfiniteScroll() {
    window.addEventListener('wheel', scrollHandler)
}
setupInfiniteScroll();