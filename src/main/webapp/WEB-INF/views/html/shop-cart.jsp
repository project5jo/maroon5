<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <h1>Shopping Cart</h1>
    <c:forEach var="item" items="${cartItems}">
        <div>
            <p>상품명: ${item.shopItemName}</p>
            <p>수량: ${item.cartTotalCount}</p>
            <p>가격: ${item.cartTotalPrice}</p>
            <form action="removeCartItem" method="post">
                <input type="hidden" name="shopItemId" value="${item.shopItemId}">
                <button type="submit">삭제</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>
