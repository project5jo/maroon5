<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-main.css" />
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/footer.css"/>

    <!-- <script src="/assets/js/mypage-point.js" defer></script> -->
    <script src="/assets/js/mypage.js" defer></script>
    <script src="/assets/js/category.js/" defer></script>

  </head>
  <body>

    <!-- modalBack -->
    <div class="modalBack"></div>

    <section class="section-header">
      <%@ include file="../include/header.jsp" %>
    </section>

    <header class="section-title">
      <%@ include file="mypage-title.jsp" %>
    </header>

    <section class="section-container">
  
      <nav class="section-leftNav">
        <%@ include file="mypage-leftNavigation.jsp" %>
      </nav>
  
      <main class="section-main">
        <div class="middle-box">
          <div class="memberInfo">

            <div class="profileInfo">

              <div class="profileInfo-img">
                <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile" onclick="openProfile ()">
              </div>
        
              <div class="profileInfo-icon" onclick="openProfile ()">
                <i class="fas fa-user-cog"></i>
              </div>

            </div>

            <div class="nameInfo">
              <div class="suffix">
                <p>${nowMember.account}</p>
                <span>님</span>
              </div>
              <div>
                <p>환영합니다</p>
              </div>

              <div class="goMember-box">
                <div class="goMember">
                  <a  href="/mypage-password"><p>비밀번호 변경</p></a>
                </div>
                <div class="goMember">
                  <a  href="/mypage-memberinfo"><p>회원정보수정</p></a>
                </div>
              </div>

            </div>

            <div class="pointInfo">
              <div class="myPoint">
                <p>내 포인트</p>
              </div>
              <div class="suffix">
                <p>${nowMember.point}</p>
                <span>원</span>
              </div>
              <div class="goPoint">
                <a  href="/mypage-point"><p>포인트 충전하러가기</p></a>
              </div>

            </div>

          </div>
          
          <div class="shoppingInfo">

            <div class="homeInfo">
              <div class="shoppingInfo-img">
                <a  href="/shop"><img src="/assets/img/shop.jpg" alt="shop"></a>
              </div>
              
              <div class="goBtn">
                <a  href="/shop"><p>쇼핑 홈</p></a>
              </div>
            </div>

            <div class="orderInfo">
              <div class="shoppingInfo-img">
                <a  href="/#"><img src="/assets/img/order.jpg" alt="order"></a>
              </div>
              <div class="goBtn">
                <a  href="/#"><p>주문상세</p></a>
              </div>
            </div>

            <div class="cartInfo">
              <div class="shoppingInfo-img">
                <a  href="/#"><img src="/assets/img/cart.jpg" alt="cart"></a>
              </div>
              <div class="goBtn">
                <a  href="/#"><p>장바구니</p></a>
              </div>
            </div>

          </div>

          

            <!-- <div class="middleForm-box">

              <div class="middleForm-profile">

                <div class="middleProfile-content">

                  <div class="middleProfile-img">
                    <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile" onclick="openProfile ()">
                  </div>
            
                  <div class="middleProfile-icon" onclick="openProfile ()">
                    <i class="fas fa-user-cog"></i>
                  </div>

                </div>

                <div class="middleProfile-nameBox">

                  <div class="name-content">
                    <span class="name">키티키티키티키티</span>
                    <span class="gray right">님</span>
                    <div>
                        <span class="gray">안녕하세요.</span>
                    </div>
                  </div>

                  <div class="btn-flex">
                    <div class="name-infoBtn">
                      <p>프로필사진수정</p>
                    </div>
  
                    <div class="name-infoBtn">
                      <p>회원정보수정</p>
                    </div>
                  </div>

                </div>
          
              </div>

              <div class="middleForm-profile">
                <div class="middlePoint-content">

                  <div class="middleProfile-point">
                    <p class="left-point">내 포인트</p>
                    <div class="right-point">
                      <span>1000000000</span>
                      <span class="gray right">원</span>
                    </div>
                  </div>

                  <div class="name-infoBtn">
                    <p>포인트 충전하러가기 ></p>
                  </div>
                  
                </div>
              </div>


              <div class="middleForm-point">
                <div class="middleProfile-content">
                  <div class="middleProfile-img">
                    <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile" onclick="openProfile ()">
                  </div>
                  <p>쇼핑홈</p>
                </div>

                <div class="middleProfile-content">
                  <div class="middleProfile-img">
                    <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile" onclick="openProfile ()">
                  </div>
                  <p>주문내역</p>
                </div>

                <div class="middleProfile-content">
                  <div class="middleProfile-img">
                    <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile" onclick="openProfile ()">
                  </div>
                  <p>장바구니</p>
                </div>

              </div>
        
          
              <div class="profile-infoBtn">
                <a  href="/mypage-memberinfo"><p class="Menu-title">회원정보수정</p></a>
              </div>

              

              <div class="middleForm-content">
                <button class="check" type="button" >충전하기</button>
              </div>

            </div> -->
          
            <!-- modal -->
            <div class="middleModal-box">
              <div class="middleModal-content">

                <span class="modal-close" onclick="closeModal()">&times;</span>
                <p>충전하시겠습니까?</p>
                <button type="button" onclick="closeModal()">취소</button>
                <button type="submit">확인</button>
              
              </div>
            </div>
            <!-- modal end -->


        </div>
        <!-- middle-box end -->
      </main>
      <!-- section-middle end -->
  
      <aside class="section-rightAside">
        <%@ include file="mypage-rightNavigation.jsp" %>
      </aside>

    </section>
    <!-- section-container end -->
    
    <section class="section-footer">
      <%@ include file="../include/footer.jsp" %>
    </section>
    
  </body>
</html>