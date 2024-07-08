<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>주문 내역 | 애착 페이지</title>
  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/footer.css" />
  <link rel="icon" href="/assets/img/favicon.ico">
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
              <c:set var="itemList" value="[]" />
              <c:forEach var="order" items="${entry.value.orders}">
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
<%--                <button class="btn" onclick='goToOrderDetails("${order.order_id}", "${entry.value.totalPrice}")'>details</button>--%>
              </c:forEach>
              <li class="order-total">주문 총액 : <fmt:formatNumber value="${entry.value.totalPrice}" type="number" minFractionDigits="0" maxFractionDigits="0"/></li>
              <button class="btn" onclick='goToOrderDetails("${entry.value.orders[0].order_id}", "${entry.value.totalPrice}")'>details</button>
            </ul>
          </div>
        </c:forEach>
      </div>
    </div>
    <%@ include file="../include/footer.jsp" %>
  </section>
</main>
<script>
    function goToOrderDetails(orderId, totalCount) {
      fetch(`/order-details`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ orderId: orderId , totalCount : totalCount})
      })
              .then(response => {
                if (response.redirected) {
                  window.location.href = response.url;
                } else {
                  return response.json();
                }
              })
              .then(data => {
                console.log(data);
                // 주문 상세 페이지로 이동하거나 모달을 표시하는 등의 추가 작업을 여기에 추가합니다.
              })
              .catch(error => {
                console.error('Error:', error);
              });
    }
</script>
</body>
</html>