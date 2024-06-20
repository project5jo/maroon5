package com.spring.mood.projectmvc.dto.responseDto;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private long orderId;
    private String userAccount;
    private LocalDateTime orderDate;
    private String address1;
    private String address2;
    private String address3;
    private String receiverName;
    private String receiverPhone;

    // Orders 엔티티로부터 OrderResponseDto 생성
    public static OrderResponseDto fromEntity(Orders order) {
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .userAccount(order.getUserAccount())
                .orderDate(order.getOrderDate())
                .address1(order.getAddress1())
                .address2(order.getAddress2())
                .address3(order.getAddress3())
                .receiverName(order.getReceiverName())
                .receiverPhone(order.getReceiverPhone())
                .build();
    }
}
