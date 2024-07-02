package com.spring.mood.projectmvc.entity;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Orders {

    private Long orderId; // PK
    private String userAccount; // FK ( Users )
    private LocalDateTime orderDate;
    private String address1;
    private String address2;
    private String address3;
    private String receiverName;
    private String receiverPhone;

//    private int point;
}
