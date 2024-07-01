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
    <link rel="stylesheet" href="/assets/css/header.css" />
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

        </ul>

        <!-- 메세지 input창 -->
        <div class="my-chat">
          <c:if test="${loginUser == null}">
            <input
              class="my-chat-input"
              placeholder="비로그인은 메세지 작성이 불가능합니다."
              maxlength="200"
            />
          </c:if>
          <c:if test="${loginUser != null}">
            <input
              class="my-chat-input"
              placeholder="${loginUser.nickName}님 메세지 작성"
              maxlength="200"
            />
          </c:if>

          <div class="send">
            <i class="fas fa-paper-plane"></i>
          </div>
        </div>
      </section>
    </main>
    <footer></footer>

    <script>

      const loginUser = "<c:out value='${loginUser.account}' />";
      <%--const loginUserName = "<c:out value='${loginUser.nickName}' />";--%>
      const topicId = 1;
      let roomId = null;
      <%--let roomId = ${roomId};--%>

      let sendere = document.querySelector(".send");
      let chat = document.querySelector(".my-chat-input");


      sendere.addEventListener("click", () => {
        sendMessage();
        document.querySelector(".my-chat-input").value = "";
      });

      chat.addEventListener('keyup', e => {
        if (e.keyCode === 13) {
          sendere.click();
        }
      })




      let currentSubscription = null;
      let stompClient = null;
      let socket = null;

      function connect() {
        socket = new SockJS("/chat-websocket");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
          console.log("Connected: " + frame);
          joinRoom(); // 방 구독 및 참여
        });
      }
      function joinRoom() {
        fetch(`/api/chat/joinRoom`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ topicId: topicId, username: loginUser })
        })
                .then(response => response.json())
                .then(data => {
                  console.log("Joined room:", data);
                  roomId = data.roomId;
                  // if (data.roomId !== roomId) {
                  //   roomId = data.roomId;
                  //   console.log("새로운 방")
                  //   console.log(roomId + "asdasdasd")
                  //   // updateURL(roomId);
                  setTimeout(()=> {
                    subscribeToRoom(roomId); // 새로운 방에 대한 구독 설정
                  }, 2000)
                  // loadMessages(roomId); // 새로운 방의 메시지 로드
                  // } else {
                  //   console.log("기존 방")
                  //   subscribeToRoom(roomId); // 초기 구독 설정
                  // }
                })
                .catch(error => {
                  console.error("Error joining room:", error);
                });
      }

      // subscribeToRoom 함수 수정
      function subscribeToRoom(roomId1) {
        console.log('subscribeToRoom' + roomId1)
        console.log('subscribeToRoom' + roomId)

        if (currentSubscription) {
          // 구독 취소 및 오류 처리 추가
          stompClient.unsubscribe(() => {
            console.log("Successfully unsubscribed from previous room.");
            subscribeNewRoom(roomId1); // 새로운 방 구독
          }, (error) => {
            console.error("Failed to unsubscribe: ", error);
            subscribeNewRoom(roomId1); // 오류 발생 시에도 새로운 방 구독 시도
          });
        } else {
          subscribeNewRoom(roomId1); // 초기 구독 설정
        }
      }

      function subscribeNewRoom(roomId1) {
        console.log('subscribenewRoom' + roomId1)
        console.log('subscribenewRoom' + roomId)
        stompClient.unsubscribe('myTopicId');
        currentSubscription = stompClient.subscribe(`/topic/messages/\${topicId}/\${roomId1}`, { id: 'myTopicId' }, function (message) {
          showMessage(JSON.parse(message.body));
        });
        loadMessages(roomId1); // 새로운 방의 메시지 로드
        console.log("Subscribed to room: " + roomId1);
      }
      function showMessage(message) {
        let messageElement = document.createElement('li');
        let timestamp = new Date(message.timestamp);

        // 포맷팅 옵션 설정
        let options = {
          hour: '2-digit',
          minute: '2-digit',
          hour12: true
        };

        // 포맷팅 변환
        let formattedTime = timestamp.toLocaleString('ko-KR', options);
        if (message.sender === loginUser) {
          messageElement.className = 'my-msg';
          messageElement.innerHTML = `
            <div class="my-msg-writing-time"><p>\${formattedTime}</p></div>
            <div class="msg-text">
              <p>\${message.content}</p>
            </div>
                `;
        } else {
          messageElement.className = "chatting-msg";
          messageElement.innerHTML = `
            <div class="msg-profile"></div>
                <div class="msg-content">
                <div class="msg-nickname"><p>\${message.sender}</p></div>
                 <div class="msg-text">
                   <p>\${message.content}</p>
                  </div>
                </div>
             <div class="msg-writing-time"><p>\${formattedTime}</p></div>
                `;
        }

        let firstMessage = document.querySelector(".chatting");

        firstMessage.appendChild(messageElement);
        firstMessage.scrollTop = firstMessage.scrollHeight;
      }
      function loadMessages(roomId) {
        setTimeout(() => {fetch(`/api/chat/messages?roomId=${roomId}&topicId=${topicId}`)
                .then((response) => response.json())
                .then((messages) => {
                  let chatContainer = document.querySelector(".chatting");
                  chatContainer.innerHTML = ''; // 기존 메시지 삭제
                  messages.forEach((message) => {
                    showMessage(message);
                    console.log('gdgd')
                  });
                  chatContainer.scrollTop = chatContainer.scrollHeight;
                });}, 500)
      }
      function updateURL(newRoomId) {
        const newURL = `${window.location.pathname}?roomId=\${newRoomId}&topicId=${topicId}`;
        console.log("Updating URL to:", newURL); // 디버그용 로그 추가
        history.pushState(null, '', newURL);
        window.location.reload()
      }


      function sendMessage() {
        // 값을 입력하고 버튼을 누르면 /app/sendMessage라는 경로로 서버에 요청을 함
        // let sender = document.querySelector('.send').value;
        let content = document.querySelector(".my-chat-input").value;
        let sender = loginUser;


        // 1. "/app/sendMessage":
        // •	메시지를 보낼 서버의 경로임~
        //     2.	{}:
        // •	서버에 같이 보낼 헤더 정보 > 지금 시간만 보내줌
        //     3.	JSON.stringify({'sender': sender, 'content': content}):
        // •	입력한 메세지 본문을 JSON 으로 변환해서 보내줌
        if (!sender) return;
        if (!content) return;
        stompClient.send(
                `/app/sendMessage/${topicId}/${roomId}`,
          {
            timestamp: new Date().toString(),
          },
                JSON.stringify({ sender: sender, content: content, topicId: topicId, roomId: roomId })
        );
      }



      window.onload = function () {
        // 사이트 진입시 일단은 자동으로 연결
        connect()
        // loadMessages()
        setupInfiniteScroll();
      };

    </script>
    <script defer src="/assets/js/category.js"></script>
    <script defer src="/assets/js/bgChange.js"></script>
  </body>
</html>
