package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper {

    // 장바구니에 상품 추가
    boolean save(ShoppingCart shoppingCart);

    // 기타 필요한 장바구니 관련 메서드 추가 가능
}
