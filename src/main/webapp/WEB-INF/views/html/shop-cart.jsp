<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>장바구니 | 애착 페이지</title>
    <link rel="stylesheet" href="/assets/css/shop-cart.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
    <link rel="stylesheet" href="/assets/css/footer.css"/>
  <link rel="icon" href="/assets/img/favicon.ico">
</head>
<body>
<%@ include file="../include/header.jsp" %>

<!-- main -->
<main>
    <div class="cart-container">
      <!-- <div class="product">
        <div class="inner">
          <div class="product-wrapper">
            <h2 class="product-title">Product</h2>
            <h2 class="price">Price</h2>
            <h2 class="QTY">QTY</h2>
          </div>
        </div>
      </div> -->
      <!-- 아이템 반복 -->
      <c:forEach var="cartItem" items="${cartItems}">
        <div class="cart-item" data-item-id="${cartItem.shopItemId}">
          <div class="inner">
            <div class="cart-item-wrapper">
              <!-- img -->
              <div class="cart-item-img-box">
                <a href="">
                  <img src="${cartItem.shopItemImg}" alt="${cartItem.shopItemName}">
                </a>
              </div>
              <!-- 상품 설명 -->
              <div class="cart-item-details-box">
                <div class="item-details">
                  <p class="shop-item-desc">${cartItem.shopItemDesc}</p>
                  <p class="title point">${cartItem.shopItemName}</p>
                  <p class="price">${cartItem.unitPrice} 원</p>
                </div>
              </div>
              <div class="item-price">
                <p class="item-price-value point">${cartItem.cartTotalPrice}원</p>
              </div>
              <div class="item-quantity">
                <div class="quantity-wrapper">
                  <div class="item-num-wrapper">
                    <button class="quantity-btn" onclick="decrementQuantity(${cartItem.shopItemId}, ${cartItem.unitPrice})">-</button>
                    <input type="number" class="quantity-input" value="${cartItem.cartTotalCount}" min="1" data-price="${cartItem.unitPrice}" oninput="updatePrice(${cartItem.shopItemId}, ${cartItem.unitPrice})">
                    <button class="quantity-btn" onclick="incrementQuantity(${cartItem.shopItemId}, ${cartItem.unitPrice})">+</button>
                  </div>
                  <div class="item-remove">
                    <button class="remove-btn" onclick="removeItem(${cartItem.shopItemId})">
                      <i class="fa-solid fa-trash"></i>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      <!-- 아이템 반복 끝 -->
      <div class="inner">
        <div class="cart-footer">
          <div class="cart-footer-wrapper">
            <div class="subtotal">
              <span class="point">Subtotal:</span>
              <span id="cartTotalPrice" class="point">${cartTotalPrice}원</span>
            </div>
            <button onclick="checkout()" class="checkout-btn point">CHECKOUT</button>
            <p>Tax included and shipping calculated at checkout</p>
          </div>
        </div>
      </div>
    </div>
  </main>


<%@ include file="../include/footer.jsp" %>
<script src="/assets/js/category.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script>
    function incrementQuantity(itemId, itemPrice) {
        const quantityInput = document.querySelector('.cart-item[data-item-id="' + itemId + '"] .quantity-input');
        quantityInput.value = parseInt(quantityInput.value) + 1;
        updatePrice(itemId, itemPrice);
    }

    function decrementQuantity(itemId, itemPrice) {
        const quantityInput = document.querySelector('.cart-item[data-item-id="' + itemId + '"] .quantity-input');
        if (parseInt(quantityInput.value) > 1) {
            quantityInput.value = parseInt(quantityInput.value) - 1;
            updatePrice(itemId, itemPrice);
        }
    }

    function updatePrice(itemId, itemPrice) {
        const quantityInput = document.querySelector('.cart-item[data-item-id="' + itemId + '"] .quantity-input');
        const itemPriceElement = document.querySelector('.cart-item[data-item-id="' + itemId + '"] .item-price-value');
        const newPrice = quantityInput.value * itemPrice;
        itemPriceElement.textContent = newPrice +"원";
        updateTotalPrice();
    }

    function updateTotalPrice() {
        let totalPrice = 0;
        const priceElements = document.querySelectorAll('.item-price-value');
        priceElements.forEach(function(priceElement) {
            totalPrice += parseInt(priceElement.textContent);
        });
        document.getElementById('cartTotalPrice').textContent = totalPrice+"원";
    }

    function removeItem(itemId) {
        $.post("/cart/remove", { itemId: itemId }, function() {
            const cartItem = document.querySelector('.cart-item[data-item-id="' + itemId + '"]');
            cartItem.remove();
            updateTotalPrice();
        }).fail(function() {
            alert("장바구니에서 상품을 제거하는 중 오류가 발생했습니다.");
        });
    }

    function checkout() {
        const cartItems = [];
        document.querySelectorAll('.cart-item').forEach(item => {
            const itemId = item.getAttribute('data-item-id');
            const quantity = item.querySelector('.quantity-input').value;
            const totalPrice = item.querySelector('.item-price-value').textContent;
            cartItems.push({ itemId, quantity, totalPrice });
        });

        $.ajax({
            url: '/cart/update',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ cartItems: cartItems }),
            success: function(response) {
                window.location.href = '/checkout';
            },
            error: function(error) {
                alert('장바구니 업데이트 중 오류가 발생했습니다.');
            }
        });
    }
</script>
<script src="https://kit.fontawesome.com/a9dfb46732.js" crossorigin="anonymous"></script>
</body>
</html>
