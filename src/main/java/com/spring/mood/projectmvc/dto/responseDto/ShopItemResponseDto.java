package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.ShopItem;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopItemResponseDto {
    private Long shopItemId;
    private String shopItemName;
    private String shopItemDesc;
    private Double shopItemPrice;
    private String shopItemImg;
    private LocalDateTime shopItemDate;
    private long shopItemStock;
    private long shopItemView;

    // ShopItem 엔티티로부터 ShopItemResponseDto 생성
    public static ShopItemResponseDto fromEntity(ShopItem shopItem) {
        return ShopItemResponseDto.builder()
                .shopItemId(shopItem.getShopItemId())
                .shopItemName(shopItem.getShopItemName())
                .shopItemDesc(shopItem.getShopItemDesc())
                .shopItemPrice(shopItem.getShopItemPrice())
                .shopItemImg(shopItem.getShopItemImg())
                .shopItemDate(shopItem.getShopItemDate())
                .shopItemStock(shopItem.getShopItemStock())
                .shopItemView(shopItem.getShopItemView())
                .build();
    }
}
