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
        <div class="description">
          <h1>${item.shopItemDesc}</h1>
          <p>- 10ml  <br>
            - 100% pure and natural <br>
            - For external use only <br>
            - Designed in Melbourne</p>
        </div>
        <div class="quantity">
          <button type="button">-</button>
          <input type="number" step="any">
          <button type="button">+</button>
        </div>
        <div class="addBtn">
          <button type="button"  id="cart-btn">Add to cart</button>
        </div>
        <div class="price">
          <p>가격: ₩ ${item.shopItemPrice}</p>
        </div>
        <div class="what">
          <p>Elevate your home with our thoughtfully curated sensory collection. From artisanal incense holders to aromatic candles, each piece is chosen
            for its story and the ambiance it creates. Discover
            a range of handcrafted items
            that transform your space into a haven of tranquility and style.</p>
        </div>
        <div>RELATED PRODUCTS</div>
      </div>
    </div>
    <div class="suggestion">
      <img src="" alt="">
    </div>
    <div class="suggestion">
      <img src="" alt="">
    </div>
    <div class="suggestion">
      <img src="" alt="">
    </div>
    <%@ include file="../include/footer.jsp" %>
  </div>
<!-- 

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
  </div> -->
  <script src="/assets/js/category.js"></script>
</body>
</html>
