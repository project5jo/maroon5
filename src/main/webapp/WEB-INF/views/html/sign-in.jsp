<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/sign-in.css" />
    <link rel="stylesheet" href="/assets/css/shop-header.css" />

    <script src="/assets/js/SignIn.js" defer></script>
  </head>
  <body>
    <!-- header  -->
    <%@ include file="../include/header.jsp" %>

    <!-- main -->
    <main>
      <form action="/sign-in" method="post">
        <section class="sign-in">
          <div class="inner">
            <div class="sign-in-title">
              <p>로그인</p>
            </div>
            <input
              type="text"
              class="email"
              name="account"
              placeholder="email"
            />
            <input
              type="password"
              class="pw"
              name="password"
              placeholder="password"
            />

            <!--label태그는 체크박스를 누르지 않아도 연결된 체크박스를 체크해줌-->
            <label for="chk">
              <input type="checkbox" id="chk" name="autoLogin" />
              <!--실제로는 글자를 기울이는 태그, 퍼블리셔들이 아이콘담을 때 많이 사용-->
              <i class="circle"></i>
              <span class="text">자동 로그인</span>
            </label>

            <!-- 아이디 비번 오류 태그 -->
            <p class="LoginErrorText">${result}</p>

            <button class="sign-in-btn" type="submit">SIGN IN</button>

            <button class="sign-up-btn">
              <a href="/create">회원가입</a>
            </button>
            <div class="find">
              <a class="find-email" href="/findId">아이디 찾기</a>
              <span>ㅣ</span>
              <a class="find-pw" href="/modifyPw">비밀번호 찾기</a>
            </div>
          </div>
        </section>
      </form>
    </main>
    <footer></footer>
  </body>
</html>
