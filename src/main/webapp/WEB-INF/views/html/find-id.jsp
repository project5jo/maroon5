<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/find-id.css" />
  </head>
  <body>
    <!-- header  -->
    <%@ include file="../include/header.jsp" %>

    <!-- main -->
    <main>
      <form action="/findId" method="post">
        <section class="find-id">
          <div class="inner">
            <div class="wrapper">
              <div class="find-id-title">
                <p>아이디 찾기</p>
              </div>

              <c:if test="${user != null}">
                <div class="foundUserId">
                  <p>
                    ${user.userName}님의 아이디는 <br />
                    <span>${user.userAccount}</span> 입니다
                  </p>
                </div>

                <div class="btn">
                  <button class="sign-in"><a href="/sign-in">로그인</a></button>
                  <button class="find-pw">
                    <a href="/modifyPw">비밀번호 찾기</a>
                  </button>
                </div>
              </c:if>

              <c:if test="${user == null}">
                <p class="sub-title">이름</p>
                <input
                  type="text"
                  class="name"
                  name="name"
                  placeholder="name"
                />

                <p class="sub-title">이메일</p>
                <input
                  type="text"
                  class="email"
                  name="email"
                  placeholder="email"
                />
                <c:if test="${result == 'null'}">
                  <p class="result">
                    존재하지 않는 회원입니다. 입력하신 내용을 다시 확인해주세요.
                  </p>
                </c:if>
                <button class="find-id-btn" type="submit">확인</button>

                <div class="find">
                  <a class="find-pw" href="/modifyPw">비밀번호 찾기</a>
                </div>
              </c:if>
            </div>
          </div>
        </section>
      </form>
    </main>
    <footer></footer>
  </body>
</html>
