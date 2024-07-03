<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-memberinfo.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css" />

    <script src="/assets/js/Mypage.js" defer></script>
    <script src="/assets/js/Mypage-memberInfo.js" defer></script>
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
                  <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile.jpg'}" alt="profile">
                </div>
                <div class="profile-icon">
                  <i class="fas fa-user-cog"></i>
                  <!-- <a href="#" class="btn-gradient yellow mini">사진수정<i class="fas fa-user-cog"></i></a> -->
                  <input type="file" class="upload-img" accept="image/*" style="display: none" name="profileImage"/>
                </div>
              </div>
              <p class="profile-name">${isUpdated ? updatedMember.name : nowMember.name} 님</p>
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
          <!-- left end -->

          <!-- right -->
          <div class="tr-right">

            <!-- title -->
            <div class="right-title">
              <a href="/mypage"><i class="fas fa-chalkboard-teacher"></i></a>
              <p>회원정보수정</p>
            </div>

            <div class="right-contents">

              <div class="right-content">
                <p>소중한 개인정보를 최신으로 관리하세요.</p>
              </div>

              <form action="/mypage-memberinfo" method="post">

                <div class="form-contents">

                  <div class="form-content">
                    <h2><i class="fas fa-user"></i> 아이디* </h2>
                    <input type="text" name="account" value="${isUpdated ? updatedMember.account : nowMember.account}">
                    <p>"아이디는 변경할 수 없습니다."</p>
                  </div>
                
                  <div class="form-content name">
                    <h2><i class="far fa-address-card"></i> 이름*</h2>
                    <input type="text" name="name" value="${isUpdated ? updatedMember.name : nowMember.name}">
                    <p>"이름은 한글만 입력할 수 있습니다. (2자 이상)"</p>
                  </div>
                
                  <div class="form-content birth">
                    <h2><i class="fas fa-birthday-cake"></i> 생년월일*</h2>
                    <input type="date" name="birth" value="${isUpdated ? updatedMember.birth : nowMember.birth}">
                    <p></p>
                  </div>
                
                  <div class="form-content email">
                    <h2><i class="far fa-envelope"></i> 이메일*</h2>
                    <input type="text" name="email" value="${isUpdated ? updatedMember.email : nowMember.email}">
                    <p></p>
                  </div>
                
                  <div class="form-content button">
                    <button class="btn-gradient large yellow check" type="button">확인</button>
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

    <footer></footer>
  
</body>
</html>