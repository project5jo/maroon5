document.getElementById('addProductForm').addEventListener('submit', function(event) {
  let form = this;
  let isValid = true;
  let fields = ['shop_item_name', 'shop_item_desc', 'shop_item_price', 'shop_item_stock', 'shop_item_img'];
  fields.forEach(function(field) {
    if (!form[field].value) {
      isValid = false;
    }
  });

  if (!isValid) {
    alert('상품 추가에 실패했습니다. 모든 필드를 입력해주세요.');
    event.preventDefault();
  }
});