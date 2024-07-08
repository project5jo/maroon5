<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>마이페이지 홈 | 애착 페이지</title>

    <link rel="stylesheet" href="/assets/css/mypage-main.css"/>
    <link rel="stylesheet" href="/assets/css/shop-header.css"/>
    <link rel="stylesheet" href="/assets/css/footer.css"/>
    <link rel="icon" href="/assets/img/favicon.ico">
    <script src="/assets/js/mypage.js" defer></script>
    <script src="/assets/js/category.js/" defer></script>
</head>
<body>
<section class="section-header">
    <%@ include file="../include/header.jsp" %>
</section>


<section class="section-container">
    <nav class="section-leftNav">
        <%@ include file="mypage-leftNavigation.jsp" %>
    </nav>

    <main class="section-main">
        <div class="profile-section">
            <div class="profile-img">
                <img
                        src="${not empty nowMember.profileImage ? nowMember.profileImage : '/assets/img/profile3.jpg'}"
                        alt="profile"
                        onclick="openProfile()"
                />
            </div>
            <div class="profile-info">
                <p>${nowMember.account}님</p>
                <p>환영합니다</p>
                <p>내 포인트: ${nowMember.point}원</p>
                <div class="profile-actions">
                    <a href="/mypage-password">비밀번호 변경</a>
                    <a href="/mypage-memberinfo">회원정보수정</a>
                    <a href="/mypage-point">포인트 충전하러가기</a>
                </div>
            </div>
        </div>

        <div class="shopping-section">
            <div class="shopping-info-grid">
                <div class="shopping-info">
                    <p>쇼핑 홈</p>
                    <div class="shopping-img">
                        <a href="/shop"><img src="/assets/img/shopping.png" alt="shop" /></a>
                    </div>
                    <div class="actions">
                        <a href="/shop">
                        <button class="manage-btn">이동하기</button>
<%--                        <button class="more-btn">더보기</button>--%>
                        </a>
                    </div>
                </div>
                <div class="shopping-info">
                    <p>주문상세</p>
                    <div class="shopping-img">
                        <a href="/complete"><img src="/assets/img/list.png" alt="order"/></a>
                    </div>
                    <div class="actions">
                        <a href="/complete">
                        <button class="manage-btn">이동하기</button>
                        </a>
<%--                        <button class="more-btn">더보기</button>--%>
                    </div>
                </div>
                <div class="shopping-info">
                    <p>장바구니</p>
                    <div class="shopping-img">
                        <a href="/cart"><img src="/assets/img/cart.png" alt="cart"/></a>
                    </div>
                    <div class="actions">
                        <a href="/cart">
                        <button class="manage-btn">이동하기</button>
                        </a>
<%--                        <button class="more-btn">더보기</button>--%>
                    </div>
                </div>
            </div>
        </div>


        <div class="middleModal-box">
            <div class="middleModal-content">
                <span class="modal-close" onclick="closeModal()">&times;</span>
                <p>충전하시겠습니까?</p>
                <button type="button" onclick="closeModal()">취소</button>
                <button type="submit">확인</button>
            </div>
        </div>
    </main>

    <aside class="section-rightAside">
        <%@ include file="mypage-rightNavigation.jsp" %>
    </aside>
</section>

<section class="section-footer">
    <%@ include file="../include/footer.jsp" %>
</section>
</body>
</html>