package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;

    @Transactional
    public void createOrder(Orders order) {
        orderMapper.saveOrder(order);
    }
}
