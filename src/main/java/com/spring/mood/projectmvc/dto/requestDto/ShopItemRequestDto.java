package com.spring.mood.projectmvc.dto.requestDto;

import lombok.*;
import com.spring.mood.projectmvc.entity.ShopItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @ToString
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopItemRequestDto {
    private String shopItemName;
    private String shopItemDesc;
    private BigDecimal shopItemPrice;
    private String shopItemImg;
    private LocalDateTime shopItemDate;
    private long shopItemStock;
    private long shopItemView;

    public ShopItem toShopItemEntity () {
        return ShopItem.builder()
                .shopItemName(this.shopItemName)
                .shopItemDesc(this.shopItemDesc)
                .shopItemPrice(this.shopItemPrice)
                .shopItemImg(this.shopItemImg)
                .shopItemDate(this.shopItemDate)
                .shopItemStock(this.shopItemStock)
                .shopItemView(this.shopItemView)
                .build();
    }
}
