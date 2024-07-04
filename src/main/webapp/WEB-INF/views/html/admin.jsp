<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
<%--    <link rel="stylesheet" href="/assets/css/admin.css">--%>
</head>
<body>
<h1>관리자 페이지</h1>
<div>
    <label for="newTopicId">새로운 Topic ID: </label>
    <input type="text" id="newTopicId">
    <button onclick="updateTopicId()">Update Topic ID</button>
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
                    alert('Topic ID updated successfully');
                } else {
                    alert('Failed to update Topic ID');
                }
            })
            .catch(error => {
                console.error('Error updating Topic ID:', error);
            });
    }
</script>
</body>
</html>