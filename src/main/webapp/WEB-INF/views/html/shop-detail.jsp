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
                    <input type="number" name="quantity" id="quantityInput" value="1" min="1" step="1">
                    <button type="button" onclick="incrementQuantity()">+</button>
                </div>
                <div class="addBtn">
                    <input type="hidden" name="itemId" value="${item.shopItemId}">
                    <input type="hidden" name="itemPrice" value="${item.shopItemPrice}">
                    <input type="hidden" name="userAccount" value="${userAccount}"> <!-- 추가된 부분 -->
                    <button type="submit">Add to cart</button>
                </div>
            </form>
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
            <img src="" alt="">
        </div>
        <div class="suggestion">
            <img src="" alt="">
        </div>
        <div class="suggestion">
            <img src="" alt="">
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

<script src="/assets/js/category.js"></script>
<script>
    function incrementQuantity() {
        const quantityInput = document.getElementById('quantityInput');
        quantityInput.value = parseInt(quantityInput.value) + 1;
    }

    function decrementQuantity() {
        const quantityInput = document.getElementById('quantityInput');
        if (parseInt(quantityInput.value) > 1) {
            quantityInput.value = parseInt(quantityInput.value) - 1;
        }
    }

    // 성공 및 에러 메시지가 있을 경우 얼럿 창 표시
    window.onload = function() {
        <c:if test="${not empty successMessage}">
        alert("${successMessage}");
        </c:if>
        <c:if test="${not empty errorMessage}">
        alert("${errorMessage}");
        </c:if>
    };
</script>
</body>
</html>
