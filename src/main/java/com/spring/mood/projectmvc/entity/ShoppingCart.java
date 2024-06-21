
package com.spring.mood.projectmvc.entity;

import lombok.*;



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

    private Double cartTotalPrice;
    private long cartTotalCount;


}
