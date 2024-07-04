<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-profile.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/footer.css"/>

    <!-- <script src="/assets/js/Mypage.js" defer></script> -->
    <script src="/assets/js/Mypage-profile.js" defer></script>
    <script src="/assets/js/category.js/" defer></script>

  </head>
  <body>

    <!-- modalBack -->
    <div class="form-modalback"></div>

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
              <p>프로필사진수정</p>
            </div>

            <div class="right-contents">

              <div class="right-content">
                <p>나만의 이미지로 프로필 사진을 넣어보세요.</p>
              </div>

              <form action="/mypage-profile" method="post" enctype="multipart/form-data" class="profileForm">

                <div class="form-contents">

                  <div class="form-content profile">
                    <h2><i class="fas fa-user"></i> 프로필사진*</h2>
                    <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile">
                    <input type="file" class="profileInput" accept="image/*" style="display: none" name="profile" />
                    <input type="hidden" class="profileInputStatus" value="false" name="profileStatus" >
                  </div>

                  <div class="form-content button">
                    <button class="btn-gradient large yellow upload" type="button">프로필사진 선택</button>
                    <button class="btn-gradient large yellow basic" type="button">기본 이미지 적용</button>
  
                    <button class="btn-gradient large yellow delete" type="button">취소</button>
                    <button class="btn-gradient large yellow check" type="button">적용</button>
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

              <input type="hidden" id="profileSRC" value="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" >

            </div>
            <!-- right-contents -->

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