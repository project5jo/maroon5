package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.OrderDetails;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.mapper.OrderDetailsMapper;
import com.spring.mood.projectmvc.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderMapper orderMapper;


    private final OrderDetailsMapper orderDetailsMapper;
    private final CartArchiveService cartArchiveService;
    private final MemberMapper memberMapper;
    private final ShoppingCartService shoppingCartService;

    @Transactional
    public void createOrder(Orders order) {
        orderMapper.saveOrder(order);
    }

    public int calculateTotalPrice(String userAccount) {
        List<ShoppingCartResponseDto> cartItems = shoppingCartService.getCartByUser(userAccount);
        return cartItems.stream()
                .mapToInt(ShoppingCartResponseDto::getCartTotalPrice)
                .sum();
    }

    //포인트 처리
    public void DeductionPoint(Integer usesPoint, String account, RedirectAttributes redirectAttributes) {
        // 멤버 찾기
        Member user = memberMapper.findOne(account);
        if (user == null) {
            log.error("User not found: {}", account);
            redirectAttributes.addAttribute("error", "User not found");
            return;
        }

        // 디비 멤버 포인트
        Integer userPoint = user.getUserPoint();

        if (usesPoint > userPoint) {
            redirectAttributes.addAttribute("error", "Insufficient points");
            return;
        }

        Integer deductionPoint = userPoint - usesPoint;
        log.info("deductionPoint : {}", deductionPoint);

        user.setUserPoint(deductionPoint);
        log.info("setUser : {}", user);
        memberMapper.updatePoint(deductionPoint, account);
        redirectAttributes.addFlashAttribute("loginUser", user);
    }

    public Member findUser(String account) {
        return memberMapper.findOne(account);
    }

    @Transactional
    public void createOrderWithDetails(Orders order, List<OrderDetails> orderDetailsList) {
        // 주문 생성
        log.info("Creating order: {}", order);
        orderMapper.insertOrder(order);
        Long orderId = order.getOrderId(); // 주문 생성 후 주문 ID 가져오기
        log.info("Generated orderId: {}", orderId);

        // 주문 상세 정보에 주문 ID 설정
        for (OrderDetails details : orderDetailsList) {
            details.setOrderId(orderId);
            log.info("Setting orderId for orderDetails: {}", details);
        }

        // 주문 상세 정보 저장
        orderDetailsMapper.insertOrderDetails(orderId, orderDetailsList);
        log.info("Order details saved for orderId: {}", orderId);
    }

    @Transactional
    public void saveOrderDetails(Long orderId) {
        log.info("Saving order details with Order ID: {}", orderId);
        orderDetailsMapper.saveOrderDetails(orderId);
    }

    public List<OrderDetailResponseDto> getOrderDetailsByOrderId(Long orderId) {
        return orderMapper.getOrderDetailsByOrderId(orderId);
    }

    public Map<String, List<Map<String, Object>>> getGroupedOrderHistory(String userAccount) {
        List<Map<String, Object>> orderHistory = cartArchiveService.getOrderHistory(userAccount);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00");
        return orderHistory.stream()
                .collect(Collectors.groupingBy(order -> {
                    Timestamp archivedAtTimestamp = (Timestamp) order.get("archived_at");
                    LocalDateTime archivedAt = archivedAtTimestamp.toLocalDateTime();
                    return archivedAt.format(formatter);
                }));
    }
}

