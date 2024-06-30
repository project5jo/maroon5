const $Mypage = document.querySelector(".profile img");

$Mypage.addEventListener("click", e => {
  console.log("클릭클릭");
})

const $ok = document.querySelector('.ok');
const $success = document.querySelector('.modal-box');
$ok.addEventListener('click', e => {
    console.log('클릭클릭');
    $success.style.display = 'block';
})


// 1. 이름
const $myPageName = document.querySelector(".name input"); // 이름 입력창 변수설정
const $nameSub = document.querySelector(".name p"); // 이름 조건태그 변수설정
const nameValid = /^[가-힣]+$/; // 이름에 입력가능한 문자열
const nameTrim = "${isUpdated ? updatedMember.name : nowMember.name}";

$myPageName.addEventListener('keyup', e => {

  setTimeout (() => {
    
      // 이름 공백체크
      if(e.target.value === '' || e.target.value.length === 0) {
          e.target.value === nameTrim;

          // 이름 조건체크
      } else if (!nameValid.test(e.target.value)) {
          $nameSub.style.color = 'red';
          $nameSub.textContent = "이름은 한글만 입력 (2자 이상)";
          flagArray.nameFlag = false;
          checkAllFlagArray();
      } else if (e.target.value.length > 1){
          $nameSub.style.color = 'blue';
          $nameSub.textContent = "";
          flagArray.nameFlag = true;
          checkAllFlagArray();
      }
  }, 500) // setTimeout end
}); // keyup end

// 3. 생년월일
const $myPageBirth = document.querySelector(".birth input"); // 생년월일
const $birthSub = document.querySelector(".birth p"); // 생년월일 조건태그 변수설정
const birthTrim = "${isUpdated ? updatedMember.birth : nowMember.birth}";

$myPageBirth.addEventListener('blur', e => {

  setTimeout (() => {
      // 생일 공백체크
      if(e.target.value === '' || e.target.value.length === 0) {
          e.target.value === birthTrim;

      } else {
          $birthSub.style.color = 'blue';
          $birthSub.textContent = "";

          flagArray.birthFlag = true;
          checkAllFlagArray();
      }
  }, 500) // setTimeout end
}); // keyup end




// 2. 이메일
const $myPageEmail = document.querySelector(".email input"); // 이메일 입력창 변수설정
const $emailSub = document.querySelector(".email p"); // 이메일 조건태그 변수설정
const emailValid = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일에 입력가능한 문자열
const emailTrim = "${isUpdated ? updatedMember.email : nowMember.email}";

$myPageEmail.addEventListener('keyup', e => {

  setTimeout (() => {
      // 이메일 공백체크
      if(e.target.value === '' || e.target.value.length === 0) {
          e.target.value === emailTrim;

          // 이메일 조건체크
      } else if (!emailValid.test(e.target.value)) {
          $emailSub.style.color = 'red';
          $emailSub.textContent = "올바른 이메일 형식이 아닙니다.";
          flagArray.emailFlag = false;
          checkAllFlagArray();
      } else {
          // 이메일 중복체크
          // fetch 사용
          const $emailAccountValue = $myPageEmail.value;
          let encodedEmail = encodeURIComponent($emailAccountValue);
          const URL = `http://localhost:8383/checkemail?email=${encodedEmail}`;
          const IPURL = `http://172.30.1.60:8383/checkemail?email=${encodedEmail}`;

          Promise.any([
              fetch(URL, {
                  method: 'POST',
                  headers: {'content-type': 'application/json'},
                  body: JSON.stringify({ email: $myPageEmail.value })
              }),
              fetch(IPURL, {
                  method: 'POST',
                  headers: {'content-type': 'application/json'},
                  body: JSON.stringify({ email: $myPageEmail.value })
              })
          ])
          .then(res => res.json())
          .then(json => {
              if (json === false) {
                  $emailSub.style.color = 'blue';
                  $emailSub.textContent = '사용가능한 이메일입니다';

                  flagArray.emailFlag = true;
                  checkAllFlagArray();
              } else {
                  $emailSub.style.color = 'red';
                  $emailSub.textContent = "중복되는 이메일입니다. 다른 이메일을 입력해주세요.";
                  flagArray.emailFlag = false;
                  checkAllFlagArray();
              }
          })
      }
  }, 500) // setTimeout end
}); // keyup end


