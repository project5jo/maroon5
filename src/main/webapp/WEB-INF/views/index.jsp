<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/main.css" />
  </head>
  <body>
    <!-- header  -->
    <header>
      <nav class="menu">
        <div class="inner">
          <div class="logo point">나의 애착 페이지</div>
          <ul class="gnb">
            <li class="login point">
              <a href="../../sign-in/html/sign-in.html">LOGIN</a>
            </li>
            <li><i class="fas fa-bars"></i></li>
          </ul>
        </div>
      </nav>
    </header>
    <!-- main -->
    <main>
      <!-- 마우스 애니메이션 -->
      <div class="mouse">
        <div class="wheel"></div>
      </div>

      <!-- 메인 배너 -->
      <section class="main_banner">
        <div class="inner"></div>
      </section>

      <!-- chat -->
      <section class="chat">
        <div class="chat-title">
          <div class="chat-title-content">
            <i class="fas fa-bell"></i>
            <p class="point">고민을 이야기해주세요</p>
          </div>
          <i class="fas fa-times"></i>
        </div>
        <!-- 채팅 메세지 창 -->
        <ul class="chatting">
          <!-- 하나의 채팅 -->
          <li class="chatting-msg">
            <div class="msg-profile"></div>
            <div class="msg-content">
              <div class="msg-nickname"><p>이예진</p></div>
              <div class="msg-text">
                <p>5조 화이팅! 내 고민은 당근 취업이지</p>
              </div>
            </div>
            <div class="msg-writing-time"><p>오전 12:05</p></div>
          </li>
          <!-- 하나의 채팅 -->
          <li class="chatting-msg">
            <div class="msg-profile"></div>
            <div class="msg-content">
              <div class="msg-nickname"><p>이예진</p></div>
              <div class="msg-text">
                <p>5조 화이팅! 내 고민은 당근 취업이지</p>
              </div>
            </div>
            <div class="msg-writing-time"><p>오전 12:05</p></div>
          </li>
          <!-- 내가 쓴 채팅 -->
          <li class="my-msg">
            <div class="my-msg-writing-time"><p>오전 12:05</p></div>
            <div class="msg-text">
              <p>5조 화이팅! 내 고민은 당근 취업이지</p>
            </div>
          </li>
          <!-- 내가 쓴 채팅 -->
          <li class="my-msg">
            <div class="my-msg-writing-time"><p>오전 12:05</p></div>
            <div class="msg-text">
              <p>5조 화이팅! 내 고민은 당근 취업이지</p>
            </div>
          </li>
          <!-- 하나의 채팅 -->
          <li class="chatting-msg">
            <div class="msg-profile"></div>
            <div class="msg-content">
              <div class="msg-nickname"><p>이예진</p></div>
              <div class="msg-text">
                <p>5조 화이팅! 내 고민은 당근 취업이지</p>
              </div>
            </div>
            <div class="msg-writing-time"><p>오전 12:05</p></div>
          </li>
          <!-- 하나의 채팅 -->
          <li class="chatting-msg">
            <div class="msg-profile"></div>
            <div class="msg-content">
              <div class="msg-nickname"><p>이예진</p></div>
              <div class="msg-text">
                <p>5조 화이팅! 내 고민은 당근 취업이지</p>
              </div>
            </div>
            <div class="msg-writing-time"><p>오전 12:05</p></div>
          </li>
          <!-- 하나의 채팅 -->
          <li class="chatting-msg">
            <div class="msg-profile"></div>
            <div class="msg-content">
              <div class="msg-nickname"><p>이예진</p></div>
              <div class="msg-text">
                <p>5조 화이팅! 내 고민은 당근 취업이지</p>
              </div>
            </div>
            <div class="msg-writing-time"><p>오전 12:05</p></div>
          </li>
          <!-- 하나의 채팅 -->
          <li class="chatting-msg">
            <div class="msg-profile"></div>
            <div class="msg-content">
              <div class="msg-nickname"><p>이예진</p></div>
              <div class="msg-text">
                <p>5조 화이팅! 내 고민은 당근 취업이지</p>
              </div>
            </div>
            <div class="msg-writing-time"><p>오전 12:05</p></div>
          </li>
        </ul>

        <!-- 메세지 input창 -->
        <div class="my-chat">
          <input
            class="my-chat-input"
            placeholder="메세지 작성"
            maxlength="200"
          />
          <div class="send">
            <i class="fas fa-paper-plane"></i>
          </div>
        </div>
      </section>
    </main>
    <footer></footer>
  </body>
</html>
