<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>상품 목록</title>
  <link rel="stylesheet" href="/assets/css/shop-index.css">
  <link rel="stylesheet" href="/assets/css/shop-header.css"/>
</head>
<body>
  <%@ include file="../include/shop-header.jsp" %>
  <div class="container">
    <div class="search-container">
      <button class="item-add">Add Item</button>
      <form action="/shop" method="get">
        <input type="text" name="keyword" class="search-bar">
        <button type="submit" class="item-search">Search</button>
      </form>
    </div>
    <div class="main-container">
      
      <ul>
        <c:forEach var="item" items="${items}">
          <li>
            <p>${item.shopItemDesc}</p>
            <div class="img-box">
              <a href="/shop/${item.shopItemId}">
                <img src="${item.shopItemImg}" alt="${item.shopItemName}">
              </a>
            </div>
            <span>${item.shopItemName}</span>
            <button id="cart-btn">₩ ${item.shopItemPrice}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add to cart</button>
          </li>
        </c:forEach>
      </ul>
    </div>
    <%@ include file="../include/footer.jsp" %>
    
  </div>
  <script src="/assets/js/category.js"></script>
</body>
</html>
