package com.spring.mood.projectmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDetailsMapper {
    void saveOrderDetails(@Param("orderId") Long orderId, @Param("cartArchiveIds") List<Long> cartArchiveIds);
    List<Map<String, Object>> getOrderDetails(@Param("userAccount") String userAccount);

    boolean save (Long id);

}
