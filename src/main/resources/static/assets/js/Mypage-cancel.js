const $check = document.querySelector(".check"); // check 버튼 태그

const $cancelInput = document.querySelector(".cancelInput");

$cancelInput.addEventListener('click', e => {
    console.log('클릭되고있니?');
    checkToggle();
})

// 1-1. input 태그를 클릭여부에 따른 value 값 변경하기
function checkToggle (checkbox) {

    if (checkbox.checked) { // type checkbox 의 속성 checked (true, false)
        checkbox.value = "true";
        $check.style.backgroundColor = '#4d3333';

        // 재확인 모달창 열기 이벤트 추가
        $check.addEventListener('click', openModal);

    } else {
        checkbox.value = "false";
        $check.style.backgroundColor = 'rgba(82, 51, 51, 0.3)';

        // 재확인 모달창 열기 이벤트 제거
        $check.removeEventListener('click', openModal);
    }
}

