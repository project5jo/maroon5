<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header  -->
<header>
  <nav class="menu">
    <div class="inner">
      <div class="logo point">나의 애착 페이지</div>
      <ul class="gnb">
        <li class="login point">
          <a href="../../sign-in/html/sign-in.html">LOGIN</a>
        </li>

        <li><i class="fas fa-bars"></i></li>
      </ul>
    </div>
  </nav>
  <!-- 메뉴바 -->
  <div class="category-drawer">
    <button class="cancel_btn">
      <span></span>
      <span></span>
    </button>
    <ul class="category">
      <li class="point"><a href="#chat">Chat</a></li>
      <li class="point"><a href="#shop">Shop</a></li>
      <li class="point"><a href="#mypage">MyPage</a></li>

      <!-- <i class="fas fa-times"></i> -->
    </ul>
  </div>
</header>