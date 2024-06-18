<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="assets/css/sign-up.css" />
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
            <p>회원가입</p>
          </div>
          <div class="tr">
            <p class="th-title">아이디<span></span></p>
            <input
              type="text"
              class="email"
              placeholder="아이디를 입력하세요"
            />
            <p class="th-sub">영문 소문자 / 숫자, 4~16자로 입력해 주세요.</p>
          </div>
          <div class="tr">
            <p class="th-title">비밀번호<span></span></p>
            <input type="text" class="pw" placeholder="비밀번호를 입력하세요" />
            <p class="th-sub">
              (영문 대소문자 / 숫자 / 특수문자 중 3가지 이상 조합, 8자~16자)
            </p>
            <p class="th-title margin-top">비밀번호 확인<span></span></p>
            <input type="text" class="pw" placeholder="비밀번호를 입력하세요" />
          </div>
          <div class="tr">
            <p class="th-title">이름<span></span></p>
            <input type="text" class="email" placeholder="이름을 입력하세요" />
          </div>
          <div class="tr">
            <p class="th-title">휴대전화<span></span></p>
            <input type="text" class="email" placeholder="이름을 입력하세요" />
          </div>
          <div class="tr">
            <p class="th-title">이메일<span></span></p>
            <input
              type="text"
              class="email"
              placeholder="이메일을 입력하세요"
            />
          </div>
          <div class="btn">
            <button class="cancel"><a href="#">취소</a></button>
            <button class="sign-up"><a href="#">가입하기</a></button>
          </div>
        </div>
      </section>
    </main>
    <footer></footer>
  </body>
</html>
