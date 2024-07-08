<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!-- right-navigation  -->
<div class="rightNavi-box">
  <div class="rightNavi-content">

    <div class="profile-content">

      <div class="profile-img">
        <img src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}" alt="profile" onclick="openProfile ()">
      </div>

      <div class="profile-icon" onclick="openProfile ()">
        <i class="fas fa-user-cog"></i>
      </div>

    </div>

    <div class="profile-name">
      <h2 class="profile-name">${isUpdated ? updatedMember.account : nowMember.account} 님</h2>
      <p>${isUpdated ? updatedMember.email: nowMember.email}</p>
    </div>

    <div class="profile-infoBtn">
      <a  href="/mypage-memberinfo"><p class="Menu-title">회원정보수정</p></a>
    </div>
    
  </div> 
</div>
