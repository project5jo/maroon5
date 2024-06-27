<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/modify-pw.css" />
  </head>
  <body>
    <!-- header  -->
    <header>
      <nav class="menu">
        <div class="inner">
          <div class="wrapper">
            <div class="logo point">
              <a href="#">나의 애착 페이지</a>
            </div>
            <ul class="gnb">
              <li class="login point"><a href="#">LOGIN</a></li>
              <li><i class="fas fa-bars"></i></li>
            </ul>
          </div>
        </div>
      </nav>
    </header>

    <!-- main -->
    <main>
      <form action="/modifyPw" method="post">
        <section class="modifyPw">
          <div class="inner">
            <div class="wrapper">
              <div class="sign-in-title">
                <p>비밀번호 변경</p>
              </div>

              <p class="sub-title">아이디</p>
              <input
                type="text"
                class="account"
                name="account"
                placeholder="account"
              />

              <p class="sub-title">새 비밀번호</p>
              <input
                type="text"
                class="newPassword"
                name="newPassword"
                placeholder="새 비밀번호 작성"
              />

              <p class="sub-title">새 비밀번호 확인</p>
              <input
                type="text"
                class="newPasswordCk"
                name="newPasswordCk"
                placeholder="새 비밀번호 확인"
              />

              <c:if test="${result == 'null'}">
                <p class="result">
                  존재하지 않는 회원입니다. 입력하신 내용을 다시 확인해주세요.
                </p>
              </c:if>

              <button class="modify-pw-btn" type="submit">확인</button>

              <div class="find">
                <a class="find-id" href="/findId">아이디 찾기</a>
              </div>
            </div>
          </div>
        </section>
      </form>
    </main>
    <footer></footer>
  </body>
</html>
