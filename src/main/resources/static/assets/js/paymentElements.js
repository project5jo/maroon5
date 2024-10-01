//====================== 변수
const $inputPhonNum = document.querySelector(".receiver-phone");
const $PointBtn = document.querySelector(".btn-point");
const $InputPointBtn = document.querySelector(".usesPoint");
const $pointInfo = document.querySelector(".user-point-info");
const $btnPay = document.querySelector(".btn-pay");
const totalOrderPrice = document.querySelector(".total-price").innerHTML;
const requiredFields = document.querySelectorAll("[required]");

const $payBtn = document.querySelector(".btn-pay");
const numPatten = /^[0-9]*$/;

export {
  $inputPhonNum,
  $PointBtn,
  $InputPointBtn,
  $pointInfo,
  $btnPay,
  totalOrderPrice,
  requiredFields,
  $payBtn,
  numPatten,
};
