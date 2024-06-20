
// 1. account 조건 검증하기
let idFlag = false; // 아이디 조건확인
const $inputAccount = document.querySelector(".account"); // 아이디 입력창 변수설정
const $accountSub = document.querySelector(".th-accountSub"); // 아이디 조건태그 변수설정
const accountValid = /^[a-z0-9]{4,14}$/; // 아이디에 입력가능한 문자열

$inputAccount.addEventListener('keyup', e => {

    setTimeout (() => {
        // 아이디 공백체크
        if(e.target.value === '' || e.target.value.length === 0) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $accountSub.style.color = 'red';
            $accountSub.textContent = "아이디는 필수 입력정보입니다.";

            // 아이디 조건체크
        } else if (!accountValid.test(e.target.value)) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $accountSub.style.color = 'red';
            $accountSub.textContent = "아이디는 영문 소문자/숫자 조합 (4~16자)";
        } else {
            // 아이디 중복체크
            // fetch 사용
            const $inputAccountValue = $inputAccount.value;
            let encodedAccount = encodeURIComponent($inputAccountValue);
            const URL = `http://localhost:8383/checkid?account=${encodedAccount}`;
            const IPURL = `http://172.30.1.60:8383/checkid?account=${encodedAccount}`;
            fetch(URL && IPURL, {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify({ account: $inputAccount.value })
            })
            .then(res => res.json())
            .then(json => {
                if (json === false) {
                    e.target.classList.remove("falsefocus");
                    e.target.classList.add("truefocus");
                    $accountSub.style.color = 'blue';
                    $accountSub.textContent = '사용가능한 아이디입니다';

                    idFlag = true;

                } else {
                    e.target.classList.remove("truefocus");
                    e.target.classList.add("falsefocus");
                    $accountSub.style.color = 'red';
                    $accountSub.textContent = "중복되는 아이디입니다. 다른 아이디를 입력해주세요.";
                }
            })
        }
        
    }, 500) // setTimeout end

}); // keyup end


// 2. password 조건 검증하기
let firstPasswordFlag = false; // 첫번째 비밀번호 조건확인
let secondPasswordFlag = false; // 두번째 비밀번호 조건확인

let firstPasswordValue = '';
let secondPasswordValue = '';

const $inputFirstPassword = document.querySelector(".pw1"); // 첫번째 비밀번호 입력창 변수설정
const $inputSecondPassword = document.querySelector(".pw2"); // 두번째 비밀번호 입력창 변수설정
const $firstPasswordSub = document.querySelector(".th-passwordSub1"); // 첫번째 비밀번호 조건태그 변수설정
const $secondPasswordSub = document.querySelector(".th-passwordSub2"); // 두번째 비밀번호 조건태그 변수설정
const passwordValid = /^(?=.*[a-zA-Z!@#$%^&*()\-_=+{};:,<.>])(?=.*\d).{8,16}$/; //비밀번호에 입력가능한 문자열

$inputFirstPassword.addEventListener('input', e => {

    setTimeout (() => {
        // 비밀번호 공백체크
        if(e.target.value === '' || e.target.value.length === 0) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $firstPasswordSub.style.color = 'red';
            $firstPasswordSub.textContent = "비밀번호는 필수 입력정보입니다.";

            // 비밀번호 조건체크
        } else if (!passwordValid.test(e.target.value)) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $firstPasswordSub.style.color = 'red';
            $firstPasswordSub.textContent = "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
        } else {
            $firstPasswordSub.textContent = "";
            $secondPasswordSub.textContent = "";
            e.target.classList.remove("falsefocus");
            e.target.classList.add("truefocus");

            if ($inputSecondPassword.value !== '' || $inputSecondPassword.value.length !== 0) {

                if ($inputSecondPassword.value !== $inputFirstPassword.value) {
                    $inputSecondPassword.classList.remove("truefocus");
                    $inputSecondPassword.classList.add("falsefocus");

                    e.target.classList.remove("truefocus");
                    e.target.classList.add("falsefocus");
    
                    $secondPasswordSub.style.color = 'red';
                    $secondPasswordSub.textContent = "입력하신 비밀번호가 일치하지 않습니다.";
                } else {
                    $inputSecondPassword.classList.remove("falsefocus");
                    $inputSecondPassword.classList.add("truefocus");
                    $firstPasswordSub.textContent = "";
    
                    e.target.classList.remove("falsefocus");
                    e.target.classList.add("truefocus");
                    $secondPasswordSub.style.color = 'blue';
                    $secondPasswordSub.textContent = "사용가능한 비밀번호입니다.";
    
                    firstPasswordFlag = true;
                    secondPasswordFlag = true;
                }
            }
        }
    }, 500) // setTimeout end

}); // keyup end

$inputSecondPassword.addEventListener('input', e => {

    setTimeout (() => {

        if(e.target.value === '' || e.target.value.length === 0) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $secondPasswordSub.style.color = 'red';
            $secondPasswordSub.textContent = "비밀번호는 필수 입력정보입니다.";

            // 비밀번호 조건체크
        } else if (!passwordValid.test(e.target.value)) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $secondPasswordSub.style.color = 'red';
            $secondPasswordSub.textContent = "비밀번호는 영문/숫자/특수문자 2가지 이상 조합 (8~16자)";
        } else {
            $firstPasswordSub.textContent = "";
            $secondPasswordSub.textContent = "";
            e.target.classList.remove("falsefocus");
            e.target.classList.add("truefocus");

            if ($inputFirstPassword.value !== '' || $inputFirstPassword.value.length !== 0) {

                if ($inputSecondPassword.value !== $inputFirstPassword.value) {
                    $inputFirstPassword.classList.remove("truefocus");
                    $inputFirstPassword.classList.add("falsefocus");

                    e.target.classList.remove("truefocus");
                    e.target.classList.add("falsefocus");
    
                    $secondPasswordSub.style.color = 'red';
                    $secondPasswordSub.textContent = "입력하신 비밀번호가 일치하지 않습니다.";
                } else {
                    $inputFirstPassword.classList.remove("falsefocus");
                    $inputFirstPassword.classList.add("truefocus");
                    $firstPasswordSub.textContent = "";
    
                    e.target.classList.remove("falsefocus");
                    e.target.classList.add("truefocus");
                    $secondPasswordSub.style.color = 'blue';
                    $secondPasswordSub.textContent = "사용가능한 비밀번호입니다.";
    
                    firstPasswordFlag = true;
                    secondPasswordFlag = true;
                }
            }
        }
        
    }, 500) // setTimeout end

}); // keyup end


// 3. name 조건 검증하기
let nameFlag = false; // 이름 조건확인
const $inputName = document.querySelector(".name"); // 이름 입력창 변수설정
const $nameSub = document.querySelector(".th-nameSub"); // 이름 조건태그 변수설정
const nameValid = /^[가-힣]+$/; // 이름에 입력가능한 문자열

$inputName.addEventListener('keyup', e => {

    setTimeout (() => {
        // 이름 공백체크
        if(e.target.value === '' || e.target.value.length === 0) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $nameSub.style.color = 'red';
            $nameSub.textContent = "이름은 필수 입력정보입니다.";

            // 이름 조건체크
        } else if (!nameValid.test(e.target.value)) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $nameSub.style.color = 'red';
            $nameSub.textContent = "이름은 한글만 입력 (2자 이상)";
        } else if (e.target.value.length > 1){
            e.target.classList.remove("falsefocus");
            e.target.classList.add("truefocus");
            $nameSub.style.color = 'blue';
            $nameSub.textContent = "";

            nameFlag = true;
        }
    }, 500) // setTimeout end

}); // keyup end


// 4. birth 조건 검증하기
let birthFlag = false; // 생일 조건확인
const $chooseBirth = document.querySelector(".birth"); // 생일선택창 변수설정
const $birthSub = document.querySelector(".th-birthSub"); // 이름 조건태그 변수설정

$chooseBirth.addEventListener('blur', e => {

    setTimeout (() => {
        // 생일 공백체크
        if(e.target.value === '' || e.target.value.length === 0) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $birthSub.style.color = 'red';
            $birthSub.textContent = "생년월일은 필수 입력정보입니다.";

            // 이름 조건체크
        } else {
            e.target.classList.remove("falsefocus");
            e.target.classList.add("truefocus");
            $birthSub.style.color = 'blue';
            $birthSub.textContent = "";

            birthFlag = true;
        }
    }, 500) // setTimeout end

}); // keyup end


// 5. email 조건 검증하기
let emailFlag = false; // 이메일 조건확인
const $inputEmail = document.querySelector(".email"); // 이메일 입력창 변수설정
const $emailSub = document.querySelector(".th-emailSub"); // 이메일 조건태그 변수설정
const emailValid = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일에 입력가능한 문자열

$inputEmail.addEventListener('keyup', e => {

    setTimeout (() => {
        // 이메일 공백체크
        if(e.target.value === '' || e.target.value.length === 0) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $emailSub.style.color = 'red';
            $emailSub.textContent = "이메일은 필수 입력정보입니다.";

            // 이메일 조건체크
        } else if (!emailValid.test(e.target.value)) {
            e.target.classList.remove("truefocus");
            e.target.classList.add("falsefocus");
            $emailSub.style.color = 'red';
            $emailSub.textContent = "올바른 이메일 형식이 아닙니다.";
        } else {
            // 이메일 중복체크
            // fetch 사용
            const $emailAccountValue = $inputEmail.value;
            let encodedEmail = encodeURIComponent($emailAccountValue);
            const URL = `http://localhost:8383/checkemail?email=${encodedEmail}`;
            const IPURL = `http://172.30.1.60:8383/checkemail?email=${encodedEmail}`;
            fetch(URL && IPURL, {
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify({ email: $inputEmail.value })
            })
            .then(res => res.json())
            .then(json => {
                if (json === false) {
                    e.target.classList.remove("falsefocus");
                    e.target.classList.add("truefocus");
                    $emailSub.style.color = 'blue';
                    $emailSub.textContent = '사용가능한 이메일입니다';

                    emailFlag = true;

                } else {
                    e.target.classList.remove("truefocus");
                    e.target.classList.add("falsefocus");
                    $emailSub.style.color = 'red';
                    $emailSub.textContent = "중복되는 이메일입니다. 다른 이메일을 입력해주세요.";
                }
            })
        }
        
    }, 500) // setTimeout end

}); // keyup end
