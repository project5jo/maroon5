<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <!-- 프리텐다드 웹폰트 -->
    <link
      rel="stylesheet"
      as="style"
      crossorigin
      href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css"
    />

    <link rel="stylesheet" href="/assets/css/modify-pw.css" />
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
  </head>
  <body>
    <!-- header  -->
    <%@ include file="../include/header.jsp" %>

    <!-- main -->
    <main>
      <form action="/modifyPw" method="post">
        <section class="modify-pw">
          <div class="inner">
            <div class="wrapper">
              <div class="modify-pw-title">
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
                type="password"
                class="newPassword"
                name="newPassword"
                placeholder="새 비밀번호 작성"
              />

              <p class="sub-title">새 비밀번호 확인</p>
              <input
                type="password"
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
                <a class="sign-in" href="/sign-in">로그인</a>
                <span>ㅣ</span>
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
