<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/header.css"/>
    <link rel="stylesheet" href="/assets/css/main.css"/>
    <link rel="stylesheet" href="/assets/css/bgChange.css">
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="/assets/js/toggleChat.js/" defer></script>
<script src="/assets/js/showChatBox.js/" defer></script>
<script src="/assets/js/category.js/" defer></script>
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
        <video id="backgroundVideo" autoplay muted loop playsinline style="width: 100%; height: 100%; object-fit: cover;">
            <source id="videoSource" src="" type="video/mp4">
            Your browser does not support HTML5 video.
        </video>
        <div class="inner"></div>
    </section>

    <!-- chat -->
    <section class="chat">
        <div class="chat-title">
            <div class="chat-title-content">
                <i class="fas fa-bell"></i>
                <p class="point">${topicContent}</p>
                <a href="/zzz">LOGIN</a>
            </div>

            <button class="cancelBtn">
                <span></span>
                <span></span>
            </button>
        </div>
        <!-- 채팅 메세지 창 -->
        <ul class="chatting"></ul>

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
                <div class="chat-profile">
                    <img src="${loginUser.profileUrl != null ? loginUser.profileUrl : '/assets/img/profile3.jpg'}"
                         alt="프로필 사진"/>
                </div>
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
    let topicId = null;
    let roomId = null;
    let currentSubscription = null;
    let delay = true;

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
    function chatHide() {
        const chatBox = document.querySelector('.chat');
        chatBox.classList.add('slide-down');
    }

    // WebSocket을 사용하여 topicId 변경 알림을 받기 위한 설정 (수정)
    function setupTopicIdListener() {
        const socket = new SockJS('/chat-websocket'); // (수정)
        const stompClient = Stomp.over(socket); // (수정)
        stompClient.connect({}, function () { // (수정)
            stompClient.subscribe('/topic/currentTopic', function (message) { // (수정)
                const data = JSON.parse(message.body); // (수정)
                console.log("asdasd" + data.topicId)
                console.log("topic Id " + topicId)
                topicId = data.topicId; // (수정)
                console.log("Updated Topic ID:", topicId); // (수정)
                // 새로운 topicId에 대한 구독 및 채팅방 재설정 (수정)
                joinRoom(); // (수정)ㄱ

            }); // (수정)
        }); // (수정)
    }

    // 초기화 함수 (수정)
    function initialize() {
        fetch('/api/admin/currentTopic') // (수정)
            .then(response => response.json()) // (수정)
            .then(data => { // (수정)
                topicId = data.topicId; // (수정)
                console.log("Current Topic ID:", topicId); // (수정)
                connect(); // (수정)
                setupTopicIdListener(); // topicId 변경 리스너 설정 (수정)
            }) // (수정)
            .catch(error => console.error("Error fetching current topic:", error)); // (수정)
    }

    function connect() {
        const socket = new SockJS("/chat-websocket"); // (수정)
        stompClient = Stomp.over(socket); // (수정)
        stompClient.connect({}, function (frame) { // (수정)
            console.log("Connected: " + frame); // (수정)
            joinRoom(); // 방 구독 및 참여 (수정)
        }); // (수정)
    }

    function joinRoom() {
        if (!topicId || !loginUser) return; // (수정)
        fetch(`/api/chat/joinRoom`, { // (수정)
            method: 'POST', // (수정)
            headers: { // (수정)
                'Content-Type': 'application/json' // (수정)
            }, // (수정)
            body: JSON.stringify({topicId: topicId, username: loginUser}) // (수정)
        }) // (수정)
            .then(response => response.json()) // (수정)
            .then(data => { // (수정)
                console.log("Joined room:", data); // (수정)
                roomId = data.roomId; // (수정)
                document.querySelector('.chat-title .point').textContent = data.topicContent;
                setTimeout(() => { // (수정)
                    subscribeToRoom(roomId); // 새로운 방에 대한 구독 설정 (수정)
                }, 2000); // (수정)
            }) // (수정)
            .catch(error => { // (수정)
                console.error("Error joining room:", error); // (수정)
            }); // (수정)
    }

    function subscribeToRoom(roomId1) {
        console.log('subscribeToRoom' + roomId1);

        if (currentSubscription) {
            currentSubscription.unsubscribe();
            console.log("Successfully unsubscribed from previous room.");
        }

        currentSubscription = stompClient.subscribe(`/topic/messages/\${topicId}/\${roomId1}`, function (message) {
            console.log("Received message:", message.body);
            showMessage(JSON.parse(message.body));
        });

        loadMessages(roomId1);
        console.log("Subscribed to room: " + roomId1);
    }


    function subscribeNewRoom(roomId1) { // (수정)
        console.log('subscribenewRoom' + roomId1); // (수정)
        console.log('subscribenewRoom' + roomId); // (수정)
        currentSubscription = stompClient.subscribe(`/topic/messages/\${topicId}/\${roomId1}`, function (message) { // (수정)
            showMessage(JSON.parse(message.body)); // (수정)
        }); // (수정)
        loadMessages(roomId1); // 새로운 방의 메시지 로드 (수정)
        console.log("Subscribed to room: " + roomId1); // (수정)
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
        let profile = null;

        if (message.profileUrl) {
            profile = message.profileUrl
        } else {
            profile = `/assets/img/profile3.jpg`
        }

        console.log(profile)

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
            <div class="msg-profile">
                 <img src="\${profile}" alt="프로필 사진" />
            </div>
                <div class="msg-content">
                <div class="msg-nickname"><p>\${message.senderName}</p></div>
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

        fetch(`/api/chat/messages?roomId=\${roomId}&topicId=\${topicId}`)
            .then((response) => response.json())
            .then((messages) => {
                let chatContainer = document.querySelector(".chatting");
                chatContainer.innerHTML = ''; // 기존 메시지 삭제
                setTimeout(() => {
                    messages.forEach((message) => {
                        showMessage(message);
                        chatContainer.scrollTop = chatContainer.scrollHeight;
                    });
                }, 500)

                chatContainer.scrollTop = chatContainer.scrollHeight;
            });
    }

    function sendMessage() {
        // 값을 입력하고 버튼을 누르면 /app/sendMessage라는 경로로 서버에 요청을 함
        let content = document.querySelector(".my-chat-input").value;
        let sender = loginUser;

        if (!sender) return;
        if (!content) return;
        if (!delay) return;
        stompClient.send(
            `/app/sendMessage/\${topicId}/\${roomId}`,
            {
                timestamp: new Date().toString(),
            },
            JSON.stringify({sender: sender, content: content, topicId: topicId, roomId: roomId})
        );
        delay = false;
        setTimeout(() => {
            delay = true;
        }, 500);
    }

    window.onload = function () {
        // 사이트 진입시 일단은 자동으로 연결
        chatHide()
        initialize(); // 초기화 함수 호출 (수정)
        setupInfiniteScroll();
    };
</script>
<script defer src="/assets/js/category.js"></script>
<script defer src="/assets/js/bgChange.js"></script>
</body>
</html>
