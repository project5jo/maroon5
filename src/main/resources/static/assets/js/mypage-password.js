
const $inputNow = document.querySelector(".inputNow"); // 새 비밀번호 input 태그
const $nowSub = document.querySelector(".nowSub"); // 새 비밀번호 조건태그 변수설정

const $inputFirst = document.querySelector(".inputFirst"); // 새 비밀번호 input 태그
const $firstSub = document.querySelector(".firstSub"); // 새 비밀번호 조건태그 변수설정

const $inputSecond= document.querySelector(".inputSecond"); // 새 비밀번호 확인 input 태그
const $secondSub = document.querySelector(".secondSub"); // 새 비밀번호 확인 조건태그 변수설정

const passwordValid = /^(?=.*[a-zA-Z!@#$%^&*()\-_=+{};:,<.>])(?=.*\d).{7,15}$/; //비밀번호에 입력가능한 문자열

// $nowSub.style.color = "blue";
//             $nowSub.textContent = "";
$inputNow.addEventListener('input', e => {
  setTimeout(() => {
      // 비밀번호 공백체크
      if (e.target.value.trim() === "" || e.target.value.length === 0) {
        $nowSub.style.color = "red";
        $nowSub.textContent = "비밀번호는 필수 입력정보입니다.";
      }else {
        const $inputNowValue = $inputNow.value;
        let encodedPassword = encodeURIComponent($inputNowValue);
        const URL = `/checkpassword?password=${encodedPassword}`;
        // const URL = `/checkpassword`;

        fetch(URL, {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify({ password: $inputNowValue })
            })
      //   fetch(URL, {
      //     method: 'POST',
      //     headers: {
      //         'Content-Type': 'application/json'
      //     },
      //     body: JSON.stringify({
      //         password: encodedPassword
      //     })
      // })
        .then(res => res.json())
        .then(json => {
            if (json === true) {
              $nowSub.style.color = 'blue';
              $nowSub.textContent = '현재 비밀번호와 일치합니다';

            } else {
              $nowSub.style.color = 'red';
              $nowSub.textContent = "현재 비밀번호와 일치하지 않습니다. 다시입력해주세요.";
            }
        })
      } 
  })
});



$inputFirst.addEventListener("input", (e) => {
    setTimeout(() => {
      // 비밀번호 공백체크
      if (e.target.value === "" || e.target.value.length === 0) {
        $firstSub.style.color = "red";
        $firstSub.textContent = "비밀번호는 필수 입력정보입니다.";
  
        // 비밀번호 조건체크
      } else if (!passwordValid.test(e.target.value)) {
        $firstSub.style.color = "red";
        $firstSub.textContent = "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
      } else {
        $firstSub.textContent = "";
        $secondSub.textContent = "";
        $firstSub.style.color = "blue";
        $secondSub.style.color = "blue";

  
        if (
            $inputSecond.value.trim() !== "" || $inputSecond.value.length !== 0
        ) {
          if ($inputSecond.value !== $inputFirst.value) {
            $secondSub.style.color = "red";
            $secondSub.textContent = "입력하신 비밀번호가 일치하지 않습니다.";
          } else {
            $secondSub.style.color = "blue";
            $secondSub.textContent = "사용가능한 비밀번호입니다.";
          }
        }
      }
    }, 500); // setTimeout end
}); // keyup end

$inputSecond.addEventListener("input", (e) => {
    setTimeout(() => {
      // 비밀번호 공백체크
      if (e.target.value.trim() === "" || e.target.value.length === 0) {
        $secondSub.style.color = "red";
        $secondSub.textContent = "비밀번호는 필수 입력정보입니다.";
  
        // 비밀번호 조건체크
      } else if (!passwordValid.test(e.target.value)) {
        $secondSub.style.color = "red";
        $secondSub.textContent = "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
      } else {
        $firstSub.textContent = "";
        $secondSub.textContent = "";
        $firstSub.style.color = "blue";
        $secondSub.style.color = "blue";

  
        if (
            $inputFirst.value.trim() !== "" || $inputFirst.value.length !== 0
        ) {
          if ($inputSecond.value !== $inputFirst.value) {
            $secondSub.style.color = "red";
            $secondSub.textContent = "입력하신 비밀번호가 일치하지 않습니다.";
          } else {
            $secondSub.style.color = "blue";
            $secondSub.textContent = "사용가능한 비밀번호입니다.";
          }
        }
      }
    }, 500); // setTimeout end
}); // keyup end


const $check = document.querySelector('.check'); // 확인 버튼


// 조건 만족여부에 따른 수정하기 버튼 활성화
function checkAllMatch() {

    // 현재 비밀번호가 공백이 아니고, 새 비밀번호와 새 비밀번호 확인이 일치하는지 확인
    if ($inputNow.value.trim() !== '' && $inputFirst.value.trim() !== '' && $inputSecond.value.trim() !== '' && $inputFirst.value === $inputSecond.value) {
        $check.style.backgroundColor = '#4d3333';
        $check.addEventListener('click', openModal);
    } else {
        $check.style.backgroundColor = 'rgba(82, 51, 51, 0.3)';
        $check.removeEventListener('click', openModal);
    }
}
  
