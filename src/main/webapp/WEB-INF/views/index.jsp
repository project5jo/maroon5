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
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/chat-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/messages', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    }

    function sendMessage() {
        var sender = document.getElementById('sender').value;
        var content = document.getElementById('message').value;
        stompClient.send("/app/sendMessage", {}, JSON.stringify({'sender': sender, 'content': content}));
    }

    function showMessage(message) {
        var chat = document.getElementById('chat');
        var messageElement = document.createElement('div');
        messageElement.innerHTML = '<strong>' + message.sender + ':</strong> ' + message.content;
        chat.appendChild(messageElement);

        function loadMessages() {
            fetch('/messages')
                .then(response => response.json())
                .then(messages => {
                    messages.forEach(message => {
                        showMessage(message);
                    });
                });
        }

        window.onload = function() {
            connect();
            loadMessages();
        }
    }

    connect();
</script>
</body>
</html>