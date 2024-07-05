//====================== 변수

const $PointBtn = document.querySelector(".btn-point");
const $InputPointBtn = document.querySelector(".usesPoint");
const $pointInfo = document.querySelector(".user-point-info");
const $btnPay = document.querySelector(".btn-pay");

const $payBtn = document.querySelector(".btn-pay");
const numPatten = /^[0-9]*$/;
let pointFlag = false;

//입력값 검증
let IsInput = false;

let InputPlaceholder = $InputPointBtn.placeholder;
console.log(InputPlaceholder);

//====================== 비동기 fetch

//포인트 사용 검증
async function fetchPoint(InputValue) {
  const res = await fetch(
    `/checkPoint?point=${InputValue}`
  );
  const flag = await res.json();
  pointFlag = flag;
  console.log(flag);
}

//결제 검증
async function fetchPayPoint(InputValue) {
  const res = await fetch(`/payPoint?point=${InputValue}`);
  const message = await res.json();
  return message.message; // 메시지 반환
}

// ====================== 결제 창 활성화

//====================== 이벤트

// 포인트 Input keyup 이벤트
$InputPointBtn.addEventListener("keyup", (e) => {
  const InputValue = $InputPointBtn.value;
  console.log(InputValue);
  if (!numPatten.test(InputValue)) {
    $InputPointBtn.classList.add("falsefocus");
  } else {
    if ($InputPointBtn.classList.contains("falsefocus")) {
      $InputPointBtn.classList.remove("falsefocus");
    }
  }
});

// 포인트 적용 이벤트
$PointBtn.addEventListener("click", async () => {
  const InputValue = $InputPointBtn.value;
  await fetchPoint(InputValue);
  console.log("click");
  if (InputValue.trim() === "") {
    $InputPointBtn.classList.add("falsefocus");
    $InputPointBtn.value = "";
    $InputPointBtn.placeholder = "사용할 포인트를 입력하세요";
  } else if (pointFlag === false) {
    $InputPointBtn.classList.add("falsefocus");
    $InputPointBtn.value = "";
    $InputPointBtn.placeholder = "보유한 포인트를 초과하였습니다";
  } else if (pointFlag === true) {
    $InputPointBtn.classList.remove("falsefocus");
    document.querySelector(".total-point").innerHTML = InputValue + "원";
  }
});

// 결제 버튼 이벤트
$payBtn.addEventListener("click", async (event) => {
  event.preventDefault(); // 기본 동작을 막습니다.
  const payPointMessage = await fetchPayPoint($InputPointBtn.value); // 비동기 함수 호출 결과 대기

  if (payPointMessage === "입력하신 포인트가 결제 금액을 초과했습니다.") {
    payErrorStyle("입력하신 포인트가 결제 금액을 초과했습니다.");
  } else if (payPointMessage === "포인트가 부족합니다.") {
    payErrorStyle("포인트가 부족합니다.");
  } else {
    document.querySelector(".form-pay").submit();
  }
});

//====================== 함수

//결제부분 에러 함수
const payErrorStyle = (text) => {
  $InputPointBtn.classList.add("falsefocus");
  $InputPointBtn.value = "";
  $InputPointBtn.placeholder = text;
  $btnPay.type = "button";
};
