
const $check = document.querySelector('.check'); // 확인 버튼

// 1-1. 이름, 생년월일, 이메일 input 태그 중 어떤것이든 사용가능한 값 변경시 확인버튼 활성화/비활성화 하기
function activeCheck () {
    $check.style.backgroundColor = '#4d3333';

    // 재확인 모달창 열기 이벤트 추가
    $check.addEventListener('click', openModal);
}

function inActiveCheck () {
    $check.style.backgroundColor = 'rgba(82, 51, 51, 0.3)';

        // 재확인 모달창 열기 이벤트 제거
        $check.removeEventListener('click', openModal);
}



// 1. 이름
const $inputName = document.querySelector(".inputName"); // 이름 input 태그
const $nameSub = document.querySelector(".nameSub"); // 이름 조건 태그
const $nameSRC = document.getElementById("nameSRC"); // // 서버로부터 받아온 기존 이름
const nameValid = /^[가-힣]+$/; // 이름에 입력가능한 문자열

$inputName.addEventListener('keyup', e => {

  setTimeout (() => {
    
      // 이름 공백체크
      if(e.target.value === '' || e.target.value.length === 0) {
          e.target.value === $nameSRC.value;
          inActiveCheck ();

          // 이름 조건체크
      } else if (!nameValid.test(e.target.value)) {
          $nameSub.style.color = 'red';
          $nameSub.textContent = "이름은 한글만 입력 (2자 이상)";
          inActiveCheck ();

          // 이름이 조건에 맞을 경우
      } else if (e.target.value.length > 1){
          $nameSub.style.color = 'blue';
          $nameSub.textContent = "사용가능한 이름입니다.";
          activeCheck ();
      }
  }, 500) // setTimeout end
}); // keyup end



// 3. 생년월일
const $inputBirth = document.querySelector(".inputBirth"); // 생년월일
const $birthSub = document.querySelector(".birthSub"); // 생년월일 조건태그 변수설정
const $birthSRC = document.getElementById("birthSRC"); // // 서버로부터 받아온 기존 생년월일

$inputBirth.addEventListener('blur', e => {

  setTimeout (() => {
      // 생일 공백체크
      if(e.target.value === '' || e.target.value.length === 0) {
          e.target.value === $birthSRC.value;
          inActiveCheck ();

      } else {
          $birthSub.style.color = 'blue';
          $birthSub.textContent = "사용가능한 생년월일입니다.";
          activeCheck ();
      }
  }, 500) // setTimeout end
}); // keyup end



// 2. 이메일
const $inputEmail = document.querySelector(".inputEmail"); // 이메일 입력창 변수설정
const $emailSub = document.querySelector(".emailSub"); // 이메일 조건태그 변수설정
const $emailSRC = document.getElementById("emailSRC"); // // 서버로부터 받아온 기존 생년월일
const emailValid = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일에 입력가능한 문자열

$inputEmail.addEventListener('keyup', e => {

  setTimeout (() => {
      // 이메일 공백체크
      if(e.target.value === '' || e.target.value.length === 0) {
          e.target.value === $emailSRC.value;
          inActiveCheck ();

          // 이메일 조건체크
      } else if (!emailValid.test(e.target.value)) {
          $emailSub.style.color = 'red';
          $emailSub.textContent = "올바른 이메일 형식이 아닙니다.";
          inActiveCheck ();
      } else {
          // 이메일 중복체크
          // fetch 사용
          const $emailAccountValue = $inputEmail.value;
          let encodedEmail = encodeURIComponent($emailAccountValue);
          const URL = `http://localhost:8383/checkemail?email=${encodedEmail}`;
          const IPURL = `http://172.30.1.60:8383/checkemail?email=${encodedEmail}`;

          Promise.any([
              fetch(URL, {
                  method: 'POST',
                  headers: {'content-type': 'application/json'},
                  body: JSON.stringify({ email: $inputEmail.value })
              }),
              fetch(IPURL, {
                  method: 'POST',
                  headers: {'content-type': 'application/json'},
                  body: JSON.stringify({ email: $inputEmail.value })
              })
          ])
          .then(res => res.json())
          .then(json => {
              if (json === false) {
                  $emailSub.style.color = 'blue';
                  $emailSub.textContent = '사용가능한 이메일입니다';
                  activeCheck ();

              } else {
                  $emailSub.style.color = 'red';
                  $emailSub.textContent = "중복되는 이메일입니다. 다른 이메일을 입력해주세요.";
                  inActiveCheck ();
              }
          })
      }
  }, 500) // setTimeout end
}); // keyup end


