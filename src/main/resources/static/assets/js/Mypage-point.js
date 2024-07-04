
const $Inputpoint = document.querySelector('.inputPoint'); // 금액 입력 input 태그

let totalInputPoint = 0; // 충전할 포인트 누적 초기값

// 금액 버튼 클릭시 포인트 누적해주는 함수
function addPoint (point) {

    let addPoint = parseInt(point.value);
    // console.log("더해줄금액", addPoint);

    // 버튼 클릭시 금액 누적해서 더해주기
    totalInputPoint += addPoint;
    // console.log("누적금액", totalPoint);

    $Inputpoint.setAttribute("value", totalInputPoint);

    // console.log("최종금액", $Inputpoint);
}



// 정정버튼 클릭시 금액 입력 input 태그값 초기화 함수
function removePoint () {

    $Inputpoint.removeAttribute("value");
}


// 기존 포인트 + 충전할 포인트를 더한 예상포인트를 입력
let $pointDB = document.getElementById("pointSRC"); // DB 의 기존포인트 태그
let $expectPoint = document.querySelector('.expectPoint'); // 예상 최종 포인트 태그

let originalPoint = parseInt($pointDB.getAttribute('value')); // DB 의 기존포인트 값 추출
let chargePoint = parseInt($Inputpoint.getAttribute('value')); // input 태그의 누적포인트 추출

let totalExpectPoint = 0; // 예상 포인트 누적 초기값

console.log("DB 의 기존포인트", originalPoint);
console.log("input 태그의 누적포인트", chargePoint);



if (!chargePoint) {
    $expectPoint.setAttribute("value", originalPoint);
} else {
    totalExpectPoint = originalPoint + chargePoint;
    $expectPoint.setAttribute("value", addPoint);
}




$Inputpoint.addEventListener('click', e => {
    expectedPoint();
    console.log('클릭클릭');
})





