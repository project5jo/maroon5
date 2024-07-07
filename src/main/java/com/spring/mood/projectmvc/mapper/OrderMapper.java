package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
import com.spring.mood.projectmvc.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO Orders (user_account, order_date, address1, address2, address3, receiver_name, receiver_phone) " +
            "VALUES (#{userAccount}, #{orderDate}, #{address1}, #{address2}, #{address3}, #{receiverName}, #{receiverPhone})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void saveOrder(Orders order);

    void insertOrder(Orders order);

    List<OrderDetailResponseDto> getOrderDetailsByOrderId(@Param("orderId") Long orderId);
    List<OrderDetailResponseDto> getAllDetail(@Param("orderId") Long orderId, @Param("userAccount") String userAccount);
}
