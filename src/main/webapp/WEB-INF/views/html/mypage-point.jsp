<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Document</title>

  <link rel="stylesheet" href="/assets/css/mypage-point.css"/>
  <link rel="stylesheet" href="/assets/css/shop-header.css"/>
  <link rel="stylesheet" href="/assets/css/footer.css"/>

  <script src="/assets/js/mypage-point.js" defer></script>
  <script src="/assets/js/mypage.js" defer></script>
  <script src="/assets/js/category.js/" defer></script>
</head>
<body>
<div class="modalBack"></div>

<section class="section-header">
  <%@ include file="../include/header.jsp" %>
</section>

<section class="section-container">
  <nav class="section-leftNav">
    <%@ include file="mypage-leftNavigation.jsp" %>
  </nav>

  <main class="section-main">
    <div class="middle-box">
      <form action="/mypage-point" method="post">
        <div class="middleForm-box">
          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 현재 포인트</h2>
            <p id="pointSRC" class="pointFont">${isUpdated ? updatedMember.point : nowMember.point}</p>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 충전하실 포인트를 선택해주세요.</h2>
            <input type="text" class="formInput pointFont" name="point" value="0" readonly>
            <button type="button" onclick="removePoint()"><i class="fas fa-times-circle"></i>정정</button>

            <div class="form-flex">
              <button type="button" onclick="addPoint(this)" value="1000"><i class="fas fa-plus-circle"></i>천원</button>
              <button type="button" onclick="addPoint(this)" value="5000"><i class="fas fa-plus-circle"></i>오천원</button>
              <button type="button" onclick="addPoint(this)" value="10000"><i class="fas fa-plus-circle"></i>1만원</button>
              <button type="button" onclick="addPoint(this)" value="50000"><i class="fas fa-plus-circle"></i>5만원</button>
              <button type="button" onclick="addPoint(this)" value="100000"><i class="fas fa-plus-circle"></i>10만원</button>
            </div>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 충전 후 예상 포인트</h2>
            <p class="expectPoint pointFont"></p>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 충전안내</h2>
            <p>포인트 충전시 최소 충전금액은 1,000원이며, 1,000원 단위로 충전이 가능합니다.</p>
            <p>포인트는 충전 후 취소할 수 없습니다.</p>
          </div>

          <div class="middleForm-content centerBtn">
            <button class="check" type="button">충전하기</button>
          </div>
        </div>

        <div class="middleModal-box">
          <div class="middleModal-content">
            <span class="modal-close" onclick="closeModal()">&times;</span>
            <p>충전하시겠습니까?</p>
            <button type="button" onclick="closeModal()">취소</button>
            <button type="submit">확인</button>
          </div>
        </div>
      </form>
    </div>
  </main>

  <aside class="section-rightAside">
    <%@ include file="mypage-rightNavigation.jsp" %>
  </aside>
</section>

<section class="section-footer">
  <%@ include file="../include/footer.jsp" %>
</section>
</body>
</html>