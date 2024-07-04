function scrollHandler(e) {
    const chatBox = document.querySelector('.chat');

    if (chatBox.classList.contains('slide-up') && e.deltaY < 0) {
        // 채팅창이 이미 열려 있고 휠을 위로 스크롤할 때는 아무 작업도 하지 않음
        return;
    }

    if (chatBox.classList.contains('slide-down') && e.deltaY > 35) {
        // 채팅창이 이미 닫혀 있고 휠을 아래로 스크롤할 때는 아무 작업도 하지 않음
        chatBox.classList.remove('slide-down');
        chatBox.classList.add('slide-up');
    }

    // if (e.deltaY > 0) {
    //     chatBox.classList.remove('slide-down');
    //     chatBox.classList.add('slide-up');
    // } else {
    //     chatBox.classList.remove('slide-up');
    //     chatBox.classList.add('slide-down');
    // }
}

// 무한 스크롤 이벤트 생성 함수
function setupInfiniteScroll() {
    window.addEventListener('wheel', scrollHandler);
}

setupInfiniteScroll();