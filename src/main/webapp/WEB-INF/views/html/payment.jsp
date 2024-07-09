<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%> <%@ taglib prefix="c"
                                           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>주문 결제 | 애착 페이지</title>

  <link rel="stylesheet" href="/assets/css/shop-header.css" />
  <link rel="stylesheet" href="/assets/css/payment.css" />
  <link rel="stylesheet" href="/assets/css/footer.css"/>
  <link rel="icon" href="/assets/img/favicon.ico">
  <script src="/assets/js/category.js/" defer></script>
  <script src="/assets/js/payment.js/" defer></script>
  
  <!-- 카카오 주소 검색 API 스크립트 -->
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script>
    function sample6_execDaumPostcode() {
      new daum.Postcode({
        oncomplete: function(data) {
          // 각 주소의 노출 규칙에 따라 주소를 조합한다.
          var addr = ''; // 주소 변수
          var extraAddr = ''; // 참고항목 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            addr = data.roadAddress;
          } else { // 사용자가 지번 주소를 선택했을 경우(J)
            addr = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if(data.userSelectedType === 'R'){
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
              extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraAddr !== ''){
              extraAddr = ' (' + extraAddr + ')';
            }
            // 조합된 참고항목을 해당 필드에 넣는다.
            document.getElementById("sample6_extraAddress").value = extraAddr;

          } else {
            document.getElementById("sample6_extraAddress").value = '';
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('sample6_postcode').value = data.zonecode;
          document.getElementById("sample6_address").value = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.getElementById("sample6_detailAddress").focus();
        }
      }).open();
    }
  </script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<main>
  <section class="payment">
    <form class="form-pay" action="/checkout" method="post" enctype="multipart/form-data">
      <div class="inner">
        <div class="payment-title">
          <p>주문결제</p>
        </div>

        <div class="payment-item">
          <p class="payment-sub-title">주문상품<span></span></p>
          <ul class="prd-box-list">
            <c:forEach var="cartItem" items="${cartItems}">
              <li class="prd-box">
                <div class="prd-item"><div class="item-img">
                  <img src="${cartItem.shopItemImg}" alt="${cartItem.shopItemName}">
                </div></div>
                <div class="prd-item-info">
                  <p class="prdName">${cartItem.shopItemName}</p>
                  <p>수량:${cartItem.cartTotalCount}개</p>
                  <p class="prdPrice"><span>${cartItem.cartTotalPrice}</span>원</p>
                </div>
              </li>
            </c:forEach>
          </ul>
        </div>

        <div class="payment-Delivery">
          <p class="payment-sub-title">배송지<span></span></p>
          <div class="tr">
            <p class="th-title">받는분<span class="icoRequired"></span></p>
            <input
                    type="text"
                    class="receiver-name"
                    value="${loginUser.nickName}"
                    name="receiverName"
                    
                    required />
          </div>
        
          <div class="tr">
            <p class="th-title">받는분 휴대번호<span></span></p>
            <input
                    type="text"
                    class="receiver-phone"
                    placeholder="받으실분 전화번호를 입력하세요"
                    name="receiverPhone"
                    required
            />
          </div>
          <div class="tr">
            <p class="th-title">주소<span></span></p>
            <div class="postal-code">
              <input type="text" id="sample6_postcode" placeholder="우편번호" name="address1" required>
              <button type="button" class="postal-code-search" onclick="sample6_execDaumPostcode()">주소검색</button>
            </div>
            <input type="text" id="sample6_address" placeholder="주소" name="address2" required>
            <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address3" required>
            <input class="sample6-extraAddress" type="text" id="sample6_extraAddress" placeholder="참고항목" readonly>
          </div>
        </div>

        <div class="user-point">
          <p class="payment-sub-title">포인트<span></span></p>
          <div class="tr">
            <p class="th-title">
              포인트 결제<span class="icoRequired"></span>
            </p>
            <div class="user-point-input">
              <input
                      type="text"
                      id="usesPoint"
                      class="usesPoint"
                      placeholder="사용할 포인트를 입력하세요"
                      name="usesPoint"
                      required
              />
              <!-- <button type="button" class="btn-point">적용</button> -->
            </div>
            <p class="user-point-info">
              <span>보유하신 포인트: </span>${userPoint}
            </p>
          </div>
        </div>

        <div class="order-price-info">
          <p class="payment-sub-title">결제 정보<span></span></p>
          <div class="tr">
            <div class="price-info">
              <p class="th-title">주문상품</p>
              <p>${totalItemsPrice}원</p>
            </div>
          
            <div class="price-info">
              <p class="th-title">배송비</p>
              <p>+ 3,000원</p>
            </div>
            <div class="price-info">
              <p class="th-title">최종 금액</p>
              <p class="total-price">${totalOrderPrice}원</p>
            </div>
            <div class="price-info">
              <p class="th-title">포인트 결제</p>
              <p class="total-point">0원</p>
            </div>
          </div>
        </div>

        <button type="submit" class="btn-pay">${totalOrderPrice}원 결제</button>
      </div>
    </form>
  </section>
</main>
<%@ include file="../include/footer.jsp" %>
</body>
</html>
