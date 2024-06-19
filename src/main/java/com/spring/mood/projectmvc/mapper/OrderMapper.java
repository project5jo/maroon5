package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    Orders findOne (Long id);

    boolean save (Orders orders);

}
