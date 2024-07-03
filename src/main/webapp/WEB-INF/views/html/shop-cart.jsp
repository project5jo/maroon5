<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <link rel="stylesheet" href="/assets/css/shop-cart.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
    <link rel="stylesheet" href="/assets/css/footer.css"/>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="cart-container">
    <div class="cart-header">
        <h2>Cart</h2>
    </div>
    <div class="product">
        <h2 class="">Product</h2>
        <h2 class="price">Price</h2>
        <h2 class="QTY">QTY</h2>
    </div>
    <!-- 아이템 반복 -->
    <c:forEach var="cartItem" items="${cartItems}">
        <div class="cart-item" data-item-id="${cartItem.shopItemId}">
            <a href="">
                <img src="${cartItem.shopItemImg}" alt="${cartItem.shopItemName}">
            </a>
            <div class="item-details">
                <h3>${cartItem.shopItemName}</h3>
                <p>${cartItem.shopItemDesc}</p>
            </div>
            <div class="item-price">
                <span>₩ <span class="item-price-value">${cartItem.cartTotalPrice}</span></span>
            </div>
            <div class="item-quantity">
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
    </c:forEach>
    <!-- 아이템 반복 끝 -->
    <div class="cart-footer">
        <div class="subtotal">
            <span>Subtotal:</span>
            <span>₩ <span id="cartTotalPrice">${cartTotalPrice}</span></span>
        </div>
        <!-- <form action="/checkout" method="post"> -->
            <!-- <input type="hidden" name="userAccount" value="${sessionScope.loginUser.account}"> -->
            <button  class="checkout-btn"><a href="/checkout">CHECKOUT</a></button>
        <!-- </form> -->
        <p>Tax included and shipping calculated at checkout</p>
    </div>
</div>

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
        itemPriceElement.textContent = newPrice.toFixed(2);
        updateTotalPrice();
    }

    function updateTotalPrice() {
        let totalPrice = 0;
        const priceElements = document.querySelectorAll('.item-price-value');
        priceElements.forEach(function(priceElement) {
            totalPrice += parseFloat(priceElement.textContent);
        });
        document.getElementById('cartTotalPrice').textContent = totalPrice.toFixed(2);
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
</script>
<script src="https://kit.fontawesome.com/a9dfb46732.js" crossorigin="anonymous"></script>
</body>
</html>
