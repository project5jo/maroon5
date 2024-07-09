package com.spring.mood.projectmvc.controller.controller;

import com.spring.mood.projectmvc.dto.responseDto.ShoppingCartResponseDto;
import com.spring.mood.projectmvc.dto.responseDto.SignInUserInfoDTO;
import com.spring.mood.projectmvc.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/cart")
    public String addToCart(@RequestParam("itemId") Long itemId,
                            @RequestParam("itemPrice") int itemPrice,
                            @RequestParam("quantity") int quantity,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        String userAccount = loginUser.getAccount();

        try {
            shoppingCartService.addToCart(itemId, itemPrice, quantity, userAccount);
            redirectAttributes.addFlashAttribute("successMessage", "장바구니에 상품이 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "장바구니에 상품을 추가하는 중 오류가 발생했습니다.");
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");

        List<ShoppingCartResponseDto> cartItems = shoppingCartService.getCartByUser(loginUser.getAccount());
        model.addAttribute("cartItems", cartItems);

        int cartTotalPrice = cartItems.stream()
                .mapToInt(ShoppingCartResponseDto::getCartTotalPrice)
                .sum();

        model.addAttribute("cartTotalPrice", cartTotalPrice);

        // Logging
        log.info("Cart Items: {}", cartItems);
        log.info("Cart Total Price: {}", cartTotalPrice);

        return "html/shop-cart";
    }


    /**
     * 확인 요망
     * @param requestData
     * @param session
     * @return
     */
    @PostMapping("/cart/update")
    @ResponseBody
    public String updateCart(@RequestBody Map<String, Object> requestData, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        String userAccount = loginUser.getAccount();;

        List<Map<String, Object>> cartItems = (List<Map<String, Object>>) requestData.get("cartItems");



        try {
            for (Map<String, Object> item : cartItems) {
                Long itemId = Long.valueOf((String) item.get("itemId"));
                int quantity = Integer.parseInt((String) item.get("quantity"));
                int totalPrice = Integer.parseInt((String) item.get("totalPrice"));
                shoppingCartService.updateCartItem(userAccount, itemId, quantity, totalPrice);
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("itemId") Long itemId, RedirectAttributes redirectAttributes, HttpSession session) {
        SignInUserInfoDTO loginUser = (SignInUserInfoDTO) session.getAttribute("loginUser");
        String userAccount = loginUser.getAccount();
        try {
            shoppingCartService.removeFromCart(itemId, userAccount);
            redirectAttributes.addFlashAttribute("successMessage", "장바구니에서 상품이 성공적으로 제거되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "장바구니에서 상품을 제거하는 중 오류가 발생했습니다.");
        }
        return "redirect:/cart";
    }
}
