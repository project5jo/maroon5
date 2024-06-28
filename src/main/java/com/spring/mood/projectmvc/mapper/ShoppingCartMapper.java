package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> findByUserAccount(String userAccount);
    // 장바구니 아이템 조회 메서드 추가
    List<ShopItem> findItemsByCartId(@Param("cartId") Long cartId);
    // 장바구니에 상품 추가
    boolean save(ShoppingCart shoppingCart);

    @Update("UPDATE ShoppingCart SET cart_total_price = #{cartTotalPrice}, cart_total_count = #{cartTotalCount} " +
            "WHERE cart_id = #{cartId}")
    boolean update(ShoppingCart shoppingCart);

    @Select("SELECT * FROM ShoppingCart WHERE user_account = #{userAccount} AND shop_item_id = #{itemId}")
    ShoppingCart findItemByUserAccountAndItemId(@Param("userAccount") String userAccount, @Param("itemId") Long itemId);
}
