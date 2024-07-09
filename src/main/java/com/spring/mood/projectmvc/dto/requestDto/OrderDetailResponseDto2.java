package com.spring.mood.projectmvc.dto.requestDto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponseDto2 {
    private Integer orderId;
    private BigDecimal totalCount;
//    private List<String> itemlist;/
}
