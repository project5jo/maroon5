package com.spring.mood.projectmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.spring.mood.projectmvc.entity.OrderDetails;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDetailsMapper {
    void saveOrderDetails(@Param("orderId") Long orderId);
    List<Map<String, Object>> getOrderDetails(@Param("userAccount") String userAccount);

    void insertOrderDetails(@Param("orderId") Long orderId, @Param("orderDetails") List<OrderDetails> orderDetails);

    boolean save (Long id);

}
