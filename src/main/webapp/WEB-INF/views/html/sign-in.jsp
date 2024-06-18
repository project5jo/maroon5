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
  </head>
  <body>
    <!-- header  -->
    <header>
      <nav class="menu">
        <div class="inner">
          <div class="logo point">
            <a href="../../main/html/main.html">나의 애착 페이지</a>
          </div>
          <ul class="gnb">
            <li class="login point"><a href="./sign-in.html">LOGIN</a></li>
            <li><i class="fas fa-bars"></i></li>
          </ul>
        </div>
      </nav>
    </header>

    <!-- main -->
    <main>
      <section class="sign-in">
        <div class="inner">
          <div class="sign-in-title">
            <p>로그인</p>
          </div>
          <input type="text" class="email" placeholder="email" />
          <input type="text" class="pw" placeholder="password" />

          <form action="#">
            <!--label태그는 체크박스를 누르지 않아도 연결된 체크박스를 체크해줌-->
            <label for="chk">
              <input type="checkbox" id="chk" />
              <!--실제로는 글자를 기울이는 태그, 퍼블리셔들이 아이콘담을 때 많이 사용-->
              <i class="circle"></i>
              <span class="text">자동 로그인</span>
            </label>
          </form>

          <button class="sign-in-btn"><a href="#">SIGN IN</a></button>
          <button class="kakao-btn">
            <a href="">카카오 로그인 / 회원가입</a>
          </button>
          <button class="sign-up-btn">
            <a href="../../sign-up/html/sign-up.html">회원가입</a>
          </button>
          <div class="find">
            <a class="find-email" href="#">아이디 찾기</a>
            <span>ㅣ</span>
            <a class="find-pw" href="#">비밀번호 찾기</a>
          </div>
        </div>
      </section>
    </main>
    <footer></footer>
  </body>
</html>
