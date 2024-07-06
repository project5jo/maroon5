<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 상세 정보</title>
    <link rel="stylesheet" href="/assets/css/shop-detail.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
    <link rel="stylesheet" href="/assets/css/footer.css"/>
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
            <form id="addToCartForm" action="/cart" method="post" onsubmit="return handleFormSubmit(event)">
                <div class="quantity">
                    <button type="button" onclick="decrementQuantity()">-</button>
                    <input type="number" name="quantity" id="quantityInput" value="1" min="1" step="1" oninput="updatePrice()">
                    <button type="button" onclick="incrementQuantity()">+</button>
                </div>
                <div class="addBtn">
                    <input type="hidden" name="itemId" value="${item.shopItemId}">
                    <input type="hidden" name="itemPrice" id="itemPrice" value="${item.shopItemPrice}">
                    <c:choose>
                        <c:when test="${sessionScope.loginUser != null}">
                            <input type="hidden" name="userAccount" value="${sessionScope.loginUser.account}">
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="userAccount" value="">
                        </c:otherwise>
                    </c:choose>
                    <button type="submit">Add to cart</button>
                </div>
            </form>
            <div class="price" id="totalPrice">₩ ${item.shopItemPrice}</div>
        </div>
    </div>
    <div class="what">
        <p>Elevate your home with our thoughtfully curated sensory collection. From artisanal incense holders to aromatic candles,<br>each piece is chosen
            for its story and the ambiance it creates. Discover
            a range of handcrafted items<br>
            that transform your space into a haven of tranquility and style.</p>
    </div>
    <div class="suggestion-container">
        <div class="suggestion">
            <a href="/shop/${randomImages[0].shopItemId}">
                <img src="${randomImages[0].shopItemImg}" alt="추천 상품 1">
            </a>
        </div>
        <div class="suggestion">
            <a href="/shop/${randomImages[1].shopItemId}">
                <img src="${randomImages[1].shopItemImg}" alt="추천 상품 2">
            </a>
        </div>
        <div class="suggestion">
            <a href="/shop/${randomImages[2].shopItemId}">
                <img src="${randomImages[2].shopItemImg}" alt="추천 상품 3">
            </a>
        </div>
    </div>
    <!-- 모달 HTML 코드 -->
    <div id="cartModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <p id="modalMessage">장바구니에 추가되었습니다.</p>
            <button id="modalButton" onclick="goToCart()">Go to Cart</button>
            <button onclick="goBack()">Back</button>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>

<script src="/assets/js/category.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
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
        totalPrice.textContent = '₩' + newPrice;
    }

    function handleFormSubmit(event) {
        event.preventDefault(); // 폼 제출 막기

        const userAccount = document.querySelector(`#addToCartForm input[name="userAccount"]`).value;

        if (!userAccount) {
            showFailModal();
            return false;
        }

        const form = document.getElementById('addToCartForm');
        const formData = new FormData(form);

        fetch('/cart', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    showModal();
                } else {
                    alert('장바구니에 추가하는 데 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('장바구니에 추가하는 도중 오류가 발생했습니다.');
            });

        return false; // 폼 제출 막기
    }

    function showFailModal() {
        document.getElementById('modalMessage').textContent = "로그인 후 이용해주세요.";
        const modalButton = document.getElementById('modalButton');
        modalButton.textContent = "Login";
        modalButton.setAttribute("onclick", "goToSignIn()");
        document.getElementById("cartModal").style.display = "block";
    }

    function goToSignIn() {
        window.location.href = "/sign-in";
    }

    function showModal() {
        document.getElementById("cartModal").style.display = "block";
    }

    function closeModal() {
        document.getElementById("cartModal").style.display = "none";
    }

    function goToCart() {
        window.location.href = "/cart";
    }

    function goBack() {
        window.location.href = "/shop";
    }
</script>
</body>
</html>
