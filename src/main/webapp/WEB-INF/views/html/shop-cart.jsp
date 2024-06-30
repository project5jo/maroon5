<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <link rel="stylesheet" href="/assets/css/shop-cart.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
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
        <div class="cart-item">
            <a href="">
                <img src="${cartItem.shopItemImg}" alt="${cartItem.shopItemName}">
            </a>
            <div class="item-details">
                <h3>${cartItem.shopItemName}</h3>
                <p>${cartItem.shopItemDesc}</p>
            </div>
            <div class="item-price">
                <span>₩ ${cartItem.cartTotalPrice}</span>
            </div>
            <div class="item-quantity">
                <button class="quantity-btn" onclick="updateQuantity(`${cartItem.shopItemId}`, -1)">-</button>
                <span>${cartItem.cartTotalCount}</span>
                <button class="quantity-btn" onclick="updateQuantity(`${cartItem.shopItemId}`, 1)">+</button>
            </div>
            <div class="item-remove">
                <button class="remove-btn" onclick="removeItem(`${cartItem.shopItemId}`)">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </div>
        </div>
    </c:forEach>
    <!-- 아이템 반복 끝 -->
    <div class="cart-footer">
        <div class="subtotal">
            <span>Subtotal:</span>
            <span>₩${cartTotalPrice}</span>
        </div>
        <button class="checkout-btn">CHECKOUT</button>
        <p>Tax included and shipping calculated at checkout</p>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script>
    function updateQuantity(itemId, change) {
        // TODO: Implement quantity update logic
        console.log("Updating quantity for item", itemId, "by", change);
    }

    function removeItem(itemId) {
        // TODO: Implement item removal logic
        console.log("Removing item", itemId);
    }
</script>
</body>
</html>
