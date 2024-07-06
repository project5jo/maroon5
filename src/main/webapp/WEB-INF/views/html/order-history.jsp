<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>주문 내역</title>
  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/footer.css" />
  <link rel="stylesheet" href="/assets/css/order-history.css" />
  <script src="/assets/js/category.js/" defer></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<main>
  <section class="order-history">
    <div class="inner">
      <div class="order-history-title">
        <p>Orders</p>
      </div>

      <div class="order-title">
        <ul>
          <li>주문 번호</li>
          <li>제품 사진</li>
          <li>이름</li>
          <li>가격</li>
          <li>수량</li>
          <li>총액</li>
          <li>주문 시간</li>
        </ul>
      </div>

      <div class="order-history-list">
        <!-- 날짜별로 주문 내역 그룹화 -->
        <c:forEach var="entry" items="${groupedOrderHistory}">
          <div class="order-time-group">
            <ul>
              <c:forEach var="order" items="${entry.value}">
                <li class="order-item">
                  <span>${order.order_id}</span>
                  <span class="box">
                    <a href="/shop/${order.shop_item_id}">
                      <img src="${order.shop_item_img}" alt="${order.shop_item_name}">
                    </a>
                  </span>
                  <span class="item-title">${order.shop_item_name}</span>
                  <span class="item-price"><fmt:formatNumber value="${order.shop_item_price}" type="number" minFractionDigits="0" maxFractionDigits="0"/></span>
                  <span class="quantity">${order.cart_total_count}</span>
                  <span class="item-total"><fmt:formatNumber value="${order.cart_total_price}" type="number" minFractionDigits="0" maxFractionDigits="0"/></span>
                  <span class="order-time"><fmt:formatDate value="${order.archived_at}" pattern="yyyy-MM-dd HH:mm"/></span>
                </li>
              </c:forEach>
              <span class="order-total">주문 총액 : </span>
              <button class="btn" onclick="goToOrderDetails()">details</button>
            </ul>
          </div>
        </c:forEach>
      </div>
    </div>
    <%@ include file="../include/footer.jsp" %>
  </section>
</main>
<script>
  function goToOrderDetails(orderId) {
    window.location.href = `/order-details?orderId=${orderId}`;
  }
</script>
</body>
</html>
