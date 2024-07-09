package com.spring.mood.projectmvc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartArchiveMapper {
    void copyCartToArchive(@Param("userAccount") String userAccount, @Param("orderId") Long orderId);
    List<Long> getCartArchiveIds(@Param("userAccount") String userAccount);

    List<Map<String, Object>> getOrderHistory(@Param("userAccount") String userAccount);
}
