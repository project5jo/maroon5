<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세 정보</title>
    <link rel="stylesheet" href="/assets/css/shop-detail.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <div class="main-container">
        <div class="img-box">
            <img src="${item.shopItemImg}" alt="-">
        </div>
        <div class="details">
            <div class="description">
                <h1>${item.shopItemName}</h1>
                <p><br><br>
                    ${item.shopItemDesc}<br>
                </p>
            </div>
            <form id="addToCartForm" action="/addToCart" method="post">
                <div class="quantity">
                    <button type="button" onclick="decrementQuantity()">-</button>
                    <input type="number" name="quantity" id="quantityInput" value="1" min="1" step="1" oninput="updatePrice()">
                    <button type="button" onclick="incrementQuantity()">+</button>
                </div>
                <div class="addBtn">
                    <input type="hidden" name="itemId" value="${item.shopItemId}">
                    <input type="hidden" name="itemPrice" id="itemPrice" value="${item.shopItemPrice}">
                    <button type="submit">Add to cart</button>
                </div>
            </form>
            <div class="price" id="totalPrice">가격: ₩${item.shopItemPrice}</div>
        </div>
    </div>
    <div class="what">
        <p>Elevate your home with our thoughtfully curated sensory collection. From artisanal incense holders to aromatic candles,<br>each piece is chosen
            for its story and the ambiance it creates. Discover
            a range of handcrafted items<br>
            that transform your space into a haven of tranquility and style.</p>
    </div>
    <div class="suggestion-title">RELATED PRODUCTS</div>
    <div class="suggestion-container">
        <div class="suggestion">
            <img src="${randomImages[0]}" alt="추천 상품 1">
        </div>
        <div class="suggestion">
            <img src="${randomImages[1]}" alt="추천 상품 2">
        </div>
        <div class="suggestion">
            <img src="${randomImages[2]}" alt="추천 상품 3">
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

<script src="/assets/js/category.js"></script>
<script>
    function incrementQuantity() {
        const quantityInput = document.getElementById('quantityInput');
        quantityInput.value = parseInt(quantityInput.value) + 1;
        updatePrice();
    }

    function decrementQuantity() {
        const quantityInput = document.getElementById('quantityInput');
        if (parseInt(quantityInput.value) > 1) {
            quantityInput.value = parseInt(quantityInput.value) - 1;
            updatePrice();
        }
    }

    function updatePrice() {
        const quantityInput = document.getElementById('quantityInput');
        const itemPrice = document.getElementById('itemPrice').value;
        const totalPrice = document.getElementById('totalPrice');
        const newPrice = quantityInput.value * itemPrice;
        totalPrice.textContent = '가격: ₩' + newPrice.toFixed(2);
    }
</script>
</body>
</html>
