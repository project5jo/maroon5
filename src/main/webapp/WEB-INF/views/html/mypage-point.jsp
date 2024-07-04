<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-point.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/footer.css"/>

    <script src="/assets/js/Mypage.js" defer></script>
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
          
          <!-- right -->
          <div class="tr-right">

            <!-- title -->
            <div class="right-title">
              <a href="/mypage"><i class="fas fa-chalkboard-teacher"></i></a>
              <p>포인트충전</p>
            </div>

            <div class="right-contents">

              <div class="right-content">
                <h2><i class="fas fa-check"></i>현재 포인트</h2>
                <p id="pointSRC">${isUpdated ? updatedMember.point : nowMember.point}</p>
              </div>

              <!-- <div class="right-content">
                <h2><i class="fas fa-check"></i>충전하실 포인트를 선택해주세요.</h2>
              </div> -->

                <form action="/mypage-point" method="post">

                  <div class="form-contents">

                    <div class="form-content">
                      <h2><i class="fas fa-check"></i>충전하실 포인트를 입력해주세요.</h2>
                      <input type="text" class="inputPoint" name="point" readonly>
                      <button type="button" onclick="removePoint()"><i class="fas fa-times-circle"></i>정정</button>
                    </div>
    
                    <div class="form-content money">
                      <button type="button" onclick="addPoint(this)" value="1000"><i class="fas fa-plus-circle"></i>천원</button>
                      <button type="button" onclick="addPoint(this)" value="5000"><i class="fas fa-plus-circle"></i>오천원</button>
                      <button type="button" onclick="addPoint(this)" value="10000"><i class="fas fa-plus-circle"></i>1만원</button>
                      <button type="button" onclick="addPoint(this)" value="50000"><i class="fas fa-plus-circle"></i>5만원</button>
                      <button type="button" onclick="addPoint(this)" value="100000"><i class="fas fa-plus-circle"></i>10만원</button>
                    </div>

                    <div class="form-content">
                      <h2><i class="fas fa-check"></i>예상 충전 후 포인트</h2>
                      <input type="text" class="expectPoint" readonly>
                    </div>
    
                    <div class="form-content">
                      <h2><i class="fas fa-check"></i>충전안내</h2>
                      <p>포인트 충전시 최소 충전금액은 1,000원이며, 1,000원 단위로 충전이 가능합니다.</p>
                      <p>포인트는 충전취소가 불가능합니다.</p>
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
    <script src="/assets/js/Mypage-point.js"></script>
  </body>
</html>