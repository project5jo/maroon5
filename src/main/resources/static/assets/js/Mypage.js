const $Mypage = document.querySelector(".profile img"); // 왼쪽메뉴 프로필 사진
const $leftProfileBtn = document.querySelector('.profile-icon'); // 왼쪽메뉴 프로필 수정 버튼

$Mypage.addEventListener("click", e => {
  console.log("클릭클릭");
  window.location.href = '/mypage-profile'
})

$leftProfileBtn.addEventListener("click", e => {
    console.log("클릭클릭");
    window.location.href = '/mypage-profile'
})



// 폼 태그 모달창
const $CheckBtn = document.querySelector('.check'); // 적용 버튼

const $modal = document.querySelector('.form-modals'); // 모달창
const $modalBack = document.querySelector('.modalBack'); // 모달창 배경

const $modalCancelBtn = document.querySelector('.cancel');// 모달 취소 버튼
const $modalXButton = document.querySelector('.modal-header span'); // 모달창의 X버튼

// 3-1. 적용 버튼을 누르면 재확인 모달창 생성하기
$CheckBtn.addEventListener('click', e => {
  $modal.style.display = 'block';
  $modalBack.style.display = 'block';
  // console.log("인풋저장상태", $profileInputStatus.value);
})

// 3-2. 모달창의 취소버튼을 클릭할 경우 모달창 사라지기
$modalCancelBtn.addEventListener('click', e => {
  // 모달창의 취소버튼
  $modal.style.display = 'none';
  $modalBack.style.display = 'none';
})

$modalXButton.addEventListener('click', e => {
  // 모달창의 X버튼
  $modal.style.display = 'none';
  $modalBack.style.display = 'none';
})

// const allInput = document.querySelectorAll('.right-checkContent input');

// allInput.addEventListener('click', e=> {
//   console.log('클릭클릭');
// })

// function checkInputValue () {
//   let allInputValue = '';
//   allInput.forEach(input => {
//     if(!input.value.trim() === '') {
//       allInputValue = true;
//     }
//   });

//   if (allInputValue) {
//     $CheckBtn.type = 'button';
//   } else {
//     $CheckBtn.type = 'reset';
//   }
// }

// allInput.forEach(input => {
//   input.addEventListener('input', checkInputValue);
// });


