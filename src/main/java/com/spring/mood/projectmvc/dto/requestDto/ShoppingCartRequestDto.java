package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.ShopItem;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartRequestDto {
    private Long cartId;
    private String userAccount;
    private Long shopItemId;
    private Double cartTotalPrice;
    private long cartTotalCount;

    public ShopItem toShopItemEntity() {
        return ShopItem.builder()
                .shopItemId(this.shopItemId)
                .build();
    }

    public Member toUserEntity() {
        return Member.builder()
                .userAccount(this.userAccount)
                .build();
    }

    public ShoppingCart toCartEntity(Member member, ShopItem item) {
        return ShoppingCart.builder()
                .cartId(this.cartId)
                .cartTotalPrice(this.cartTotalPrice)
                .cartTotalCount(this.cartTotalCount)
                .member(member)
                .items(List.of(item))  // assuming each ShoppingCart has multiple items
                .build();
    }
}
