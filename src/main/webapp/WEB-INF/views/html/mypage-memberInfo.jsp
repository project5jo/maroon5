<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>유저 정보 수정</title>

  <link rel="stylesheet" href="/assets/css/mypage-memberInfo.css" />
  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/footer.css"/>

  <script src="/assets/js/mypage-memberInfo.js" defer></script>
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
      <form action="/mypage-memberinfo" method="post">

        <div class="middleForm-box">

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 아이디 </h2>
            <div class="form-indentation">
              <h2>${isUpdated ? updatedMember.account : nowMember.account}</h2>
              <p>아이디는 변경할 수 없습니다.</p>
            </div>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 이름</h2>
              <input type="text" name="name" value="${isUpdated ? updatedMember.name : nowMember.name}" class="inputName" autocomplete="off">
            <div class="form-indentation">
              <p class="nameSub"></p>
            </div>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 생년월일</h2>
              <input type="date" name="birth" value="${isUpdated ? updatedMember.birth : nowMember.birth}" class="inputBirth" autocomplete="off">
            <div class="form-indentation">
              <p class="birthSub"></p>
            </div>
          </div>

          <div class="middleForm-content">
            <h2><i class="fas fa-check"></i> 이메일</h2>
              <input type="text" name="email" value="${isUpdated ? updatedMember.email : nowMember.email}" class="inputEmail" autocomplete="off">
            <div class="form-indentation">
              <p class="emailSub"></p>
            </div>
          </div>

          <div class="middleForm-content">
            <button class="check" type="button" >수정하기</button>
          </div>

        </div>

        <!-- modal -->
        <div class="middleModal-box">
          <div class="middleModal-content">

            <span class="modal-close" onclick="closeModal()">&times;</span>
            <p>회원정보를 수정하시겠습니까?</p>
            <button type="button" onclick="closeModal()">취소</button>
            <button type="submit">확인</button>

          </div>
        </div>
        <!-- modal end -->

      </form>
      <!-- form end -->

      <input type="hidden" id="nameSRC" value="${isUpdated ? updatedMember.name : nowMember.name}">
      <input type="hidden" id="birthSRC" value="${isUpdated ? updatedMember.birth : nowMember.birth}">
      <input type="hidden" id="emailSRC" value="${isUpdated ? updatedMember.email : nowMember.email}">

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