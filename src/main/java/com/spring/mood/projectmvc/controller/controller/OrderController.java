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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        String account = loginUser.getAccount();
        Member user = orderService.findUser(account);

        //카트 정보
        model.addAttribute("cartItems", shoppingCartService.getCartByUser(account));

        //총 금액
        BigDecimal totalPrice = orderService.calculateTotalPrice(account);
        model.addAttribute("totalPrice", totalPrice);


        //포인트 넣기
        log.info("getPoint!!!!!! : {}",user.getUserPoint());
        model.addAttribute("userPoint", user.getUserPoint());

        return "html/payment";
    }


    @PostMapping("/checkout")
    public String checkout(OrderRequestDto orderRequestDto,  RedirectAttributes redirectAttributes, HttpSession session) {


        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
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

        //포인트 dto 받기
        Integer usesPoint = orderRequestDto.getUsesPoint();
        log.info("usesPoint: {}",usesPoint);

        orderService.DeductionPoint(usesPoint, account,redirectAttributes);

        orderService.createOrder(order);

//        OrderResponseDto orderResponseDto = OrderResponseDto.fromEntity(order);
//        BigDecimal totalPrice = orderService.calculateTotalPrice(orderRequestDto.getUserAccount());
//        User user = userService.getUserByAccount(orderRequestDto.getUserAccount());


//        model.addAttribute("order", orderResponseDto);
//        model.addAttribute("cartItems", shoppingCartService.getCartByUser(orderRequestDto.getUserAccount()));
//        model.addAttribute("totalPrice", totalPrice);
//        model.addAttribute("userPoint", user.getUserPoint());

        return "redirect:/shop";
    }

}
