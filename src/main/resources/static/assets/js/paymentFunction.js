//====================== 함수

//결제부분 에러 함수
export const payErrorStyle = (text, $InputPointBtn, $btnPay) => {
  $InputPointBtn.classList.add("falsefocus");
  $InputPointBtn.value = "";
  $InputPointBtn.placeholder = text;
  $btnPay.type = "button";
};

export function scrollToError(errorDiv) {
  errorDiv.scrollIntoView({ behavior: "smooth", block: "center" });
}
