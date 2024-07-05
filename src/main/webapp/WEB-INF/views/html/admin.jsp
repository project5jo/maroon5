<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="/assets/css/admin.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            margin-top: 20px;
            color: #333;
        }

        .container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            width: 80%;
            max-width: 600px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            padding: 10px 15px;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-bottom: 10px;
        }

        button:hover {
            background-color: #0056b3;
        }

        .topicList {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h1>관리자 페이지</h1>
<div class="container">
    <label for="newTopicId">변경할 주제 번호: </label>
    <input type="text" id="newTopicId">
    <button onclick="updateTopicId()">변경할 토픽 아이디</button>

    <label for="newTopicContent">새로운 주제 선정하기: </label>
    <input type="text" id="newTopicContent">
    <button onclick="insertTopicContent()">추가할 주제 선정하기</button>

    <div class="topicList"></div>
</div>
<script>
    function updateTopicId() {
        const newTopicId = document.getElementById('newTopicId').value;
        fetch('/api/admin/updateTopic', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ topicId: newTopicId })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('토픽 바뀜~');
                } else {
                    alert('오류');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function insertTopicContent() {
        const newTopicContent = document.getElementById('newTopicContent').value;
        fetch('/api/admin/updateTopicContent', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({topicContent : newTopicContent })
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    alert('주제 추가 완료~');
                    viewAllTopic()
                } else {
                    console.log(data)
                    alert('오류');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    async function viewAllTopic() {
        const res = await fetch(`/api/admin/findAll`);
        const replies = await res.json();
        const topicListDiv = document.querySelector('.topicList');
        topicListDiv.innerHTML = '';
        replies.forEach(({topicId, createdAt, topicContent}) => {
            const topicDiv = document.createElement('div');
            topicDiv.innerHTML = `<strong>ID:</strong> \${topicId}, <strong>생성일:</strong> \${createdAt}, <strong>내용:</strong> \${topicContent}`;
            topicListDiv.appendChild(topicDiv);
        });
    }

    window.onload = function () {
        viewAllTopic();
    };
</script>
</body>
</html>