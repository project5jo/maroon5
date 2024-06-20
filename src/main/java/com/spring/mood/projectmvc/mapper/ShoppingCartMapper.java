package com.spring.mood.projectmvc.mapper;

import ch.qos.logback.core.hook.ShutdownHook;
import com.spring.mood.projectmvc.dto.requestDto.ShoppingCartRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface ShoppingCartMapper{

    boolean save(ShoppingCart cart);

    Member findOne(String userAccount);

    boolean delete(ShoppingCart cart);

    List<ShoppingCartResponseDto> findAll(ShoppingCart cart);
}
