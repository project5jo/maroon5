<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>주문 내역</title>
  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/footer.css" />
  <link rel="stylesheet" href="/assets/css/order-history.css" />

</head>
<body>
<%@ include file="../include/header.jsp" %>

<main>
  <section class="order-history">
    <div class="inner">
      <div class="order-history-title">
        <p>주문 내역</p>
      </div>

      <div class="order-history-list">
        <c:forEach var="order" items="${orderHistory}">
    <div class="order-item">
        <p>주문 번호: ${order.order_id}</p>
        <p>상품 이름: ${order.shop_item_name}</p>
        <p>상품 설명: ${order.shop_item_desc}</p>
        <p>상품 가격: ${order.shop_item_price} 원</p>
        <p>수량: ${order.cart_total_count}</p>
        <p>총 가격: ${order.cart_total_price} 원</p>
        <p>주문 일자: ${order.archived_at}</p>
        <img src="${order.shop_item_img}" alt="${order.shop_item_name}">
    </div>
</c:forEach>
      </div>
    </div>
  </section>
</main>
<%@ include file="../include/footer.jsp" %>
</body>
</html>
