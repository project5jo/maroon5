package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.ShoppingCart;
import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartResponseDto {
    private Long cartId;
    private BigDecimal cartTotalPrice;
    private long cartTotalCount;
    private String userAccount;
    private Long shopItemId;
    private String shopItemName;
    private String shopItemDesc;
    private String shopItemImg;

    // ShoppingCart 엔티티로부터 ShoppingCartResponseDto 생성
    public static ShoppingCartResponseDto fromEntity(ShoppingCart cart) {
        return ShoppingCartResponseDto.builder()
                .cartId(cart.getCartId())
                .cartTotalPrice(cart.getCartTotalPrice())
                .cartTotalCount(cart.getCartTotalCount())
                .userAccount(cart.getUserAccount())
                .shopItemId(cart.getShopItemId())
                .shopItemName(cart.getShopItemName())
                .shopItemDesc(cart.getShopItemDesc())
                .shopItemImg(cart.getShopItemImg())
                .build();
    }
}
