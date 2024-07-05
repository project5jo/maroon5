package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.ShoppingCart;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartResponseDto {
    private Long cartId;
    private int cartTotalPrice;
    private long cartTotalCount;
    private String userAccount;
    private Long shopItemId;
    private String shopItemName;
    private String shopItemDesc;
    private String shopItemImg;
    private int unitPrice;  // 추가된 필드

    // ShoppingCart 엔티티로부터 ShoppingCartResponseDto 생성
    public static ShoppingCartResponseDto fromEntity(ShoppingCart cart) {
        int unitPrice = (int) (cart.getCartTotalPrice() / cart.getCartTotalCount());
        return ShoppingCartResponseDto.builder()
                .cartId(cart.getCartId())
                .cartTotalPrice(cart.getCartTotalPrice())
                .cartTotalCount(cart.getCartTotalCount())
                .userAccount(cart.getUserAccount())
                .shopItemId(cart.getShopItemId())
                .shopItemName(cart.getShopItemName())
                .shopItemDesc(cart.getShopItemDesc())
                .shopItemImg(cart.getShopItemImg())
                .unitPrice(unitPrice)
                .build();
    }
}
