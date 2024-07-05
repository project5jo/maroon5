package com.spring.mood.projectmvc.entity;

import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderDetails {

    private Long orderDetailId;

    // FK
    @Setter
    private Long orderId;
    private String userAccount;
    private Long shopItemId;

    private Long orderDetailCount;
    private String orderDetailStatus;
}
