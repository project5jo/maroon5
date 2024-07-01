<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header  -->
</head>
<body>
  <header>
    <nav class="menu">
      <div class="inner">
        <div class="logo point" ><a href="/">나의 애착 페이지</a></div>
        <ul class="gnb">
          <li class="login point">
            <c:if test="${loginUser == null}">
            <a href="/sign-in">LOGIN</a>
            </c:if>
            <c:if test="${loginUser != null}">
            <a class="point" href="/sign-out">LOGOUT</a>
          </c:if>
          </li>
          <li class="menu-icon">
            <div></div>
            <div></div>
            <div></div>
          </li>

          <!-- <i class="fas fa-bars"></i> -->
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
        <li class="point"><a href="/shop">Shop</a></li>
        <li class="point"><a href="/mypage">MyPage</a></li>

        <!-- <i class="fas fa-times"></i> -->
      </ul>
    </div>
  </header>