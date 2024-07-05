package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.OrderDetails;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.mapper.OrderDetailsMapper;
import com.spring.mood.projectmvc.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderDetailsMapper orderDetailsMapper;
    private final MemberMapper memberMapper;
    private final ShoppingCartService shoppingCartService;


    @Transactional
    public void createOrder(Orders order) {
        orderMapper.saveOrder(order);
    }

    public String loginUserAccount(HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        String account = loginUser.getAccount();
        return account;
    }

    //  장바구니 아이템 합산 금액
    public BigDecimal TotalItemsPrice(String userAccount) {
        List<ShoppingCartResponseDto> cartItems = shoppingCartService.getCartByUser(userAccount);
        return cartItems.stream()
                .map(ShoppingCartResponseDto::getCartTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //  배송비 추가 합산 금액
    public BigDecimal calculateTotalPrice(BigDecimal totalPrice) {
        BigDecimal deliveryPrice = new BigDecimal("3000");
        BigDecimal orderTotalPrice = totalPrice.add(deliveryPrice);
        return orderTotalPrice;

    }

    //포인트 처리
    public void DeductionPoint(Integer usesPoint, String account, RedirectAttributes redirectAttributes) {

        // 멤버 찾기
        Member user = memberMapper.findOne(account);
        //디비 멤버 포인트
        Integer userPoint = user.getUserPoint();


//        if (usesPoint > userPoint) {
//            redirectAttributes.addAttribute("error", "Insufficient points");


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
    }//DeductionPoint end

    public Member findUser(String account) {
        Member user = memberMapper.findOne(account);
        return user;
    }

    //    포인트 비동기 처리
    public boolean pointCk(String account, Integer point) {
        Member user = findUser(account);
        Integer userPoint = user.getUserPoint();

        return point <= userPoint;
    }

    //        // 포인트 결제 확인
    public String PaymentCk(Integer point, String account) {

        String message = "";
        //input 한 포인트
        BigDecimal inputPoint = new BigDecimal(point);

        //물건 합산 금액 찾기
        BigDecimal totalItemsPrice = TotalItemsPrice(account);
        BigDecimal totalPrice = calculateTotalPrice(totalItemsPrice);


        if (totalPrice.compareTo(inputPoint) < 0) {
            message = "입력하신 포인트가 결제 금액을 초과했습니다.";
        } else if (totalPrice.compareTo(inputPoint) > 0) {
            message = "포인트가 부족합니다.";

        } else {
            message = "결제 성공";
        }

        return message;
    }



//    public Member findUser(String account) {
//        return memberMapper.findOne(account);
//    }


    @Transactional
    public void createOrderWithDetails(Orders order, List<OrderDetails> orderDetailsList) {
        // 주문 생성
        log.info("Creating order: {}", order);
        orderMapper.insertOrder(order);
        Long orderId = order.getOrderId(); // 주문 생성 후 주문 ID 가져오기
        log.info("Generated orderId: {}", orderId);
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
}

