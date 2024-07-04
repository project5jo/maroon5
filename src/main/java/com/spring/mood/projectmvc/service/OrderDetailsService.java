package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.mapper.OrderDetailsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsMapper orderDetailsMapper;

    @Transactional
    public void saveOrderDetails(Long orderId, List<Long> cartArchiveIds) {
        log.info("Saving order details with Order ID: {}", orderId);
        orderDetailsMapper.saveOrderDetails(orderId);
    }

    public List<Map<String, Object>> getOrderDetails(String userAccount) {
        return orderDetailsMapper.getOrderDetails(userAccount);
    }
}
