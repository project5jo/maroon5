<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="stylesheet" href="/assets/css/admin.css">
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
    <link rel="stylesheet" href="/assets/css/footer.css"/>
</head>
<body>
<!-- header  -->
<%@ include file="../include/header.jsp" %>
<main>
    <section class="sign-in">
        <div class="inner">
            <div class="sign-in-title">
                <p>관리자 페이지</p>
            </div>
            <input
                    type="text"
                    class="email"
                    id="newTopicId"
                    name="account"
                    placeholder="변경할 주제의 아이디를 입력하세요."
            />
            <button onclick="updateTopicId()">서버 주제 변경시키기</button>
            <input
                    type="text"
                    class="pw"
                    id="newTopicContent"
                    name="password"
                    placeholder="새로 추가할 주제를 입력해주세요."
            />
            <button onclick="insertTopicContent()">주제 새로 추가하기</button>


            <div class="topics-grid">
                <ol class="olcards">
<%--                    <li style="--cardColor:#86530A">--%>
<%--                        <div class="content">--%>
<%--                            <div class="title">토픽 아이디</div>--%>
<%--                            <div class="text">토픽 주제</div>--%>
<%--                        </div>--%>
<%--                    </li>--%>
                </ol>
            </div>


        </div>
    </section>
</main>
<%@ include file="../include/footer.jsp" %>
<script>
    function updateTopicId() {
        const newTopicId = document.getElementById('newTopicId').value;
        fetch('/api/admin/updateTopic', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({topicId: newTopicId})
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
        if (newTopicContent.trim() === "") return
        fetch('/api/admin/updateTopicContent', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({topicContent: newTopicContent})
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
        const topic = await res.json();
        const topicListDiv = document.querySelector('.olcards');
        topicListDiv.innerHTML = '';
        topic.forEach(({ topicId, createdAt, topicContent }) => {
            const topicLi = document.createElement('li');
            topicLi.style.setProperty('--cardColor', '#86530A'); // 카드 색상 설정
            topicLi.innerHTML = `
            <div class="content">
                <div class="title">ID: \${topicId} , 생성일 : \${createdAt}</div>
                <div class="text">주제 내용: \${topicContent}</div>
            </div>
        `;
            topicListDiv.appendChild(topicLi);
        });
    }

    window.onload = function () {
        viewAllTopic();
    };
</script>
</body>
</html>