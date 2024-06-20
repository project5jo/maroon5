const $LoginErrorText = document.querySelector(".LoginErrorText");
const result = $LoginErrorText.innerHTML;
console.log("result: ", result);

if (result === "NO_ACC" || result === "NO_PW") {
  $LoginErrorText.innerHTML = ` <p class="result">아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.<br />
입력하신 내용을 다시 확인해주세요.</p>`;
  console.log("실패");
}
