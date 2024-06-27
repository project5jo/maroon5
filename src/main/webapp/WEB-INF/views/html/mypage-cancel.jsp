<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-main.css">
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
              <p>회원 탈퇴 안내</p>
            </div>

            <div class="right-contents">
              <div class="right-content">
                <p>회원 탈퇴를 신청하기 전에 안내사항을 꼭 확인해주세요.</p>
              </div>
              <div class="right-content">
                <h2><i class="fas fa-check"></i>사용하고 계신 아이디 키티는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</h2>
                <p>탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가하오니 신중하게 선택하시기 바랍니다.</p>
              </div>
              <div class="right-content">
                <h2><i class="fas fa-check"></i>탈퇴 후 회원정보 및 서비스 이용기록은 모두 삭제됩니다.</h2>
                <p>회원정보 및 구매기록 등 서비스 이용기록은 모두 삭제되며 삭제된 데이터는 복구되지 않습니다. 
                  <br>삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.
                </p>
              </div>
              <div class="right-content">
                <table>
                  <tr>
                      <td>회원정보</td>
                      <td>회원가입시 저장된 개인정보 삭제</td>
                  </tr>
                  <tr>
                      <td>구매정보</td>
                      <td>구매기록 삭제</td>
                  </tr>
                </table>
              </div>
              <div class="right-checkContent">
                <p>탈퇴 후에는 아이디 키티로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.</p>
                <input type="checkbox"> 안내사항을 모두 확인하였으며, 이에 동의합니다.
              </div>
            </div>

            <div class="right-content">
              <button class="btn-gradient yellow large">확인</button>
            </div>

          </div>

        </div>
      </section>
    </main>

    <footer></footer>

    <script src="/assets/js/Mypage.js"></script>
    
  </body>
</html>