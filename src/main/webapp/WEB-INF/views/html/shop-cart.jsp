<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="/assets/css/shop-cart.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
</head>
<body>
    <div class="cart-container">
        <div class="cart-header">
            <h2>Cart</h2>
        </div>
        <c:forEach var="cart" items="${cartItems}">
            <c:forEach var="item" items="${cart.items}">
                <div class="cart-item">
                    <a href="">
                        <img src="${item.shopItemImg}" alt="name">
                    </a>
                    <div class="item-details">
                        <h3>${item.shopItemName}</h3>
                        <p>${item.shopItemDesc}</p>
                    </div>
                    <div class="item-price">
                        <span>₩ ${item.shopItemPrice}</span>
                    </div>
                    <div class="item-quantity">
                        <button class="quantity-btn">-</button>
                        <span>${item.quantity}</span>
                        <button class="quantity-btn">+</button>
                    </div>
                    <div class="item-remove">
                        <button class="remove-btn">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </div>
                </div>
            </c:forEach>
        </c:forEach>
        <div class="cart-footer">
            <div class="subtotal">
                <span>Subtotal:</span>
                <span>₩${cartTotalPrice}</span>
            </div>
            <button class="checkout-btn">CHECKOUT</button>
            <p>Tax included and shipping calculated at checkout</p>
        </div>
    </div>
</body>
</html>
