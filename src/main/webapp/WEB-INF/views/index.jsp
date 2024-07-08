<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>나의 애착 페이지</title>
    <link rel="stylesheet" href="/assets/css/header.css"/>
    <link rel="stylesheet" href="/assets/css/main.css"/>
    <link rel="stylesheet" href="/assets/css/bgChange.css">
    <link rel="icon" href="/assets/img/favicon.ico">
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
        <video id="backgroundVideo" autoplay muted loop playsinline
               style="width: 100%; height: 100%; object-fit: cover;">
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
                <c:if test="${loginUser.userRole == 'admin'}">
                    <a href="/zzz" id="admin">ADMIN PAGE</a>
                </c:if>
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

    function initialize() {
        fetch('/api/admin/currentTopic')
            /**
             * 접속하면 initialize 실행
             * currentTopic 페칭한 후, topicId 불러옴. 불러온 후 connect 시도
             */
            .then(response => response.json())
            .then(data => {
                topicId = data.topicId;
                connect(); // WEB_SOCKET 채팅 접속 시도
                setupTopicIdListener(); // admin이 topic 바뀌는 거 감지하기 위한 웹소켓 접속 시도
            }) // (수정)
            .catch(error => console.error("토픽 에러!!!", error));
    }


    function connect() {
        const socket = new SockJS("/chat-websocket");  // 채팅 접속 소켓임. 실시간 채팅을 위한 소켓
        stompClient = Stomp.over(socket); // 다른 브라우저도 사용하기 위해서 stompClient 사용
        stompClient.connect({}, function (frame) { // 연결 시킨 후 joinRoom 실행
            joinRoom(); // 방 구독 및 참여 (수정)
        }); // (수정)
    }


    function setupTopicIdListener() {
        const socket = new SockJS('/chat-websocket');
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
            stompClient.subscribe('/topic/currentTopic', function (message) {
                /**
                 * /topic/currentTopic 을 구독하고 있다가 바뀌면 파싱한다음 data로 변환해줌.
                 * 변환하고 topicId 수정해줌.
                 */
                const data = JSON.parse(message.body);
                topicId = data.topicId;
                joinRoom();

            });
        });
    }




    function joinRoom() {
        /**
         * join 하면 방금 업데이트한 topicId와 로그인한 유저의 정보를 가지고 post mapping 날림.
         * post mapping 날리면 메세지에는 자동으로 roomId와 지금 방의 주제를 가지고 옴.
         */
        if (!topicId || !loginUser) return;
        fetch(`/api/chat/joinRoom`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({topicId: topicId, username: loginUser})
        })
            .then(response => response.json())
            .then(data => {
                roomId = data.roomId;
                document.querySelector('.chat-title .point').textContent = data.topicContent;
                setTimeout(() => {
                    subscribeToRoom(roomId);
                }, 1000);
            })
            .catch(error => {
                console.error("채팅방 입장 실패함:", error);
            });
    }

    /**
     * 구독중인 곳이 있다면 구독해제를 하고 다시 시도함.
     * 구독한 다음 showmessage 실행해서 태그를 그려줌
     * 원래 있던 채팅들 태그 그려줌.
     *
     * @param roomId1 - 들어갈 방 번호임.
     *
     */
    function subscribeToRoom(roomId1) {
        if (currentSubscription) {
            currentSubscription.unsubscribe();
        }

        currentSubscription = stompClient.subscribe(`/topic/messages/\${topicId}/\${roomId1}`, function (message) {
            showMessage(JSON.parse(message.body));
        });

        loadMessages(roomId1);
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

    /**
     * 내가 보낸 채팅이면 오른쪽, 상대가 보낸 채팅은 왼쪽으로 태그 그림.
     * @param message - 기존 채팅방에 있던 메세지들으ㄹ JSON으로 파싱한 데이터임.
     */
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


        message.profileUrl ? profile = message.profileUrl : profile = `/assets/img/profile3.jpg`;


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


    /**
     * 비동기로 패칭 시도후 새로운 채팅방의 메세지를 로드시킴.
     * @param roomId - 새로운 채팅방의 방 번호
     */
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


        // 1. "/app/sendMessage":
        // •	메시지를 보낼 서버의 경로임~
        //     2.	{}:
        // •	서버에 같이 보낼 헤더 정보 > 지금 시간만 보내줌
        //     3.	JSON.stringify({'sender': sender, 'content': content}):
        // •	입력한 메세지 본문을 JSON 으로 변환해서 보내줌
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
