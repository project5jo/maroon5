<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix="c"
                                           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>결제 확인</title>

  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/order-detail.css" />
  <link rel="stylesheet" href="/assets/css/footer.css"/>
  <script src="/assets/js/category.js/" defer></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<main>
  <section class="payment">
    <form class="form-pay" action="/checkout" method="post" enctype="multipart/form-data">
      <div class="inner">
        <div class="payment-title">
          <p>주문 결제 확인</p>
        </div>

        <div class="payment-item">
          <p class="payment-sub-title">주문상품<span></span></p>
          <ul class="prd-box-list">
            <c:forEach var="cartItem" items="${cartItems}">
              <li class="prd-box">
                <div class="prd-item"><div class="item-img">
                  <img src="${cartItem.shopItemImg}" alt="${cartItem.shopItemName}">
                </div></div>
                <div class="prd-item-info">
                  <p class="prdName">${cartItem.shopItemName}</p>
                  <p>수량:${cartItem.cartTotalCount}개</p>
                  <p class="prdPrice"><span>${cartItem.cartTotalPrice}</span>원</p>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>

        <div class="payment-Delivery">
          <p class="payment-sub-title">배송지<span></span></p>
          <div class="tr">
            <p class="th-title">받는분<span class="icoRequired"></span></p>
            <input
                    type="text"
                    class="receiver-name"
                    value="${loginUser.nickName}"
                    name="receiverName"
                    
            />
          </div>
        
          <div class="tr">
            <p class="th-title">받는분 휴대번호<span></span></p>
            <input
                    type="text"
                    class="receiver-phone"
                    placeholder="받으실분 전화번호를 입력하세요"
                    name="receiverPhone"
            />
          </div>
          <div class="tr">
            <p class="th-title">주소<span></span></p>
            <div class="postal-code">
              <input type="text" id="sample6_postcode" placeholder="우편번호" name="address1">
            </div>
            <input type="text" id="sample6_address" placeholder="주소" name="address2">
            <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address3">
            <input class="sample6-extraAddress" type="text" id="sample6_extraAddress" placeholder="참고항목" readonly>
          </div>
        </div>

        <div class="order-price-info">
          <p class="payment-sub-title">결제 정보<span></span></p>
          <div class="tr">
            <div class="price-info">
              <p class="th-title">최종 결제 금액</p>
              <p class="total-price">${totalOrderPrice}원</p>
            </div>
          </div>
        </div>

        <div class="treatment-btn">
          <button class="refund" onClick="goToHistory()">반품</button>
          <button class="replace" onClick="goToHistory()">교환</button>
        </div>
        <button class="back" onClick="goToHistory()">Back</button>
      </div>
    </form>
  </section>
</main>
<%@ include file="../include/footer.jsp" %>
<script>
  function goToHistory() {
    window.location.href = "/complete";
  }
</script>
</body>
</html>
