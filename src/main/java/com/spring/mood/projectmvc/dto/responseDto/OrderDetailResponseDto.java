package com.spring.mood.projectmvc.dto.responseDto;


import com.spring.mood.projectmvc.entity.Orders;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponseDto {
    private Long orderDetailId;

    private Long orderId;
    private String userAccount;
    private LocalDateTime orderDate;
    private String address1;
    private String address2;
    private String address3;
    private String receiverName;
    private String receiverPhone;
    private Long orderDetailCount;
    private String orderDetailStatus;
    private String shopItemName;
    private String shopItemDesc;
    private BigDecimal shopItemPrice; // decimal type임 수정검토
    private String shopItemImg;


    public OrderDetailResponseDto(String orderDetailStatus, Long orderDetailCount) {
        this.orderDetailStatus = orderDetailStatus;
        this.orderDetailCount = orderDetailCount;
    }

    // Orders 엔티티로부터 OrderDetailResponseDto 생성
    public static OrderDetailResponseDto fromEntity(Orders order, Long orderDetailCount, String orderDetailStatus) {
        return OrderDetailResponseDto.builder()
                .orderId(order.getOrderId())
                .userAccount(order.getUserAccount())
                .orderDate(order.getOrderDate())
                .address1(order.getAddress1())
                .address2(order.getAddress2())
                .address3(order.getAddress3())
                .receiverName(order.getReceiverName())
                .receiverPhone(order.getReceiverPhone())
                .orderDetailCount(orderDetailCount)
                .orderDetailStatus(orderDetailStatus)
                .build();
    }
}
