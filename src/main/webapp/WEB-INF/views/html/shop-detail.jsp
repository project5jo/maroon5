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
<main>
    <div class="container">
        <div class="main-container">
            <div class="inner">
                <div class="item-detail-info">
                    <div class="img-box">
                        <img src="${item.shopItemImg}" alt="-">
                    </div>
                    <div class="details">
                        <div class="wrapper">
                            <div class="description">
                                <p class="item-desc">${item.shopItemDesc}</p>
                                <p class="item-name point">${item.shopItemName}</p>
                                <p class="item-price point">
                                    ${item.shopItemPrice}원
                                </p>
                            </div>
                            <form id="addToCartForm" action="/cart" method="post" onsubmit="return handleFormSubmit(event)">
                                <div class="quantity">
                                    <button class="minus-btn" type="button" onclick="decrementQuantity()">-</button>
                                    <input type="number" name="quantity" id="quantityInput" value="1" min="1" step="1" oninput="updatePrice()">
                                    <button class="plus-btn" type="button" onclick="incrementQuantity()">+</button>
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
                                    <div class="price" id="totalPrice">${item.shopItemPrice}원</div>
                                    <button class="add-cart-btn " type="submit">ADD TO CART</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="what">
            <div class="inner">
                <div class="what-wrapper">
                    <div class="gift-box">
                        <div class="box-icon">
                            <img src="https://www.aesop.com/u1nb1km7t5q7/7AI4tKMotpiot46NoMsbRV/ea55cce91cb42d7a8b88d53b9288451f/giftbox.svg" alt="">
                        </div>
                        <div class="box-title point">무료 선물 포장</div>
                        <div class="box-text">무료 선물 포장 서비스가 제공됩니다. <br/>더불어 기프트 메시지가 담긴 카드를 함께 보내드립니다.</div>
                    </div>
                    <div class="sample-box">
                        <div class="box-icon">
                            <img src="https://www.aesop.com/u1nb1km7t5q7/3ZHAFYCvLBqAKjXfINVH1q/acff0cee6551b41d62d3a75c1e9c2780/droplet.svg" alt="">
                        </div>
                        <div class="box-title point">무료 샘플 선물</div>
                        <div class="box-text">주문 건에 함께 제공되는 <br/>무료 샘플을 통해 다양한 제품을 만나보세요.</div>
                    </div>
                    <div class="delivery-box">
                        <div class="box-icon">
                            <img src="https://www.aesop.com/u1nb1km7t5q7/6KfZEK5JO8mC3MByJryaYT/7e3c6cd5459718af152fbbd075b36a98/package.svg" alt="">
                        </div>
                        <div class="box-title point">무료 선물 포장</div>
                        <div class="box-text">무료 선물 포장 서비스가 제공됩니다. <br/>더불어 기프트 메시지가 담긴 카드를 함께 보내드립니다.</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="suggestion-container">
            <div class="inner">
                <div class="suggestion-wrapper">
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
            </div>
        </div>
        <div id="cartModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <p id="modalMessage">장바구니에 추가되었습니다.</p>
                <button id="modalButton" onclick="goToCart()">Go to Cart</button>
                <button onclick="goBack()">Back</button>
            </div>
        </div>
    </div>
</main>
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
        totalPrice.textContent = newPrice +"원";
    }
    function handleFormSubmit(event) {
        event.preventDefault();
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
        return false;
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
