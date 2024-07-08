<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>마이페이지 비밀번호 | 애착 페이지</title>

  <link rel="stylesheet" href="/assets/css/mypage-password.css" />
  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/footer.css"/>

  <script src="/assets/js/mypage-password.js" defer></script>
  <script src="/assets/js/mypage.js" defer></script>
  <script src="/assets/js/category.js/" defer></script>

</head>
<body>

<!-- modalBack -->
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
      <form action="/mypage-password" method="post">

        <div class="middleForm-box">

          <div class="middleForm-content">
            <h2><i class="fas fa-tags"></i> 꼭 읽어주세요.</h2>
          </div>

          <div class="middleForm-content">
            <p>주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요.</p>
            <p>여러 사이트에 동일한 비밀번호를 사용하면 도용되기 쉬우므로 <br>비밀번호를 주기적으로 변경해 주는 것이 안전합니다.</p>
          </div>

          <div class="middleForm-content">
            <h2>사용 불가능한 비밀번호</h2>
            <p>공백이 포함된 경우</p>
            <p>영문/숫자/특수문자 중 2가지 미만 조합인 경우</p>
            <p>비밀번호 글자수가 8자 미만 또는 16자 초과인 경우</p>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 현재 비밀번호</h2>
            <div class="form-indentation">
              <input type="password" name="password" placeholder="현재 비밀번호" class="inputNow" oninput="checkAllMatch()" autocomplete="off">
              <p class="nowSub">현재 사용 중인 비밀번호를 입력해주세요.</p>
            </div>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 새 비밀번호</h2>
            <div class="form-indentation">
              <input type="password" name="newPassword" placeholder="새 비밀번호" class="inputFirst" oninput="checkAllMatch()" autocomplete="off">
              <p class="firstSub">변경하실 비밀번호를 입력해주세요.</p>
            </div>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 새 비밀번호 확인</h2>
            <div class="form-indentation">
              <input type="password" name="checkNewPassword" placeholder="새 비밀번호 확인" class="inputSecond" oninput="checkAllMatch()" autocomplete="off">
              <p class="secondSub">변경하실 비밀번호를 다시 입력해주세요.</p>
            </div>
          </div>

          <div class="middleForm-content">
            <button class="check" type="button" onclick="openModal()">수정하기</button>
          </div>

        </div>

        <!-- modal -->
        <div class="middleModal-box">
          <div class="middleModal-content">

            <span class="modal-close" onclick="closeModal()">&times;</span>
            <p>비밀번호를 변경하시겠습니까?</p>
            <button type="button" onclick="closeModal()">취소</button>
            <button type="submit">확인</button>

          </div>
        </div>
        <!-- modal end -->

      </form>
      <!-- form end -->

    </div>
    <!-- middle-box end -->
  </main>
  <!-- section-middle end -->

  <aside class="section-rightAside">
    <%@ include file="mypage-rightNavigation.jsp" %>
  </aside>

</section>
<!-- section-container end -->

<section class="section-footer">
  <%@ include file="../include/footer.jsp" %>
</section>

</body>
</html>