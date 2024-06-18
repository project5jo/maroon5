
// 회원가입 입력 검증처리

// input 태그 공통 효과
// 해당 창을 클릭했을 때 창에 클래스 추가해서 스타일 변경하기
const $trs = document.querySelectorAll(".tr input");
$trs.forEach(tr => {
  tr.addEventListener('focus', e => {
    e.target.classList.add("focus");
  });

  tr.addEventListener('blur', e => {
    e.target.classList.remove("focus");
  });
});

// 1. 아이디 입력 검증 처리
const $account = document.querySelector(".account"); // 아이디 입력창
const $accountSub = document.querySelector(".th-accountSub"); // 아이디 조건위치

$account.addEventListener('click', e => {

  const accountValid = value.replace(/^[a-z0-9]+$/); // 아이디에 입력가능한 문자열

  if (!accountValid.test($account.value)) {
    $accountSub.textContent = "아이디를 정확히 입력해주세요.";
  } else{$accountSub.textContent = "";}

  // setTimeout (() => {
    
  // })
})



// $account.addEventListener('keyup', e => {

//   setTimeout (() => {
//     if ($account.value === '') {
//       $accountSub.textContent = "아이디는 필수입력정보입니다.";
//     } 
//   }, 2000);

//   // console.log($account.value);
// })

// 2. 비밀번호 입력 검증 처리
const $firstPassword = document.querySelector(".pw1"); // 비밀번호 입력창 1
const $secondPassword = document.querySelector(".pw2"); // 비밀번호 입력창 2
const $passwordSub = document.querySelector("th-passwordSub");// 비밀번호 조건위치

// 3. 이름 입력 검증 처리
const $name = document.querySelector(".name");

// 4. 생일 입력 검증 처리
const $birth = document.querySelector(".birth");

// 5. 이메일 입력 검증 처리
const $email = document.querySelector(".email");

console.log("하이하이");
