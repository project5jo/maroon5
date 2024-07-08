<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/sign-up.css" />
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <script src="/assets/js/category.js/" defer></script>
  </head>
  <body>
    <!-- header  -->
    <%@ include file="../include/header.jsp" %>

    <!-- main -->
    <main>
      <section class="sign-up">
        <form action="/create" method="post" enctype="multipart/form-data">
          <div class="inner">
            <div class="sign-up-title">
              <p>회원가입</p>
            </div>

            <!-- 프로필 업로드 모달 -->
            <div class="update-profile-modal modal-close">
              <ul class="update-profile-modal-box">
                <li class="upload-uploadbtn">
                  사진 업로드
                  <input
                    type="file"
                    class="upload-img"
                    accept="image/*"
                    style="display: none"
                    name="profileImage"
                  />
                </li>
                <li class="upload-deletebtn">사진 삭제</li>
                <li class="upload-close-btn">닫기</li>
              </ul>
            </div>

            <!-- 프로필사진 -->
            <div class="tr profile-img">
              <p class="th-title">프로필사진<span></span></p>
              <div class="prfile-wapper">
                <div class="upload-imgbox">
                  <img src="" alt="profile" />
                </div>
                <div class="update-img-modal">
                  <button type="button">사진 업로드</button>
                </div>
                <!-- <div class="upload-imgbtn">
                  <button class="upload-uploadbtn">
                    사진업로드
                    <input
                      type="file"
                      class="upload-img"
                      accept="image/*"
                      style="display: none"
                      name="profileImage"
                    />
                  </button>
                  <button class="upload-deletebtn">취소</button>
                </div> -->
              </div>
            </div>

            <!-- 아이디 입력 -->
            <div class="tr">
              <p class="th-title">아이디<span></span></p>
              <input
                type="text"
                id="account"
                class="account"
                placeholder="아이디를 입력하세요"
                name="account"
                autocomplete="off"
              />
              <p class="th-accountSub"></p>
            </div>

            <!-- 비밀번호 입력 -->
            <div class="tr">
              <p class="th-title">비밀번호<span></span></p>
              <input
                type="password"
                class="pw1"
                placeholder="비밀번호를 입력하세요"
                name="password"
                oninput=" checkAllMatch()"
                autocomplete="off"
              />
              <p class="th-passwordSub1"></p>
              <p class="th-title margin-top">비밀번호 확인<span></span></p>
              <input
                type="password"
                class="pw2"
                placeholder="비밀번호를 입력하세요"
                name="repassword"
                oninput=" checkAllMatch()"
                autocomplete="off"
              />
              <p class="th-passwordSub2"></p>
            </div>

            <!-- 이름 입력 -->
            <div class="tr">
              <p class="th-title">이름<span></span></p>
              <input
                type="text"
                class="name"
                placeholder="이름을 입력하세요"
                name="name"
                autocomplete="off"
              />
              <p class="th-nameSub"></p>
            </div>

            <!-- 생일 입력 -->
            <div class="tr">
              <p class="th-title">생일<span></span></p>
              <input
                type="date"
                class="birth"
                placeholder="생일을 입력하세요"
                name="birth"
                autocomplete="off"
              />
              <p class="th-birthSub"></p>
            </div>
            <div class="tr">
              <p class="th-title">이메일<span></span></p>
              <input
                type="text"
                class="email"
                placeholder="이메일을 입력하세요"
                name="email"
                autocomplete="off"
              />
              <p class="th-emailSub"></p>
            </div>

            <!-- 하단 버튼 -->
            <div class="btn">
              <button class="cancel"><a href="/login">취소</a></button>
              <button class="check" type="submit" disabled>가입하기</button>
            </div>
          </div>
        </form>
      </section>
    </main>

    <footer></footer>
    <script src="/assets/js/SignUp.js"></script>
  </body>
</html>
