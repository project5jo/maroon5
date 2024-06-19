
// 회원가입 입력 검증처리

// export const checkAvailability = async (type, keyword) => {
//   const response = await fetch(`http://localhost:8383/members/check?type=${type}&keyword=${keyword}`);
//   const flag = await response.json();
//   return !flag;
// };

const checkId = async(value) => {
  const response = await fetch(`http://localhost:8383/create?account=${value}`);
  const flag = await response.json();
  console.log("json", flag);
  console.log("json", json);
}

// const checkId = async () {
//   const url = 'http://localhost:8383/create';  // 실제 API 엔드포인트 URL로 변경 필요
//   const userAccount = document.querySelector(".account").value; // 아이디 입력창의 값

//   try {
//       const response = await fetch(url, {
//           method: 'POST',
//           headers: {
//               'Content-Type': 'application/json'
//           },
//           body: JSON.stringify({ userAccount: userAccount }),
//       });

//       console.log("Response:", response);  // 응답 확인

//       // 응답 데이터를 JSON으로 파싱하여 사용할 경우
//       const responseData = await response.json();
//       console.log("Response Data:", responseData);  // 파싱된 데이터 확인

//   } catch (error) {
//       console.error('Error:', error);
//       // 오류 처리
//   }
// }

// input 태그 공통 효과
// 해당 창을 클릭했을 때 창에 클래스 추가해서 스타일 변경하기
const $trs = document.querySelectorAll(".tr input");
$trs.forEach(tr => {
  tr.addEventListener('focus', e => {
    e.target.classList.add("nowfocus");
  });

  tr.addEventListener('blur', e => {
    e.target.classList.remove("nowfocus");
  });
});

// 1. 아이디 입력 검증 처리
const $account = document.querySelector(".account"); // 아이디 입력창
const $accountSub = document.querySelector(".th-accountSub"); // 아이디 조건위치
const $inputAccount = $account.value.trim(); // 공백제외 입력된 아이디값

$account.addEventListener('focus', e => {

  // 해당 창을 클릭했을 때 창에 클래스 추가해서 스타일 변경하기
  if ($account.value === '') {
    e.target.classList.add("focus");
    $accountSub.style.color = 'red';
    $accountSub.textContent = "아이디를 입력해주세요.";
  }

  const accountValid = /^[a-z0-9]{4,14}$/; // 아이디에 입력가능한 문자열

  $account.addEventListener('keyup', e => {
    setTimeout (() => {

      if($account.value === '') {
        $accountSub.style.color = 'red';
        $accountSub.textContent = "아이디는 필수입력정보입니다.";
      } else if (!accountValid.test($account.value)) {
        $accountSub.style.color = 'red';
        $accountSub.textContent = "아이디는 영문 소문자/숫자 조합 (4~16자)";
      }else {
        checkId($inputAccount);
        $accountSub.textContent = '사용가능한 아이디입니다';
        e.target.classList.remove("focus");
      }}, 500);

  });

});

$account.addEventListener('blur', e => {
  if ($account.value === '') {
    $accountSub.style.color = 'red';
    $accountSub.textContent = "아이디는 필수입력정보입니다."
  }
});

// function checkId (account) {
//   $.ajax({
//     url: "/create",
//     type: "post",
//     dataType: "json",
//     data: {account: account},
//     success: function(response) {
//       if(response === 'exists') {
//           $accountSub.textContent = "중복되는 아이디입니다."
//       } else {
//           $accountSub.textContent = "사용가능한 아이디입니다."
//       }
//     }
//   });

  // // 서버 URL
  // const url = 'http://localhost:8383/create';



// postData 함수 호출 예시

  // // fetch api 를 사용하여 서버에 post 요청을 보내기
  // const Response = await fetch(url, {
  //   method: 'POST',
  //   headers: {
  //     'Content-Type': 'application/json'
  //   },
  //   body: JSON.stringify({ account: $account.value }),
  // });
  // console.log("rr ", Response);


// 2. 비밀번호 입력 검증 처리
const $firstPassword = document.querySelector(".pw1"); // 비밀번호 입력창 1
const $secondPassword = document.querySelector(".pw2"); // 비밀번호 입력창 2
const $passwordSub1 = document.querySelector(".th-passwordSub1");// 비밀번호 조건위치 1
const $passwordSub2 = document.querySelector(".th-passwordSub2");// 비밀번호 조건위치 2

$firstPassword.addEventListener('focus', e => {

  // 해당 창을 클릭했을 때 창에 클래스 추가해서 스타일 변경하기
  e.target.classList.add("focus");
  $passwordSub1.style.color = 'red';
  $passwordSub1.textContent = "비밀번호를 입력해주세요.";

  const passwordValid = /^(?=.*[a-zA-Z!@#$%^&*()\-_=+{};:,<.>])(?=.*\d).{8,16}$/; // 비밀번호에 입력가능한 문자열

  $firstPassword.addEventListener('keyup', e => {
    setTimeout (() => {

      if($firstPassword.value === '') {
        $passwordSub1.style.color = 'red';
        $passwordSub1.textContent = "비밀번호는 필수입력정보입니다.";
      } else if (!passwordValid.test($firstPassword.value)) {
        $passwordSub1.style.color = 'red';
        $passwordSub1.textContent = "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
      } else {
        $passwordSub1.textContent = '';
        e.target.classList.remove("focus");
      }}, 500);
  });

});

$firstPassword.addEventListener('blur', e => {
  if ($firstPassword.value === '') {
    $passwordSub1.style.color = 'red';
    $passwordSub1.textContent = "비밀번호는 필수입력정보입니다."
  }
});


// 3. 이름 입력 검증 처리
const $name = document.querySelector(".name");

// 4. 생일 입력 검증 처리
const $birth = document.querySelector(".birth");

// 5. 이메일 입력 검증 처리
const $email = document.querySelector(".email");

console.log("하이하이");
