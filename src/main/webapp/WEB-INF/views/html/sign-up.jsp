<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/sign-up.css">
  </head>
  <body>
    <!-- header  -->
    <header>
      <nav class="menu">
        <div class="inner">
          <div class="logo point">
            <a href="/">나의 애착 페이지</a>
          </div>
          <ul class="gnb">
            <li class="login point"><a href="/login">LOGIN</a></li>
            <li><i class="fas fa-bars"></i></li>
          </ul>
        </div>
      </nav>
    </header>

    <!-- main -->
    <main>
      <section class="sign-in">
        <form action="/create" method="post">
          <div class="inner">
            <div class="sign-in-title">
              <p>회원가입</p>
            </div>
            <div class="tr">
              <p class="th-title">아이디<span></span></p>
              <input
                type="text"
                id="account"
                class="account"
                placeholder="아이디를 입력하세요"
                name="account"
              />
              <p class="th-accountSub"></p>
            </div>
            <div class="tr">
              <p class="th-title">비밀번호<span></span></p>
              <input type="text" class="pw1" placeholder="비밀번호를 입력하세요" name="password"/>
              <p class="th-passwordSub1"></p>
              <p class="th-title margin-top">비밀번호 확인<span></span></p>
              <input type="text" class="pw2" placeholder="비밀번호를 입력하세요" name="repassword"/>
              <p class="th-passwordSub2"></p>
            </div>
            <div class="tr">
              <p class="th-title">이름<span></span></p>
              <input type="text" class="name" placeholder="이름을 입력하세요" name="name"/>
              <p class="th-nameSub"></p>
            </div>
            <div class="tr">
              <p class="th-title">생일<span></span></p>
              <input type="date" class="birth" placeholder="생일을 입력하세요" name="birth"/>
              <p class="th-birthSub"></p>
            </div>
            <div class="tr">
              <p class="th-title">이메일<span></span></p>
              <input
                type="text"
                class="email"
                placeholder="이메일을 입력하세요"
                name="email"
              />
              <p class="th-emailSub"></p>
            </div>
            <div class="btn">
              <button class="cancel"><a href="/login">취소</a></button>
              <button class="sign-up" type="submit"><a href="#">가입하기</a></button>
            </div>
          </div>
        </form>
      </section>
    </main>
    <footer></footer>
    <script src="/assets/js/SignUp.js"></script>
  </body>
</html>
