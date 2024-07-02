package com.spring.mood.projectmvc.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderRequestDto {
    private String userAccount;
    private String address1;
    private String address2;
    private String address3;
    private String receiverName;
    private String receiverPhone;

//    private int point;
}
