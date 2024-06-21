<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Product</title>
  <style>
    .container {
      margin: 0 auto;
      padding: 20px;
      max-width: 600px;
    }
    .page-title {
      font-size: 24px;
      margin-bottom: 20px;
    }
    .main-container {
      border: 1px solid #ddd;
      padding: 20px;
      background-color: #f9f9f9;
    }
    .title-container, .desc-container, .upload-file {
      margin-bottom: 20px;
    }
    .upload-file input[type="file"] {
      display: none;
    }
    .upload-file label {
      display: inline-block;
      padding: 10px 20px;
      background-color: #444;
      color: white;
      cursor: pointer;
      border-radius: 4px;
    }
    button {
      display: block;
      width: 100%;
      padding: 10px;
      background-color: #444;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <%@ include file="../include/shop-header.jsp" %>
  <div class="container">
    <div class="page-title">Add product</div>
    <div class="main-container">
      <form action="/uploadProduct" method="post" enctype="multipart/form-data">
        <div class="title-container">
          <p>Title</p>
          <input type="text" name="title">
        </div>
        <div class="desc-container">
          <p>Description</p>
          <textarea name="description" rows="10" cols="50"></textarea>
        </div>
        <div class="upload-file">
          <p>업로드할 파일 경로 :</p>
          <input type="file" id="file" name="file">
          <label for="file">파일 선택</label>
        </div>
        <button type="submit">UPLOAD</button>
      </form>
    </div>
  </div>
  <%@ include file="../include/footer.jsp" %>
</body>
</html>
