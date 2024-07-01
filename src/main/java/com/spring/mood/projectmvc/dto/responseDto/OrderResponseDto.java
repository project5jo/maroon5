package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderResponseDto {
    private Long orderId;
    private String userAccount;
    private LocalDateTime orderDate;
    private String address1;
    private String address2;
    private String address3;
    private String receiverName;
    private String receiverPhone;

    public static OrderResponseDto fromEntity(Orders orders) {
        return OrderResponseDto.builder()
                .orderId(orders.getOrderId())
                .userAccount(orders.getUserAccount())
                .orderDate(orders.getOrderDate())
                .address1(orders.getAddress1())
                .address2(orders.getAddress2())
                .address3(orders.getAddress3())
                .receiverName(orders.getReceiverName())
                .receiverPhone(orders.getReceiverPhone())
                .build();
    }
}
