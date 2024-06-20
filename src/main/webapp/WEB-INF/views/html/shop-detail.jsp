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
  <%@ include file="../include/shop-header.jsp" %>
  <div class="container">
    <div class="main-container">
      <div class="img-box">
        <img src="${item.shopItemImg}" alt="${item.shopItemName}">
      </div>
      <div class="details">
        <h1>${item.shopItemName}</h1>
        <p>${item.shopItemDesc}</p>
        <p>가격: ₩ ${item.shopItemPrice}</p>
        <p>재고: ${item.shopItemStock}</p>
        <p>조회수: ${item.shopItemView}</p>
        <button id="cart-btn">Add to cart</button>
      </div>
    </div>
    <%@ include file="../include/footer.jsp" %>
  </div>
  <script src="/assets/js/category.js"></script>
</body>
</html>
