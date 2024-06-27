function confirmDeletion(itemId) {
  if (confirm("정말로 이 상품을 삭제하시겠습니까?")) {
    window.location.href = `/shop/delete/${itemId}`; // 여기는 실제 삭제 로직을 처리하는 URL로 변경하세요.
  }
}