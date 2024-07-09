package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.ShopItem;
import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderDetailRequestDto {
    private Long orderId;
    private String userAccount;
    private Long shopItemId;
    private Long orderDetailCount;
    private String orderDetailStatus;

    public ShopItem toShopItemEntity () {
        return ShopItem.builder()
                .shopItemId(this.shopItemId)
                .build();
    }

    public Member toUserEntity () {
        return Member.builder()
                .userAccount(this.userAccount)
                .build();
    }



}
