package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartResponseDto {
    private Long cartId;
    private Double cartTotalPrice;
    private long cartTotalCount;
    private String userAccount;
    private List<ShopItemResponseDto> items;

    // ShoppingCart 엔티티로부터 ShoppingCartResponseDto 생성
    public static ShoppingCartResponseDto fromEntity(ShoppingCart cart) {
        return ShoppingCartResponseDto.builder()
                .cartId(cart.getCartId())
                .cartTotalPrice(cart.getCartTotalPrice())
                .cartTotalCount(cart.getCartTotalCount())
                .userAccount(cart.getMember().getUserAccount())
                .items(cart.getItems().stream()
                        .map(ShopItemResponseDto::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
