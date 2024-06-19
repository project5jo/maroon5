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
    <link rel="stylesheet" href="/assets/css/header.css"/>
  </head>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
       <script src="/assets/js/toggleChat.js/" defer></script>
        <script src="/assets/js/showChatBox.js/" defer></script>
  <body>
    <%@ include file="./include/header.jsp" %>
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

          <button class="cancelBtn">
            <span></span>
            <span></span>
          </button>

          <!-- <i class="fas fa-times"></i> -->
        </div>
        <!-- 채팅 메세지 창 -->
        <ul class="chatting">
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

        let sendere = document.querySelector('.send');
        sendere.addEventListener('click', ()=> {
            sendMessage();
            document.querySelector('.my-chat-input').value = '';



        })
      let stompClient = null;
      function connect() {
        let socket = new SockJS('/chat-websocket'); // 1. 사용자가 서버로 /chat-websocket이란 명령어를 보내서 접속
        stompClient = Stomp.over(socket); //2. 소켓을 사용해서 Stomp 프로토콜에 접속함.
        stompClient.connect({}, function (frame) { // 3. stopmp 를 사용해서 접속에 성공하면 /topic/messages랑 연결
          console.log('Connected: ' + frame);
          stompClient.subscribe('/topic/messages', function (message) { // 4. 3번에서 연결이 끊어지지 않은 상태에서
            // 클라이언트에서 메세지를 보내면 서버에서도 클라이언트에서 값을 보낸 message 값을 json 형식으로 showmessage로 보내줌
            showMessage(JSON.parse(message.body));
          });
        });
      }
      function sendMessage() { // 값을 입력하고 버튼을 누르면 /app/sendMessage라는 경로로 서버에 요청을 함
        // let sender = document.querySelector('.send').value;
        let content = document.querySelector('.my-chat-input').value;
        let sender = 'wlstkdgns';

        // 1. "/app/sendMessage":
        // •	메시지를 보낼 서버의 경로임~
        //     2.	{}:
        // •	서버에 같이 보낼 헤더 정보 > 지금 시간만 보내줌
        //     3.	JSON.stringify({'sender': sender, 'content': content}):
        // •	입력한 메세지 본문을 JSON 으로 변환해서 보내줌
        stompClient.send("/app/sendMessage", {
          timestamp: new Date().toString()
        }, JSON.stringify({'sender': sender, 'content': content}));
      }

      function showMessage(message) {
        console.log('msg: ', message.sender)
        let messageElement = document.createElement('li');
        if (message.sender === 'wlstkdgns1') {
          messageElement.className = 'my-msg';
          messageElement.innerHTML = `
            <div class="my-msg-writing-time"><p>\${message.timestamp}</p></div>
            <div class="msg-text">
              <p>\${message.content}</p>
            </div>
                `
        } else {
          messageElement.className = 'chatting-msg';
          messageElement.innerHTML = `
            <div class="msg-profile"></div>
                <div class="msg-content">
                <div class="msg-nickname"><p>\${message.sender}</p></div>
                 <div class="msg-text">
                   <p>\${message.content}</p>
                  </div>
                </div>
             <div class="msg-writing-time"><p>\${message.timestamp}</p></div>
                `;
        }


        let firstMessage = document.querySelector('.chatting');

        firstMessage.appendChild(messageElement);

      }
        function loadMessages() {
          //비동기로 메세지 목록 로딩 계속 해주는거
          fetch('/messages')
                  .then(response => response.json())
                  .then(messages => {
                    messages.forEach(message => {
                      showMessage(message);
                    });
                  });
        }


        window.onload = function() {
          // 사이트 진입시 일단은 자동으로 연결
          connect();
          loadMessages();
          setupInfiniteScroll();
        }

      connect();
    </script>
    <script defer src="/assets/js/category.js"></script>
    <script defer src="/assets/js/bgChange.js"></script>
  </body>
</html>
