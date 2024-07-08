<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link rel="stylesheet" href="/assets/css/mypage-cancel.css" />
    <link rel="stylesheet" href="/assets/css/shop-header.css" />
    <link rel="stylesheet" href="/assets/css/footer.css"/>

    <script src="/assets/js/mypage-cancel.js" defer></script>
    <script src="/assets/js/mypage.js" defer></script>
    <script src="/assets/js/category.js/" defer></script>

  </head>
  <body>

    <!-- modalBack -->
    <div class="modalBack"></div>

    <section class="section-header">
      <%@ include file="../include/header.jsp" %>
    </section>


    <section class="section-container">
  
      <nav class="section-leftNav">
        <%@ include file="mypage-leftNavigation.jsp" %>
      </nav>
  
      <main class="section-main">
        <div class="middle-box">
          <form action="/mypage-cancel" method="post">

            <div class="middleForm-box">

              <div class="middleForm-content">
                <h2>회원 탈퇴를 신청하기 전에 안내사항을 꼭 확인해주세요.</h2>
              </div>

              <div class="middleForm-content">
                <h2><i class="fas fa-check"></i>사용하고 계신 아이디 ${nowMember.account} 은/는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</h2>
                <div class="form-indentation">
                  <p>탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가하오니 신중하게 선택하시기 바랍니다.</p>
                </div>
              </div>

              <div class="middleForm-content">
                <h2><i class="fas fa-check"></i>탈퇴 후 회원정보 및 서비스 이용기록은 모두 삭제됩니다.</h2>
                <div class="form-indentation">
                  <p>회원정보 및 구매기록 등 서비스 이용기록은 모두 삭제되며 삭제된 데이터는 복구되지 않습니다. </p>
                </div>
                <div class="form-indentation">
                  <p>삭제되는 내용을 확인하시고 필요한 데이터는 미리 백업을 해주세요.</p>
                </div>
              </div>

              <div class="middleForm-content">
                <table>
                  <tr>
                      <td>회원정보</td>
                      <td>회원가입시 저장된 개인정보 삭제</td>
                  </tr>
                  <tr>
                      <td>구매정보</td>
                      <td>구매기록 삭제</td>
                  </tr>
                </table>
              </div>

              <div class="middleForm-content">
                <h2>탈퇴 후에는 아이디 ${nowMember.account} 로 다시 가입할 수 없으며 아이디와 데이터는 복구할 수 없습니다.</h2>
                <h2><input type="checkbox" class="cancelInput" name="deleteFlag" onclick="checkToggle(this)"> 안내사항을 모두 확인하였으며, 이에 동의합니다.</h2>
              </div>

              <div class="middleForm-content checkCenter">
                <button class="check" type="button" >탈퇴하기</button>
              </div>

            </div>
          
            <!-- modal -->
            <div class="middleModal-box">
              <div class="middleModal-content">

                <span class="modal-close" onclick="closeModal()">&times;</span>
                <p>탈퇴하시겠습니까?</p>
                <button type="button" onclick="closeModal()">취소</button>
                <button type="submit">확인</button>
              
              </div>
            </div>
            <!-- modal end -->

          </form>
          <!-- form end -->
        </div>
        <!-- middle-box end -->
      </main>
      <!-- section-middle end -->
  
      <aside class="section-rightAside">
        <%@ include file="mypage-rightNavigation.jsp" %>
      </aside>

    </section>
    <!-- section-container end -->
    
    <section class="section-footer">
      <%@ include file="../include/footer.jsp" %>
    </section>
    
  </body>
</html>