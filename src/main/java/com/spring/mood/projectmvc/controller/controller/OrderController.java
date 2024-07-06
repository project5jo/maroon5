package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
        int totalPrice = orderService.calculateTotalPrice(account);
        model.addAttribute("totalPrice", totalPrice);

        // 포인트 넣기
        log.info("getPoint!!!!!! : {}", user.getUserPoint());
        model.addAttribute("userPoint", user.getUserPoint());

        return "html/payment";
    }

    @PostMapping("/checkout")
    public String checkout(OrderRequestDto orderRequestDto, RedirectAttributes redirectAttributes, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        System.out.println("orderRequestDto = " + orderRequestDto.getAddress3());
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

        log.info("Created Order ID: {}", order.getOrderId());

        if (order.getOrderId() != null) {
        // 장바구니 항목을 아카이브로 복사
        cartArchiveService.copyCartToArchive(account, order.getOrderId());

        // 장바구니 아카이브 ID 가져오기
        List<Long> cartArchiveIds = cartArchiveService.getCartArchiveIds(account);

        // OrderDetails에 저장
        orderDetailsService.saveOrderDetails(order.getOrderId(), cartArchiveIds);

        // 장바구니 비우기
        shoppingCartService.clearCart(account);
        } else {
            log.error("Order ID is null. Order creation failed.");
            redirectAttributes.addFlashAttribute("error", "Order creation failed.");
            return "redirect:/checkout";
        }
        return "redirect:/complete";
    }

    @GetMapping("/complete")
    public String getOrderHistory(Model model, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        String userAccount = loginUser.getAccount();
        log.info("Fetching order history for user: {}", userAccount);

        Map<String, List<Map<String, Object>>> groupedOrderHistory = orderService.getGroupedOrderHistory(userAccount);
        log.info("Grouped order history: {}", groupedOrderHistory);

        model.addAttribute("groupedOrderHistory", groupedOrderHistory);

        return "html/order-history";
    }


    @GetMapping("/{orderId}/details")
    public List<OrderDetailResponseDto> getOrderDetails(@PathVariable Long orderId) {
        return orderService.getOrderDetailsByOrderId(orderId);
    }

}
