//====================== 변수
const $inputPhonNum = document.querySelector(".receiver-phone");
const $PointBtn = document.querySelector(".btn-point");
const $InputPointBtn = document.querySelector(".usesPoint");
const $pointInfo = document.querySelector(".user-point-info");
const $btnPay = document.querySelector(".btn-pay");
const totalOrderPrice = document.querySelector(".total-price").innerHTML;
const requiredFields = document.querySelectorAll("[required]");
console.log(requiredFields);

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
  const res = await fetch(`/checkPoint?point=${InputValue}`);
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

// 포인트 자동 입력
window.onload = function () {
  const totalOrderPricePoint = totalOrderPrice.slice(0, -1); // 마지막 한 글자 삭제
  $InputPointBtn.value = totalOrderPricePoint;

  document.querySelector(".total-point").innerHTML =
    $InputPointBtn.value + "원";
};

//input 작성하면 인식됨
requiredFields.forEach((input) => {
  input.addEventListener("keyup", (e) => {
    input.classList.remove("falsefocus");
  });
});

// 포인트 Input keyup 이벤트
$InputPointBtn.addEventListener("keyup", (e) => {
  const InputValue = $InputPointBtn.value;
  console.log(InputValue);
  if (!numPatten.test(InputValue)) {
    $InputPointBtn.classList.add("falsefocus");
  } else {
    document.querySelector(".total-point").innerHTML = InputValue + "원";
    if ($InputPointBtn.classList.contains("falsefocus")) {
      $InputPointBtn.classList.remove("falsefocus");
    }
  }
});

let isPhonNumPatten = true;
// 폰 번호 Input keyup 이벤트
$inputPhonNum.addEventListener("keyup", (e) => {
  const InputValue = $inputPhonNum.value;
  if (!numPatten.test(InputValue)) {
    $inputPhonNum.classList.add("falsefocus");
    isPhonNumPatten = false;
  } else {
    if ($inputPhonNum.classList.contains("falsefocus")) {
      $inputPhonNum.classList.remove("falsefocus");
    }
  }
});

// 결제 버튼 이벤트
$payBtn.addEventListener("click", async (event) => {
  event.preventDefault(); // 기본 동작을 막습니다.

  const InputValue = $InputPointBtn.value.trim();

  // 유저 point
  const pointFlag = await fetchPoint(InputValue);
  // 총 금액 비교 메세지
  const payPointMessage = await fetchPayPoint(InputValue);

  // input 내용 검사
  let isValid = true;
  requiredFields.forEach((field) => {
    if (!field.value.trim()) {
      isValid = false;
      // 필수 입력 필드 중 하나라도 비어있으면 경고 메시지 표시
      field.classList.add("falsefocus");
      field.value = "";
      field.placeholder = "필수 항목입니다. 입력 부탁드립니다.";
      scrollToError(field);
    } else {
      field.classList.remove("falsefocus"); // 필드가 채워져 있으면 에러 스타일 제거
    }
  });

  // 패턴 체크
  if (InputValue === "") {
    payErrorStyle("포인트를 입력하세요");
  } else if (!numPatten.test(InputValue)) {
    $InputPointBtn.classList.add("falsefocus");
    payErrorStyle("올바른 형식으로 입력부탁드립니다.");
  } else {
    // 입력 값이 유효한 경우 falsefocus 클래스를 제거합니다.
    if ($InputPointBtn.classList.contains("falsefocus")) {
      $InputPointBtn.classList.remove("falsefocus");
    }

    if (pointFlag === false) {
      payErrorStyle("보유한 포인트를 초과하였습니다");
    } else if (isPhonNumPatten === false) {
      $inputPhonNum.classList.add("falsefocus");
      scrollToError($inputPhonNum);
      $inputPhonNum.value = "";
      $inputPhonNum.placeholder = "숫자로만 입력 부탁드립니다.";
      $btnPay.type = "button";
    } else if (
      payPointMessage === "입력하신 포인트가 결제 금액을 초과했습니다."
    ) {
      payErrorStyle("입력하신 포인트가 결제 금액을 초과했습니다.");
    } else if (payPointMessage === "포인트가 부족합니다.") {
      payErrorStyle("포인트가 부족합니다.");
    } else if (isValid === false) {
    } else {
      document.querySelector(".form-pay").submit();
    }
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

function scrollToError(errorDiv) {
  errorDiv.scrollIntoView({ behavior: "smooth", block: "center" });
}
