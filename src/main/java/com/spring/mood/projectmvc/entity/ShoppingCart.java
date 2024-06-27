
package com.spring.mood.projectmvc.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {
    private Long cartId;
    private String userAccount;
    private Long shopItemId;
    private BigDecimal cartTotalPrice;
    private long cartTotalCount;
    private List<ShopItem> items;


}
