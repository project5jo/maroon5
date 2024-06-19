<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>나의 애착 페이지</title>
  <link rel="stylesheet" href="/assets/css/shop-index.css">
  <link rel="stylesheet" href="/assets/css/shop-header.css"/>
</head>
<body>
  <%@ include file="../include/shop-header.jsp" %>
  <div class="container">
    <div class="main-container">
      <ul>
        <li>
          <p>Our curated collection of sensory delights is designed to elevate the atmosphere of any home. Each piece is selected with care, focusing on the story it tells and the mood it evokes.</p>
          <div class="img-box">
            <a href="">
              <img src="" alt="">
            </a>
          </div>
          <span>Onyx Coffee Lab - Monarch 10oz</span>
          <button id="cart-btn">₩ 30000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add to cart</button>
        </li>
        <li>
          <p>A hand-picked range of sensory experiences to transform and uplift the home. Each item within this collection is thoughtfully chosen based on the story it tells and the ambiance it creates. Our selection includes Incausa handmade ceramic incense holders and incense, YIELD glass incense holders and incense and also our favourite Studio Milligram products from their sensory range.</p>
          <div class="img-box">
            <a href="">
              <img src="" alt="">
            </a>
          </div>
          <span>Onyx Coffee Lab - Monarch 10oz</span>
          <button id="cart-btn">₩ 30000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add to cart</button>
        </li>
        <li>
          <p>Immerse yourself in our carefully curated assortment of sensory treasures, crafted to enhance and transform your living space. Each product in this collection is chosen for its unique narrative and the ambiance it creates.</p>
          <div class="img-box">
            <a href="">
              <img src="" alt="">
            </a>
          </div>
          <span>Onyx Coffee Lab - Monarch 10oz</span>
          <button id="cart-btn">₩ 30000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add to cart</button>
        </li>

      </ul>
    </div>
    <%@ include file="../include/footer.jsp" %>
  </div>
  <script src="/assets/js/category.js"></script>
</body>
</html>