package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderDetailResponseDto2;
import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.mapper.OrderMapper;
import com.spring.mood.projectmvc.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    private final OrderMapper orderMapper;

    @GetMapping("/checkout")

    public String checkout(HttpSession session, Model model) {

            String account = orderService.loginUserAccount(session);
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        Member user = orderService.findUser(account);

        // 카트 정보
        model.addAttribute("cartItems", shoppingCartService.getCartByUser(account));


        //장바구니 아이템 총 금액
        int totalItemsPrice = orderService.TotalItemsPrice(account);
        model.addAttribute("totalItemsPrice", totalItemsPrice);

        //배송 3,000 금액을 총 합산한 금액
        int totalOrderPrice = orderService.calculateTotalPrice(totalItemsPrice);
        model.addAttribute("totalOrderPrice", totalOrderPrice);
        // 총 금액
//        int totalPrice = orderService.calculateTotalPrice(account);
//        model.addAttribute("totalPrice", totalPrice);


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
            String account = orderService.loginUserAccount(session);

        log.info("account: {}", account);

        Orders order = Orders.builder()
                .userAccount(account)
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

        Map<String, Map<String, Object>> groupedOrderHistory = orderService.getGroupedOrderHistory(userAccount);
        log.info("Grouped order history: {}", groupedOrderHistory);

        model.addAttribute("groupedOrderHistory", groupedOrderHistory);

        return "html/order-history";
    }


    @GetMapping("/{orderId}/details")
    public List<OrderDetailResponseDto> getOrderDetails(@PathVariable Long orderId) {
        return orderService.getOrderDetailsByOrderId(orderId);
    }


    // 비동기 처리 : 유저 포인트 값 처리
    @ResponseBody
    @GetMapping("/checkPoint")
    public ResponseEntity<?> checkPoint(HttpSession session,@RequestParam(required = false) Integer point){
        String account = orderService.loginUserAccount(session);

        if (point == null) {
            // point가 null일 때 처리
            return ResponseEntity.badRequest().body("point 매개변수가 필요합니다.");
        }

        //포인트 체크
        boolean flag = orderService.pointCk(account, point);
        log.info("포인트 확인 결과: {}",point);
        log.info("포인트 flag: {}",flag);
        return ResponseEntity.ok().body(flag);
    }

    //비동기 처리 : 포인트 결제 확인
    @ResponseBody
    @GetMapping("/payPoint")
    public ResponseEntity<?> pointPaymentCk(HttpSession session,Integer point){
        String account = orderService.loginUserAccount(session);
        String message = orderService.PaymentCk(point, account);

        log.info("payPoint message : {}",message);
        //message 를 Json 형태로 변경
        HashMap<String, String> response = new HashMap<>();
        response.put("message",message);
        log.info("payPoint response : {}",response);

        return ResponseEntity.ok().body(response);
    }
    // / // //// /// / / // /// // / /// / / // // / / / / ///
    // 새로운 매핑 추가: /order-details 이거 활용해서 주문상세정보 만들면됨
    // OrderRequestDto 쓰면될듯
    // / // / / / // ///// /// / ///
    @GetMapping("/order-details")
    public String getOrderDetails() {
        return "html/order-detail";
    }
    @PostMapping("/order-details")
    public ResponseEntity<?> getOrderDetails(@RequestBody OrderDetailResponseDto2 request, HttpSession session) {
                SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.badRequest().body("point 매개변수가 필요합니다.");
        }
        List<OrderDetailResponseDto> orderDetailsByOrderId = orderMapper.getOrderDetailsByOrderId(request.getOrderId().longValue());
        System.out.println("orderDetailsByOrderId = " + orderDetailsByOrderId);
        session.setAttribute("orderDetails", request);
        session.setAttribute("cartItems", orderDetailsByOrderId);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/order-details")).build();
    }


}
