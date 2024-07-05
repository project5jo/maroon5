package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.requestDto.OrderRequestDto;
import com.spring.mood.projectmvc.dto.responseDto.OrderResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.entity.User;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.service.OrderService;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import com.spring.mood.projectmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;


    @GetMapping("/checkout")
    public String checkout(HttpSession session,Model model){
        String account = orderService.loginUserAccount(session);
        Member user = orderService.findUser(account);

        //카트 정보
        model.addAttribute("cartItems", shoppingCartService.getCartByUser(account));

        //장바구니 아이템 총 금액
        BigDecimal totalItemsPrice = orderService.TotalItemsPrice(account);
        model.addAttribute("totalItemsPrice", totalItemsPrice);

        //배송 3,000 금액을 총 합산한 금액
        BigDecimal totalOrderPrice = orderService.calculateTotalPrice(totalItemsPrice);
        model.addAttribute("totalOrderPrice", totalOrderPrice);


        //포인트 넣기
        log.info("getPoint!!!!!! : {}",user.getUserPoint());
        model.addAttribute("userPoint", user.getUserPoint());

        return "html/payment";
    }


    @PostMapping("/checkout")
    public String checkout(OrderRequestDto orderRequestDto,  RedirectAttributes redirectAttributes, HttpSession session) {


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

        //포인트 dto 받기
        Integer usesPoint = orderRequestDto.getUsesPoint();
        log.info("usesPoint: {}",usesPoint);

        orderService.DeductionPoint(usesPoint, account,redirectAttributes);

        orderService.createOrder(order);

        return "redirect:/shop";
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



}
