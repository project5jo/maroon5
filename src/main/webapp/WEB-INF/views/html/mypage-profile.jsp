<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-profile.css" />
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/footer.css"/>

    <script src="/assets/js/mypage-profile.js" defer></script>
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
          <form action="/mypage-profile" method="post" enctype="multipart/form-data">

            <div class="middleForm-box">

              <div class="middleForm-content">
                <h2><i class="fas fa-camera"></i>원하는 사진으로 자유롭게 프로필사진을 꾸며보세요.</h2>
              </div>

              <div class="middleForm-content">
               <div class="form-profile" onclick="openProfileInput ()">
                  <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile">
                  <input type="file" class="formInput" accept="image/*" style="display: none" name="profile"/>
                  <input type="hidden" class="profileInputStatus" value="false" name="profileStatus">
                </div>
              </div>

              <div class="form-flex">
                <button class="upload" type="button" onclick="openProfileInput()">upload</button>
                <button class="default" type="button">default</button>
                <button class="back" type="button">back</button>
              </div>

              <div class="form-indentation">
                <p><i class="fas fa-lightbulb"></i>upload 버튼을 클릭하면 새로운 프로필 사진을 올릴 수 있습니다.</p>
                <p><i class="fas fa-lightbulb"></i>default 버튼을 클릭하면 프로필사진을 기본사진으로 되돌릴 수 있습니다.</p>
                <p><i class="fas fa-lightbulb"></i>back 버튼을 클릭하면 기존 프로필 사진이 유지됩니다.</p>
              </div>

              <div class="middleForm-content checkCenter">
                <button class="check" type="button" >적용하기</button>
              </div>

            </div>
          
            <!-- modal -->
            <div class="middleModal-box">
              <div class="middleModal-content">

                <span class="modal-close" onclick="closeModal()">&times;</span>
                <p>프로필사진을 변경하시겠습니까?</p>
                <button type="button" onclick="closeModal()">취소</button>
                <button type="submit" onclick="activeCheck ()">확인</button>
              
              </div>
            </div>
            <!-- modal end -->

          </form>
          <!-- form end -->

          <input type="hidden" id="profileSRC" value="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" >

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