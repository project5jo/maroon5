//package com.spring.mood.projectmvc.service;
//import com.spring.mood.projectmvc.entity.ShoppingCart;
//import com.spring.mood.projectmvc.mapper.ShoppingCartMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ShoppingCartService {
//
//    @Autowired
//    private ShoppingCartMapper shoppingCartMapper;
//
//    public void addItemToCart(String userAccount, int shopItemId, int count) {
//        ShoppingCart cartItem = shoppingCartMapper.findCartItem(Map.of("userAccount", userAccount, "shopItemId", shopItemId));
//        if (cartItem != null) {
//            // 기존 아이템 수량 업데이트
//            cartItem.setCartTotalCount(cartItem.getCartTotalCount() + count);
//            cartItem.setCartTotalPrice(cartItem.getCartTotalPrice().add(cartItem.getCartTotalPrice().multiply(BigDecimal.valueOf(count))));
//            shoppingCartMapper.updateCartItem(cartItem);
//        } else {
//            // 새로운 아이템 추가
//            cartItem = new ShoppingCart();
//            cartItem.setUserAccount(userAccount);
//            cartItem.setShopItemId(shopItemId);
//            cartItem.setCartTotalCount(count);
//            BigDecimal itemPrice = shoppingCartMapper.findShopItemById(shopItemId).getShopItemPrice();
//            cartItem.setCartTotalPrice(itemPrice.multiply(BigDecimal.valueOf(count)));
//            shoppingCartMapper.insertCartItem(cartItem);
//        }
//    }
//
//    public List<ShoppingCart> getCartItems(String userAccount) {
//        return shoppingCartMapper.findCartItemsByUser(userAccount);
//    }
//
//    public void removeItemFromCart(String userAccount, int shopItemId) {
//        shoppingCartMapper.deleteCartItem(Map.of("userAccount", userAccount, "shopItemId", shopItemId));
//    }
//}
