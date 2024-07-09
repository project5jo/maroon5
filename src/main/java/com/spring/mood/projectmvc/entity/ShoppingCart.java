
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
    private int cartTotalPrice;
    private long cartTotalCount;

    // 추가 필드 (Join 결과를 위한 필드)
    private String shopItemName; // 수정된 부분
    private String shopItemDesc; // 수정된 부분
    private String shopItemImg; // 수정된 부분


}
