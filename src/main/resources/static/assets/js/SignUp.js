// 회원가입 가입하기 조건 만족 전 가입하기 버튼 미활성
const $success = document.querySelector(".success"); // 가입하기 버튼
$success.style.backgroundColor = "#7c7979"; // 초기 버튼 색상 회색
$success.setAttribute("type", "button"); // 초기 버튼 타입 button

// 회원가입 조건을 만족하는지 판별하는 객체 설정
let flagArray = {
  idFlag: false,
  firstPasswordFlag: false,
  secondPasswordFlag: false,
  nameFlag: false,
  birthFlag: false,
  emailFlag: false,
};

/// 회원가입 가입하기 조건 만족 여부 체크 후 가입하기 버튼 활성화 함수
function checkAllFlagArray() {
  let checkFlag = false;

  for (let key in flagArray) {
    if (flagArray[key]) {
      checkFlag = true;
    }
  }
  if (checkFlag) {
    // 모든 플래그가 true일 때 실행할 작업
    console.log("모든 조건이 만족되었습니다.");
    $success.style.backgroundColor = "#432626"; // 활성화 후 버튼 색상 변경
    $success.setAttribute("type", "submit"); // 활성화 후 버튼 타입 submit 설정
    return;
  } else {
    console.log("만족되지 않은 조건이 있습니다.");
  }
}

// 프로필 사진 업로드하기
const $proflieImg = document.querySelector(".upload-imgbox img"); // 사진업로드창
const $profileUploadBtn = document.querySelector(".upload-uploadbtn"); // 사진 업로드 버튼

const $realUploadBtn = document.querySelector(".upload-img"); // 실제 업로드 버튼

// 1. 사진창 클릭시 파일열기 이벤트
$proflieImg.addEventListener("click", (e) => {
  $realUploadBtn.click();
});
// 2. 사진업로드버튼 클릭시 파일열기 이벤트
$profileUploadBtn.addEventListener("click", (e) => {
  $realUploadBtn.click();
});

// 3. 파일열기를 했을 때 이벤트
$realUploadBtn.addEventListener("change", (e) => {
  // 유저가 올린 파일
  const profileDate = $realUploadBtn.files[0];
  console.log(profileDate);

  // 이미지파일의 로우데이터(바이트) 를 읽는 객체 생성
  const reader = new FileReader();

  // 파일을 img 태그의 src 속성에 넣기 위해 URL 형태로 변경
  reader.readAsDataURL(profileDate);

  // 파일이 등록되는 순간 img태그에 이미지 넣기
  reader.onloadend = (e) => {
    $proflieImg.src = reader.result;
  };

  //모달 닫기
  $modal.classList.add("modal-close");
});

// 4. 업로드한 프로필사진 삭제하기
const $profileDeleteBtn = document.querySelector(".upload-deletebtn"); // 사진 삭제 버튼

// 사진 삭제 버튼을 클릭했을 때 이벤트
$profileDeleteBtn.addEventListener("click", (e) => {
  // 프로필사진을 기본사진으로 교체하기
  $proflieImg.src = "/assets/img/profile3.jpg";
  // 파일업로드버튼의 업로드파일정보 초기화하기
  const profileDate = $realUploadBtn.files[0];
  $realUploadBtn.value = "";
  $modal.classList.add("modal-close");
});

window.onload = function () {
  //실행할 내용
  $proflieImg.src = "/assets/img/profile3.jpg";
};

// 모달창 닫기
const $modal = document.querySelector(".update-profile-modal");
const $uploadCloseBtn = document.querySelector(".upload-close-btn"); //모달 닫기 버튼
$uploadCloseBtn.addEventListener("click", () => {
  $modal.classList.add("modal-close");
});

//모달창 열기
const $openModal = document.querySelector(".update-img-modal button");
$openModal.addEventListener("click", () => {
  $modal.classList.remove("modal-close");
});

// 회원가입 조건 검증하기
// 1. account 조건 검증하기
const $inputAccount = document.querySelector(".account"); // 아이디 입력창 변수설정
const $accountSub = document.querySelector(".th-accountSub"); // 아이디 조건태그 변수설정
const accountValid = /^[a-z][a-z0-9_-]{3,15}$/; // 아이디에 입력가능한 문자열

$inputAccount.addEventListener("keyup", (e) => {
  setTimeout(() => {
    // 아이디 공백체크
    if (e.target.value === "" || e.target.value.length === 0) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $accountSub.style.color = "#ff003e";
      $accountSub.textContent = "아이디는 필수 입력정보입니다.";

      // 아이디 조건체크
    } else if (!accountValid.test(e.target.value)) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $accountSub.style.color = "#ff003e";
      $accountSub.textContent = "아이디는 영문 소문자/숫자 조합 (4~16자)";
    } else {
      // 아이디 중복체크
      // fetch 사용
      const $inputAccountValue = $inputAccount.value;
      let encodedAccount = encodeURIComponent($inputAccountValue);
      const URL = `http://localhost:8383/checkid?account=${encodedAccount}`;
      const IPURL = `http://172.30.1.60:8383/checkid?account=${encodedAccount}`;

      Promise.any([
        fetch(URL, {
          method: "POST",
          headers: { "content-type": "application/json" },
          body: JSON.stringify({ account: $inputAccount.value }),
        }),
        fetch(IPURL, {
          method: "POST",
          headers: { "content-type": "application/json" },
          body: JSON.stringify({ account: $inputAccount.value }),
        }),
      ])
        .then((res) => res.json())
        .then((json) => {
          if (json === false) {
            e.target.classList.remove("falsefocus");
            e.target.classList.add("truefocus");
            $accountSub.style.color = "blue";
            $accountSub.textContent = "사용가능한 아이디입니다";

            flagArray.idFlag = true;
            checkAllFlagArray(); // 회원가입 가입하기 조건 만족 여부 체크 함수
          } else {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $accountSub.style.color = "red";
            $accountSub.textContent =
              "중복되는 아이디입니다. 다른 아이디를 입력해주세요.";
          }
        });
    }
  }, 500); // setTimeout end
}); // keyup end

// 2. password 조건 검증하기
const $inputFirstPassword = document.querySelector(".pw1"); // 첫번째 비밀번호 입력창 변수설정
const $inputSecondPassword = document.querySelector(".pw2"); // 두번째 비밀번호 입력창 변수설정
const $firstPasswordSub = document.querySelector(".th-passwordSub1"); // 첫번째 비밀번호 조건태그 변수설정
const $secondPasswordSub = document.querySelector(".th-passwordSub2"); // 두번째 비밀번호 조건태그 변수설정
const passwordValid = /^(?=.*[a-zA-Z!@#$%^&*()\-_=+{};:,<.>])(?=.*\d).{7,15}$/; //비밀번호에 입력가능한 문자열

$inputFirstPassword.addEventListener("input", (e) => {
  setTimeout(() => {
    // 비밀번호 공백체크
    if (e.target.value === "" || e.target.value.length === 0) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $firstPasswordSub.style.color = "red";
      $firstPasswordSub.textContent = "비밀번호는 필수 입력정보입니다.";

      // 비밀번호 조건체크
    } else if (!passwordValid.test(e.target.value)) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $firstPasswordSub.style.color = "red";
      $firstPasswordSub.textContent =
        "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
    } else {
      $firstPasswordSub.textContent = "";
      $secondPasswordSub.textContent = "";
      e.target.classList.remove("falsefocus");
      e.target.classList.add("truefocus");

      if (
        $inputSecondPassword.value !== "" ||
        $inputSecondPassword.value.length !== 0
      ) {
        if ($inputSecondPassword.value !== $inputFirstPassword.value) {
          $inputSecondPassword.classList.remove("truefocus");
          $inputSecondPassword.classList.add("falsefocus");

          e.target.classList.remove("truefocus");
          e.target.classList.add("falsefocus");

          $secondPasswordSub.style.color = "red";
          $secondPasswordSub.textContent =
            "입력하신 비밀번호가 일치하지 않습니다.";
        } else {
          $inputSecondPassword.classList.remove("falsefocus");
          $inputSecondPassword.classList.add("truefocus");
          $firstPasswordSub.textContent = "";

          e.target.classList.remove("falsefocus");
          e.target.classList.add("truefocus");
          $secondPasswordSub.style.color = "blue";
          $secondPasswordSub.textContent = "사용가능한 비밀번호입니다.";

          flagArray.firstPasswordFlag = true;
          checkAllFlagArray(); // 회원가입 가입하기 조건 만족 여부 체크 함수
        }
      }
    }
  }, 500); // setTimeout end
}); // keyup end

$inputSecondPassword.addEventListener("input", (e) => {
  setTimeout(() => {
    if (e.target.value === "" || e.target.value.length === 0) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $secondPasswordSub.style.color = "red";
      $secondPasswordSub.textContent = "비밀번호는 필수 입력정보입니다.";

      // 비밀번호 조건체크
    } else if (!passwordValid.test(e.target.value)) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $secondPasswordSub.style.color = "red";
      $secondPasswordSub.textContent =
        "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
    } else {
      $firstPasswordSub.textContent = "";
      $secondPasswordSub.textContent = "";
      e.target.classList.remove("falsefocus");
      e.target.classList.add("truefocus");

      if (
        $inputFirstPassword.value !== "" ||
        $inputFirstPassword.value.length !== 0
      ) {
        if ($inputSecondPassword.value !== $inputFirstPassword.value) {
          $inputFirstPassword.classList.remove("truefocus");
          $inputFirstPassword.classList.add("falsefocus");

          e.target.classList.remove("truefocus");
          e.target.classList.add("falsefocus");

          $secondPasswordSub.style.color = "red";
          $secondPasswordSub.textContent =
            "입력하신 비밀번호가 일치하지 않습니다.";
        } else {
          $inputFirstPassword.classList.remove("falsefocus");
          $inputFirstPassword.classList.add("truefocus");
          $firstPasswordSub.textContent = "";

          e.target.classList.remove("falsefocus");
          e.target.classList.add("truefocus");
          $secondPasswordSub.style.color = "blue";
          $secondPasswordSub.textContent = "사용가능한 비밀번호입니다.";

          flagArray.secondPasswordFlag = true;
          checkAllFlagArray(); // 회원가입 가입하기 조건 만족 여부 체크 함수
        }
      }
    }
  }, 500); // setTimeout end
}); // keyup end

// 3. name 조건 검증하기
const $inputName = document.querySelector(".name"); // 이름 입력창 변수설정
const $nameSub = document.querySelector(".th-nameSub"); // 이름 조건태그 변수설정
const nameValid = /^[가-힣]+$/; // 이름에 입력가능한 문자열

$inputName.addEventListener("keyup", (e) => {
  setTimeout(() => {
    // 이름 공백체크
    if (e.target.value === "" || e.target.value.length === 0) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $nameSub.style.color = "#ff003e";
      $nameSub.textContent = "이름은 필수 입력정보입니다.";

      // 이름 조건체크
    } else if (!nameValid.test(e.target.value)) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $nameSub.style.color = "#ff003e";
      $nameSub.textContent = "이름은 한글만 입력 (2자 이상)";
    } else if (e.target.value.length > 1) {
      e.target.classList.remove("falsefocus");
      e.target.classList.add("truefocus");
      $nameSub.style.color = "#1a72ff";
      $nameSub.textContent = "";

      flagArray.nameFlag = true;
      checkAllFlagArray(); // 회원가입 가입하기 조건 만족 여부 체크 함수
    }
  }, 500); // setTimeout end
}); // keyup end

// 4. birth 조건 검증하기
const $chooseBirth = document.querySelector(".birth"); // 생일선택창 변수설정
const $birthSub = document.querySelector(".th-birthSub"); // 이름 조건태그 변수설정

$chooseBirth.addEventListener("blur", (e) => {
  setTimeout(() => {
    // 생일 공백체크
    if (e.target.value === "" || e.target.value.length === 0) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $birthSub.style.color = "#ff003e";
      $birthSub.textContent = "생년월일은 필수 입력정보입니다.";
    } else {
      e.target.classList.remove("falsefocus");
      e.target.classList.add("truefocus");
      $birthSub.style.color = "#1a72ff";
      $birthSub.textContent = "";

      flagArray.birthFlag = true;
      checkAllFlagArray(); // 회원가입 가입하기 조건 만족 여부 체크 함수
    }
  }, 500); // setTimeout end
}); // keyup end

// 생일 오늘이후 입력안되는 조건 추가
let today = new Date(); //현재시간

let year = today.getFullYear(); // 현재 연도
let month = String(today.getMonth() + 1).padStart(2, "0"); // 현재 월
let day = String(today.getDate()).padStart(2, "0"); // 현재 일

// 포맷팅한 오늘날짜
var formattedDate = year + "-" + month + "-" + day;

// 날짜 선택창에 최대선택 오늘 속성추가하기
$chooseBirth.setAttribute("max", formattedDate);

// 5. email 조건 검증하기
const $inputEmail = document.querySelector(".email"); // 이메일 입력창 변수설정
const $emailSub = document.querySelector(".th-emailSub"); // 이메일 조건태그 변수설정
const emailValid = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일에 입력가능한 문자열

$inputEmail.addEventListener("keyup", (e) => {
  setTimeout(() => {
    // 이메일 공백체크
    if (e.target.value === "" || e.target.value.length === 0) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $emailSub.style.color = "#ff003e";
      $emailSub.textContent = "이메일은 필수 입력정보입니다.";

      // 이메일 조건체크
    } else if (!emailValid.test(e.target.value)) {
      e.target.classList.remove("truefocus");
      e.target.classList.add("falsefocus");
      $emailSub.style.color = "#ff003e";
      $emailSub.textContent = "올바른 이메일 형식이 아닙니다.";
    } else {
      // 이메일 중복체크
      // fetch 사용
      const $emailAccountValue = $inputEmail.value;
      let encodedEmail = encodeURIComponent($emailAccountValue);
      const URL = `http://localhost:8383/checkemail?email=${encodedEmail}`;
      const IPURL = `http://172.30.1.60:8383/checkemail?email=${encodedEmail}`;

      Promise.any([
        fetch(URL, {
          method: "POST",
          headers: { "content-type": "application/json" },
          body: JSON.stringify({ email: $inputEmail.value }),
        }),
        fetch(IPURL, {
          method: "POST",
          headers: { "content-type": "application/json" },
          body: JSON.stringify({ email: $inputEmail.value }),
        }),
      ])
        .then((res) => res.json())
        .then((json) => {
          if (json === false) {
            e.target.classList.remove("falsefocus");
            e.target.classList.add("truefocus");
            $emailSub.style.color = "#1a72ff";
            $emailSub.textContent = "사용가능한 이메일입니다";

            flagArray.emailFlag = true;
            checkAllFlagArray(); // 회원가입 가입하기 조건 만족 여부 체크 함수
          } else {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $emailSub.style.color = "#ff003e";
            $emailSub.textContent =
              "중복되는 이메일입니다. 다른 이메일을 입력해주세요.";
          }
        });
    }
  }, 500); // setTimeout end
}); // keyup end
