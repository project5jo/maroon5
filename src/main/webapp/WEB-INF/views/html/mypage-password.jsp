<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-password.css">
    <!-- fontawesome css: https://fontawesome.com -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
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
      <section class="mainpage">
        <div class="tr">

          <!-- left -->
          <div class="tr-left">

            <div class="profile-container">
              <div class="profile-box">
                <div class="profile">
                  <img src="/assets/img/profile.jpg" alt="profile">
                </div>
                <div class="profile-icon">
                  <i class="fas fa-user-cog"></i>
                  <!-- <a href="#" class="btn-gradient yellow mini">사진수정<i class="fas fa-user-cog"></i></a> -->
                  <input type="file" class="upload-img" accept="image/*" style="display: none" name="profileImage"/>
                </div>
              </div>
              <p class="profile-name">키티 님</p>
            </div>

            <div class="left-Menu">
              <a href="#"><p class="Menu-title">포인트 충전 </p></a>
            </div>
            <div class="left-Menu">
              <a href="/mypage-memberinfo"><p class="Menu-title">회원정보 수정 </p></a>
            </div>
            <div class="left-Menu">
              <a href="/mypage-password"><p class="Menu-title">비밀번호 수정 </p></a>
            </div>
            <div class="left-Menu">
              <a href="/mypage-cancel"><p class="Menu-title">회원탈퇴 </p></a>
            </div>
            <div class="left-logout">
              <a href="#" class="btn-gradient yellow mini">로그아웃<i class="fas fa-user-cog"></i></a>
            </div>

          </div>

          
          <!-- right -->
          <div class="tr-right">

            <div class="right-title">
              <p>비밀번호 변경</p>
            </div>

            <div class="right-contents">
              <div class="right-content">
                <h2><i class="fas fa-tags"></i>꼭 읽어주세요</h2>
              </div>

              <div class="right-content">
                <h2>주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요.</h2>
                <p>여러 사이트에 동일한 비밀번호를 사용하면 도용되기 쉬우므로 비밀번호를 주기적으로 변경해 주는 것이 안전합니다.</p>
              </div>

              <div class="right-content">
                <h2>사용 불가능한 비밀번호</h2>
                <p>공백이 포함된 경우</p>
                <p>영문/숫자/특수문자 중 2가지 미만 조합인 경우</p>
                <p>비밀번호 글자수가 8자미만 또는 16자 초과인 경우</p>
              </div>

              <form>
                <div class="right-input">
                  <h2>현재 비밀번호 </h2>
                  <input type="text" placeholder="이름">
                  <p>아이디는 5글자 이상 입력해야 합니다.</p>
                </div>
                <div class="right-input">
                  <h2>새 비밀번호</h2>
                  <input type="text" placeholder="이름">
                  <p>아이디는 5글자 이상 입력해야 합니다.</p>
                </div>
                <div class="right-input">
                  <h2>새 비밀번호 확인</h2>
                  <input type="text" placeholder="이름">
                  <p>아이디는 5글자 이상 입력해야 합니다.</p>
                </div>
                <div class="input-check">
                  <button class="btn-gradient yellow large">확인</button>
                </div>
              </form>

            </div>

          </div>

        </div>
      </section>
    </main>

    <footer></footer>
    
    <script src="/assets/js/Mypage.js"></script>
  
</body>
</html>