<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>상품 목록 | 애착 페이지</title>
  <link rel="stylesheet" href="/assets/css/shop-index.css">
  <link rel="stylesheet" href="/assets/css/shop-header.css"/>
  <link rel="stylesheet" href="/assets/css/footer.css"/>
  <script src="https://kit.fontawesome.com/a9dfb46732.js" crossorigin="anonymous"></script>
</head>
<body>
  <%@ include file="../include/header.jsp" %>

    <main>
      <div class="inner">
          <div class="container">
              <div class="search-container">
                  <form action="/shop" method="get">
                      <div class="search-wrapper">
                          <input placeholder="제품 검색" type="text" name="keyword" class="search-bar">
                          <button type="submit" class="item-search">Search</button>
                      </div>
                  </form>
                  <c:if test="${userRole == 'ROLE_admin'}">
                      <button class="item-add" onclick="location.href='/shop/add'">Add Item</button>
                  </c:if>
              </div>
              <div class="main-container">
                  <ul>
                      <c:forEach var="item" items="${items}">
                 
                          <li class="item-box">
                            <c:if test="${userRole == 'ROLE_admin'}">
                              <button class="delete-btn" onclick="confirmDeletion('${item.shopItemId}')"> <i class="fas fa-times"></i></button>
                            </c:if>
                            
                            <a href="/shop/${item.shopItemId}">
                              <div class="img-box">
                                    <img src="${item.shopItemImg}" alt="${item.shopItemName}">
                                  </div>
                                  <div class="item-info-wrapper">

                                    <p class="item-name-title point">${item.shopItemName}</p>

                                    <p class="item-desc">${item.shopItemDesc}</p>

                                  </div>
                                </a>

                              <form id="addToCartForm-${item.shopItemId}" action="/cart" method="post" 
                              onsubmit="return handleFormSubmit(event, '${item.shopItemId}')">
                                  <div class="addBtn">
                                      <input type="hidden" name="itemId" value="${item.shopItemId}">
                                      <input type="hidden" name="itemPrice" id="itemPrice-${item.shopItemId}" value="${item.shopItemPrice}">
                                      <c:choose>
                                          <c:when test="${sessionScope.loginUser != null}">
                                              <input type="hidden" name="userAccount" value="${sessionScope.loginUser.account}">
                                          </c:when>
                                          <c:otherwise>
                                              <input type="hidden" name="userAccount" value="">
                                          </c:otherwise>
                                      </c:choose>
                                      <input type="hidden" name="quantity" value="1">
                                      <button class="item-cart-btn point hidden" id="cart-btn-${item.shopItemId}">
                                        <p>${item.shopItemPrice}원 </p>
                                        <p> TO CART</p>
                                      </button>
                                  </div>
                              </form>
                          </li>
                        
                      </c:forEach>
                  </ul>
              </div>
              <!-- 모달 HTML 코드 -->
              <div id="cartModal" class="modal">
                  <div class="modal-content">
                      <span class="close" onclick="closeModal()">&times;</span>
                      <p id="modalMessage">장바구니에 추가되었습니다.</p>
                      <button id="modalButton" onclick="goToCart()">Go to Cart</button>
                      <button onclick="goBack()">Back</button>
                  </div>
              </div>
          </div>
      </div>
  </main>

  <%@ include file="../include/footer.jsp" %>


</div>
  <script src="/assets/js/category.js"></script>
  <script src="/assets/js/shop-index.js"></script>
  <script src="/assets/js/Header.js"></script>
  
</body>
</html>
