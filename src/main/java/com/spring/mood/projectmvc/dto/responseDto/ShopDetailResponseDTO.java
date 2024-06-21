package com.spring.mood.projectmvc.dto.responseDto;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDetailResponseDTO {

    private Long cartId;
    private String userAccount;
    private Long shopItemId;

    private Double cartTotalPrice;
    private long cartTotalCount;


}
