<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-password.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/footer.css"/>

    <script src="/assets/js/Mypage.js" defer></script>
    <!-- <script src="/assets/js/Mypage-password.js" defer></script> -->
    <script src="/assets/js/category.js/" defer></script>

  </head>
  <body>

    <!-- modalBack -->
    <div class="modalBack"></div>

    <!-- header  -->
    <%@ include file="../include/header.jsp" %>
  
    <!-- main -->
    <main>

      <section class="mainpage">
        
        <div class="tr">

          <!-- left -->
          <div class="tr-left">

            <div class="profile-container">
              <div class="profile-box">
                <div class="profile">
                  <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile">
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
              <a  href="/mypage-point"><p class="Menu-title">포인트 충전 </p></a>
            </div>
            <div class="left-Menu">
              <a  href="/mypage-orderInfo"><p class="Menu-title">주문내역</p></a>
            </div>
            <div class="left-Menu">
              <a  href="/mypage-profile"><p class="Menu-title">프로필사진 수정 </p></a>
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
              <a href="/sign-out" class="btn-gradient yellow mini">로그아웃<i class="fas fa-user-cog"></i></a>
            </div>

          </div>

          
          <!-- right -->
          <div class="tr-right">

            <!-- title -->
            <div class="right-title">
              <div class="right-titleLogo">
                <a href="/mypage"><i class="fas fa-chalkboard-teacher"></i></a>
              </div>
              <div class="right-titleContent">
                <h1>비밀번호 변경</h1>
              </div>
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

              <form action="/mypage-password" method="post">

                <div class="form-contents">

                  <div class="form-content">
                    <h2>현재 비밀번호 </h2>
                    <input type="text" name="password" placeholder="현재 비밀번호">
                    <p>현재 사용중인 비밀번호를 입력해주세요.</p>
                  </div>

                  <div class="form-content">
                    <h2>새 비밀번호</h2>
                    <input type="text" name="newPassword" placeholder="새 비밀번호">
                    <p>변경하실 비밀번호를 입력해주세요.</p>
                  </div>

                  <div class="form-content">
                    <h2>새 비밀번호 확인</h2>
                    <input type="text" name="checkNewPassword" placeholder="새 비밀번호 확인">
                    <p>새 비밀번호를 다시 한번 입력해주세요.</p>
                  </div>
                  
                  <div class="form-content button">
                    <button class="btn-gradient yellow large check" type="button">확인</button>
                  </div>

                </div>

                <!-- modal -->
                <div class="form-modals">
                  <div class="form-modal">

                    <div class="modal-header">
                      <span>X</span>
                    </div>

                    <div class="modal-content">
                      <p>진짜 수정하시겠습니까?</p>
                    </div>

                    <div class="modal-button">
                      <button class="btn-gradient large yellow cancel" type="button">취소</button>
                      <button class="btn-gradient large yellow recheck" type="submit">확인</button>
                    </div>

                  </div>
                </div>

              </form>
              <!-- form end -->

            </div>
            <!-- right-contents end -->

          </div>
          <!-- right end -->

        </div>
        <!-- tr end -->

      </section>
    </main>

    <!-- footer -->
    <%@ include file="../include/footer.jsp" %>
  
</body>
</html>