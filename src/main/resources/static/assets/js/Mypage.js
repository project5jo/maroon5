// 프로필 오른쪽 하단 아이콘 클릭 시 프로필 수정창으로 이동
function openProfile () {
  window.location.href = "/mypage-profile";
}

// form 태그 모달창 열기
const $modal = document.querySelector('.middleModal-box'); // 모달창

function openModal () {
  $modal.style.display = 'block';
}

function closeModal () {
  $modal.style.display = 'none';
}

