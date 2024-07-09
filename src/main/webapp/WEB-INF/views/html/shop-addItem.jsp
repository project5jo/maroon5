<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>물품 추가 | 관리자</title>
  <link rel="stylesheet" href="/assets/css/shop-header.css"/>
  <link rel="stylesheet" href="/assets/css/shop-addItem.css"/>
  <link rel="stylesheet" href="/assets/css/footer.css"/>
  <link rel="icon" href="/assets/img/favicon.ico">
</head>
<body>
  <%@ include file="../include/header.jsp" %>
  <div class="container">
    <div class="page-title">Add product</div>
    <div class="main-container">
      <form action="/shop/add" method="post" enctype="multipart/form-data">
        <div class="title-container">
          <p>Title</p>
          <input type="text" name="shop_item_name" required>
        </div>
        <div class="desc-container">
          <p>Description</p>
          <textarea name="shop_item_desc" rows="10" cols="50" required></textarea>
        </div>
        <div class="price-stock-container">
          <div class="price-container">
            <p>Price</p>
            <input type="number" name="shop_item_price" step="0.01" required>
          </div>
          <div class="stock-container">
            <p>Stock</p>
            <input type="number" name="shop_item_stock" required>
          </div>
        </div>
        <div class="upload-file">
          <p>업로드할 사진 파일 경로 :</p>
          <input type="file" id="file" name="shop_item_img" required>
          <label for="file">파일 선택</label>
        </div>
        <button type="submit">UPLOAD</button>
      </form>
    </div>
  </div>
  <%@ include file="../include/footer.jsp" %>
  <script src="/assets/js/category.js"></script>
  <script src="/assets/js/shop-addItem.js"></script>
</body>
</html>
