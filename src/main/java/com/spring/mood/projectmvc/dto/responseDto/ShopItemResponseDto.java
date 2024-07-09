package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.ShopItem;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopItemResponseDto {
    private Long shopItemId;
    private String shopItemName;
    private String shopItemDesc;
    private int shopItemPrice;
    private String shopItemImg;
    private LocalDateTime shopItemDate;
    private Long shopItemStock;
    private Long shopItemView;

    public static ShopItemResponseDto fromEntity(ShopItem shopItem) {
        return ShopItemResponseDto.builder()
                .shopItemId(shopItem.getShopItemId())
                .shopItemName(shopItem.getShopItemName())
                .shopItemDesc(shopItem.getShopItemDesc())
                .shopItemPrice(shopItem.getShopItemPrice())
                .shopItemImg(shopItem.getShopItemImg())
                .shopItemDate(shopItem.getShopItemDate())
                .shopItemStock(shopItem.getShopItemStock())
                .shopItemView(1L)
                .build();
    }
}
