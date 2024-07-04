package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final CartArchiveService cartArchiveService;
    private final OrderDetailsService orderDetailsService;

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        String account = loginUser.getAccount();
        Member user = orderService.findUser(account);

        // 카트 정보
        model.addAttribute("cartItems", shoppingCartService.getCartByUser(account));

        // 총 금액
        BigDecimal totalPrice = orderService.calculateTotalPrice(account);
        model.addAttribute("totalPrice", totalPrice);

        // 포인트 넣기
        log.info("getPoint!!!!!! : {}", user.getUserPoint());
        model.addAttribute("userPoint", user.getUserPoint());

        return "html/payment";
    }

    @PostMapping("/checkout")
    public String checkout(OrderRequestDto orderRequestDto, RedirectAttributes redirectAttributes, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        String account = loginUser.getAccount();

        log.info("account: {}", account);

        Orders order = Orders.builder()
                .userAccount(loginUser.getAccount())
                .orderDate(LocalDateTime.now())
                .address1(orderRequestDto.getAddress1())
                .address2(orderRequestDto.getAddress2())
                .address3(orderRequestDto.getAddress3())
                .receiverName(orderRequestDto.getReceiverName())
                .receiverPhone(orderRequestDto.getReceiverPhone())
                .build();

        // 포인트 dto 받기
        Integer usesPoint = orderRequestDto.getUsesPoint();
        log.info("usesPoint: {}", usesPoint);

        if (usesPoint != null) {
            orderService.DeductionPoint(usesPoint, account, redirectAttributes);
        }

        orderService.createOrder(order);

        // 장바구니 항목을 아카이브로 복사
        cartArchiveService.copyCartToArchive(account);

        // 장바구니 아카이브 ID 가져오기
        List<Long> cartArchiveIds = cartArchiveService.getCartArchiveIds(account);

        // OrderDetails에 저장
        orderDetailsService.saveOrderDetails(order.getOrderId(), cartArchiveIds);

        // 장바구니 비우기
        shoppingCartService.clearCart(account);

        return "redirect:/complete";
    }

    @GetMapping("/order/history")
    public String getOrderHistory(Model model, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        String userAccount = loginUser.getAccount();

        // 데이터를 올바르게 가져오는지 로그 추가
        log.info("Fetching order history for user: {}", userAccount);

        List<Map<String, Object>> orderHistory = cartArchiveService.getOrderHistory(userAccount);

        // 가져온 데이터 로그 출력
        log.info("Order history: {}", orderHistory);

        model.addAttribute("orderHistory", orderHistory);

        return "html/order-history"; // JSP 파일 경로
    }

//    @PostMapping("/complete")
//    public String completeOrder() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userAccount = authentication.getName();
//
//        // 1. 장바구니 내용을 CartArchive로 복사
//        cartArchiveService.copyCartToArchive(userAccount);
//
//        // 2. 새로운 주문 생성
//        Orders order = Orders.builder()
//                .userAccount(userAccount)
//                .orderDate(LocalDateTime.now())
//                .address1("address1")
//                .address2("address2")
//                .address3("address3")
//                .receiverName("receiver")
//                .receiverPhone("phone")
//                .orderStatus("completed") // 추가된 필드
//                .build();
//        orderService.createOrder(order);
//
//        Long orderId = order.getOrderId();  // insert 후 ID를 가져오기 위해서 order 객체에서 가져옴
//
//        // 3. CartArchive의 ID를 가져와서 OrderDetails에 저장
//        List<Long> cartArchiveIds = cartArchiveService.getCartArchiveIds(userAccount);
//        orderDetailsService.saveOrderDetails(orderId, cartArchiveIds);
//
//        // 4. 장바구니 내용 삭제
//        shoppingCartService.clearCart(userAccount);  // clearCart 호출
//
//        return "redirect:/shop";
//    }

    @GetMapping("/complete")
    public String completeOrderGet() {
        return "html/order-history"; // 주문 완료 페이지로 리다이렉트
    }
}
