package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO Orders (user_account, order_date, address1, address2, address3, receiver_name, receiver_phone) " +
            "VALUES (#{userAccount}, #{orderDate}, #{address1}, #{address2}, #{address3}, #{receiverName}, #{receiverPhone})")
    void saveOrder(Orders order);
}
