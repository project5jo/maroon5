<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
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
      <!-- 메뉴바 -->
      <div class="category-drawer">
        <button class="cancel_btn">
          <span></span>
          <span></span>
        </button>
        <ul class="category">
          <li class="point"><a href="#chat">Chat</a></li>
          <li class="point"><a href="#shop">Shop</a></li>
          <li class="point"><a href="#mypage">MyPage</a></li>

          <!-- <i class="fas fa-times"></i> -->
        </ul>
      </div>
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

          <button class="cancel_btn">
            <span></span>
            <span></span>
          </button>

          <!-- <i class="fas fa-times"></i> -->
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
    <script>
      const menuIcon = document.querySelector(".fa-bars");
      const categoryDrawer = document.querySelector(".category-drawer");
      const categoryButton = document.querySelector(
        ".category-drawer > .cancel_btn"
      );

      let timeout;

      menuIcon.addEventListener("click", () => {
        categoryDrawer.classList.add("show");
        menuIcon.style.opacity = "0";
      });

      // x 버튼 이벤트
      categoryButton.addEventListener("click", () => {
        clearTimeout(timeout);
        categoryDrawer.classList.remove("show");
        menuIcon.style.opacity = "1";
      });

      // 현시간에 맞춰 배경화면 변경 (임시로 3초설정 해놈)(테스트)
        let counter = 0;

        function setBackgroundBasedOnCounter() {
        console.log("함수도 작동함");
        let backgroundUrl = '';

        if (counter % 3 === 0) {
            backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
        } else if (counter % 3 === 1) {
            backgroundUrl = 'url(/assets/img/rain.gif)';
        } else {
            backgroundUrl = 'url(/assets/img/firepit.gif)';
        }

        document.querySelector('.main_banner').style.backgroundImage = backgroundUrl;
        counter++;
        console.log("배경바꼇을걸?");
        }

        function init() {
            setBackgroundBasedOnCounter();
            console.log("이벤트작동함");
            // 10초마다 배경을 업데이트
            setInterval(setBackgroundBasedOnCounter, 3000); // 10초마다 실행
        }

        document.addEventListener('DOMContentLoaded', init);

        
        // 현재 유저의 시간에 따라 배경지정하는 함수
        // if (hours >= 6 && hours < 14) {
        //     // 오전 6시부터 오후 2시까지
        //     backgroundUrl = 'url(/assets/img/Cinemagraph.gif)';
        // } else if (hours >= 14 && hours < 22) {
        //     // 오후 2시부터 밤 10시까지
        //     backgroundUrl = 'url(/assets/img/rain.gif)';
        // } else {
        //     // 밤 10시부터 오전 6시까지
        //     backgroundUrl = 'url(/assets/img/firepit.gif)';
        // }

        // document.body.style.backgroundImage = backgroundUrl;
        // }
    </script>
  </body>
</html>
