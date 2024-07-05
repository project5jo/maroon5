package com.spring.mood.projectmvc.service;

import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.entity.Member;
import com.spring.mood.projectmvc.entity.Orders;
import com.spring.mood.projectmvc.mapper.MemberMapper;
import com.spring.mood.projectmvc.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderMapper orderMapper;
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
}
