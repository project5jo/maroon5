package com.spring.mood.projectmvc.mapper;

import com.spring.mood.projectmvc.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

//    DTO 만들어진거 확인하고 만들기
//    List<OrderDetailResponseDTO> findAll(OrderDetailResponseDTO dto);

    boolean save (Long id);




}
