package com.spring.mood.projectmvc.dto.requestDto;

import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private String userAccount;
    private LocalDateTime orderDate;
    private String address1;
    private String address2;
    private String address3;
    private String receiverName;
    private String receiverPhone;

    // Orders 엔티티로 변환
    public Orders toOrderEntity(Member member) {
        return Orders.builder()
                .userAccount(this.userAccount)
                .orderDate(this.orderDate)
                .address1(this.address1)
                .address2(this.address2)
                .address3(this.address3)
                .receiverName(this.receiverName)
                .receiverPhone(this.receiverPhone)
                .build();
    }
}
