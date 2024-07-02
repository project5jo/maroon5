<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix="c"
                                           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>결제 페이지</title>

  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/payment.css" />
  <script src="/assets/js/category.js/" defer></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<main>
  <section class="payment">
    <form action="/checkout" method="post" enctype="multipart/form-data">
      <div class="inner">
        <div class="payment-title">
          <p>주문결제</p>
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
            <p class="th-title">주문자<span class="icoRequired"></span></p>
            <input
                    type="text"
                    id="account"
                    class="user-name"
                    value="${order.userAccount}"
                    readonly
            />
          </div>
          <div class="tr">
            <p class="th-title">수신자 이름<span></span></p>
            <input
                    type="text"
                    class="receiver-name"
                    placeholder="수신자 이름을 입력하세요"
                    name="receiverName"
            />
          </div>
          <div class="tr">
            <p class="th-title">수신자 전화번호<span></span></p>
            <input
                    type="text"
                    class="receiver-phone"
                    placeholder="수신자 전화번호를 입력하세요"
                    name="receiverPhone"
            />
          </div>
          <div class="tr">
            <p class="th-title">주소<span></span></p>
            <div class="postal-code">
              <input
                      type="text"
                      class="postal-code"
                      placeholder="우편번호를 입력하세요"
                      name="address1"
              />
              <button type="button" class="postal-code-search">주소검색</button>
            </div>
            <input
                    type="text"
                    class="postal-code"
                    placeholder="기본주소를 입력하세요"
                    name="address2"
            />
            <input
                    type="text"
                    class="postal-code"
                    placeholder="상세주소를 입력하세요"
                    name="address3"
            />
          </div>
        </div>

        <div class="user-point">
          <p class="payment-sub-title">포인트<span></span></p>
          <div class="tr">
            <p class="th-title">
              포인트 결제<span class="icoRequired"></span>
            </p>
            <div class="user-point-input">
              <input
                      type="text"
                      id="account"
                      class="user-name"
                      placeholder="사용할 포인트를 입력하세요"
                      name="point"
              />
              <button type="button" class="btn-point">적용</button>
            </div>
            <p class="user-point-info">
              <span>보유하신 포인트</span>: ${userPoint}
            </p>
          </div>
        </div>

        <div class="order-price-info">
          <p class="payment-sub-title">결제 정보<span></span></p>
          <div class="tr">
            <div class="price-info">
              <p class="th-title">주문상품</p>
              <p>${totalPrice}원</p>
            </div>
            <div class="price-info">
              <p class="th-title">포인트 사용</p>
              <p>0원</p>
            </div>
            <div class="price-info">
              <p class="th-title">배송비</p>
              <p>+ 0원</p>
            </div>
            <div class="price-info">
              <p class="th-title">최종 금액</p>
              <p class="total-price">${totalPrice}원</p>
            </div>
          </div>
        </div>

        <button type="submit" class="btn-pay">${totalPrice}원 결제하기</button>
      </div>
    </form>
  </section>
</main>
<%@ include file="../include/footer.jsp" %>
</body>
</html>
