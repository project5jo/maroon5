package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/cart")
    public String addToCart(@RequestParam("itemId") Long itemId,
                            @RequestParam("itemPrice") BigDecimal itemPrice,
                            @RequestParam("quantity") int quantity,
                            RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAccount = authentication.getName();

        try {
            shoppingCartService.addToCart(itemId, itemPrice, quantity, userAccount);
            redirectAttributes.addFlashAttribute("successMessage", "장바구니에 상품이 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "장바구니에 상품을 추가하는 중 오류가 발생했습니다.");
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAccount = authentication.getName();

        List<ShoppingCartResponseDto> cartItems = shoppingCartService.getCartByUser(userAccount);
        model.addAttribute("cartItems", cartItems);

        BigDecimal cartTotalPrice = cartItems.stream()
                .map(ShoppingCartResponseDto::getCartTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("cartTotalPrice", cartTotalPrice);

        // Logging
        log.info("Cart Items: {}", cartItems);
        log.info("Cart Total Price: {}", cartTotalPrice);

        return "html/shop-cart";
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public String updateCart(@RequestBody Map<String, Object> requestData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAccount = authentication.getName();

        List<Map<String, Object>> cartItems = (List<Map<String, Object>>) requestData.get("cartItems");

        try {
            for (Map<String, Object> item : cartItems) {
                Long itemId = Long.valueOf((String) item.get("itemId"));
                int quantity = Integer.parseInt((String) item.get("quantity"));
                BigDecimal totalPrice = new BigDecimal((String) item.get("totalPrice"));
                shoppingCartService.updateCartItem(userAccount, itemId, quantity, totalPrice);
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("itemId") Long itemId, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userAccount = authentication.getName();

        try {
            shoppingCartService.removeFromCart(itemId, userAccount);
            redirectAttributes.addFlashAttribute("successMessage", "장바구니에서 상품이 성공적으로 제거되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "장바구니에서 상품을 제거하는 중 오류가 발생했습니다.");
        }
        return "redirect:/cart";
    }
}
