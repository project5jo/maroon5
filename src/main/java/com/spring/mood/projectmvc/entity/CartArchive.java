package com.spring.mood.projectmvc.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CartArchive {

    private Long orderId; // fk
    private Long cartArchiveId; // PK
    private String userAccount; // FK (Users)
    private Long shopItemId; // FK (ShopItems)
    private BigDecimal cartTotalPrice;
    private Integer cartTotalCount;
}
