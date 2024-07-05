// 충전할 포인트 변수
const $Inputpoint = document.querySelector('.inputPoint'); // 충전할 포인트 입력 input 태그
let totalInputPoint = 0; // 충전할 포인트 누적 초기값

// 예상 포인트 변수 (기존포인트 + 충전할 포인트)
let $pointDB = document.getElementById("pointSRC"); // DB 의 기존 포인트 태그
let originalPoint = parseInt($pointDB.textContent); // DB 의 기존 포인트 태그 의 값 추출

const $expectPoint = document.querySelector('.expectPoint');  // 충전후 예상 포인트 태그
$expectPoint.textContent = originalPoint; // 예상 포인트 태그 초기값 설정
let totalExpectPoint = 0; // 충전후 예상 포인트 누적 초기값


function addPoint (point) {

    // 1-1. 금액버튼의 포인트를 숫자타입으로 변환하기
    let addPoint = parseInt(point.value);

    // 1-2. 버튼 클릭시 금액 누적해서 더해주기
    totalInputPoint += addPoint;

    // 1-3. input태그에 누적금액 적용하기
    $Inputpoint.setAttribute("value", totalInputPoint);
    $Inputpoint.value = totalInputPoint;

    // 2-1. DB 의 포인트 금액 + 충전할 포인트 더해주기
    totalExpectPoint = originalPoint + totalInputPoint;

    // 2-2. input태그에 예상포인트 적용하기
    $expectPoint.textContent = totalExpectPoint;
    // $expectPoint.setAttribute("value", totalExpectPoint);
    // $expectPoint.value = totalExpectPoint;
}


// 정정버튼 클릭시 금액 초기화하기
function removePoint () {

    totalInputPoint = 0;
    totalExpectPoint = 0;

    $Inputpoint.setAttribute("value", 0);
    $Inputpoint.value = 0;

    // $expectPoint.setAttribute("value", originalPoint);
    // $expectPoint.value = originalPoint;
    $expectPoint.textContent = originalPoint;
}








