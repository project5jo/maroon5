package com.spring.mood.projectmvc.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 숫자타입 전부 어떤걸로 통일할지 검토필요
public class ShopItem {
    private Long shopItemId; // PK
    private String shopItemName;
    private String shopItemDesc;
    private BigDecimal shopItemPrice; // decimal type임 수정검토
    private String shopItemImg;
    private LocalDateTime shopItemDate;
    private Long shopItemStock;
    private Long shopItemView;
    private Integer quantity;
}
