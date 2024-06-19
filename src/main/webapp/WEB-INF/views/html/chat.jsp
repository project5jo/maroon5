<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
<div>
    <input type="text" id="sender" placeholder="Your name">
    <input type="text" id="message" placeholder="Type a message...">
    <button onclick="sendMessage()">Send</button>
</div>
<div id="chat"></div>

<script>
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
        let sender = document.getElementById('sender').value;
        let content = document.getElementById('message').value;
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
        let messageElement = document.createElement('div');
        messageElement.innerHTML = '<strong>' + message.sender + ':</strong> ' + message.content;
        chat.appendChild(messageElement);
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

    window.onload = function () {
        // 사이트 진입시 일단은 자동으로 연결
        connect();
        loadMessages();
        setInterval(loadMessages, 5000);

    }

    connect();
</script>
</body>
</html>