package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> findByUserAccount(@Param("userAccount") String userAccount);

    boolean save(ShoppingCart shoppingCart);

    @Update("UPDATE ShoppingCart SET cart_total_price = #{cartTotalPrice}, cart_total_count = #{cartTotalCount} " +
            "WHERE cart_id = #{cartId}")
    boolean update(ShoppingCart shoppingCart);

    @Select("SELECT * FROM ShoppingCart WHERE user_account = #{userAccount} AND shop_item_id = #{itemId}")
    ShoppingCart findItemByUserAccountAndItemId(@Param("userAccount") String userAccount, @Param("itemId") Long itemId);

    @Delete("DELETE FROM ShoppingCart WHERE cart_id = #{cartId}")
    boolean delete(@Param("cartId") Long cartId);

    @Delete("DELETE FROM ShoppingCart WHERE user_account = #{userAccount}")
    void deleteByUserAccount(@Param("userAccount") String userAccount);  // 추가된 메서드

}
