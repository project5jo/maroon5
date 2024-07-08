
// 포인트 충전페이지 변수 목록
const $formInput = document.querySelector('.formInput'); // 충전할 포인트 입력 input 태그
let totalInputPoint = 0; // 충전할 포인트 누적 초기값

// 예상 포인트 변수 (기존포인트 + 충전할 포인트)
let $pointDB = document.getElementById("pointSRC"); // DB 의 기존 포인트 태그
let originalPoint = parseInt($pointDB.textContent); // DB 의 기존 포인트 태그 의 값 추출

const $expectPoint = document.querySelector('.expectPoint');  // 충전후 예상 포인트 태그
$expectPoint.textContent = originalPoint; // 예상 포인트 태그 초기값 설정
let totalExpectPoint = 0; // 충전후 예상 포인트 누적 초기값

const $check = document.querySelector('.check'); // 확인 버튼

console.log('시작아아', $formInput.value);

// form 태그 input창이 빈 경우 확인버튼 비활성화하기
function activeCheck () {
    if(parseInt($formInput.value) === 0) {
        $check.style.backgroundColor = 'rgba(82, 51, 51, 0.3)';

        // 재확인 모달창 열기 이벤트 제거
        $check.removeEventListener('click', openModal);
        
    } else if (parseInt($formInput.value) !== 0) {
        $check.style.backgroundColor = '#4d3333';

        // 재확인 모달창 열기 이벤트 추가
        $check.addEventListener('click', openModal);
    }
}



function addPoint (point) {

    // 1-1. 금액버튼의 포인트를 숫자타입으로 변환하기
    let addPoint = parseInt(point.value);

    // 1-2. 버튼 클릭시 금액 누적해서 더해주기
    totalInputPoint += addPoint;

    // 1-3. input태그에 누적금액 적용하기
    $formInput.setAttribute("value", totalInputPoint);
    $formInput.value = totalInputPoint;

    // 2-1. DB 의 포인트 금액 + 충전할 포인트 더해주기
    totalExpectPoint = originalPoint + totalInputPoint;

    // 2-2. input태그에 예상포인트 적용하기
    $expectPoint.textContent = totalExpectPoint;

    // 3. input태그의 값이 없는 경우 확인버튼 비활성화
    activeCheck (); 
}



// 정정버튼 클릭시 금액 초기화하기
function removePoint () {

    totalInputPoint = 0;
    totalExpectPoint = 0;

    $formInput.setAttribute("value", 0);
    $formInput.value = 0;

    $expectPoint.textContent = originalPoint;

    // input태그의 값이 없는 경우 확인버튼 비활성화
    console.log('아아', $formInput.value);
    activeCheck (); 
}








