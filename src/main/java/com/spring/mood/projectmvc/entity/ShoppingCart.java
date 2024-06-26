
package com.spring.mood.projectmvc.entity;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {
    // pk
    private Long cartId;
    // fk
    private String userAccount;
    private Long shopItemId;

    private BigDecimal cartTotalPrice;
    private long cartTotalCount;


}
