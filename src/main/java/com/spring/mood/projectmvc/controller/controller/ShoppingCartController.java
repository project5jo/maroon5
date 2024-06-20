//package com.spring.mood.projectmvc.controller.controller;
//
//import com.spring.mood.projectmvc.dto.requestDto.ShoppingCartRequestDto;
//import com.spring.mood.projectmvc.dto.responseDto.OrderDetailResponseDto;
//import com.spring.mood.projectmvc.entity.ShoppingCart;
//import com.spring.mood.projectmvc.service.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//@RequestMapping("/cart")
//public class ShoppingCartController {
//
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addItemToCart(@RequestParam String userAccount, @RequestParam int shopItemId, @RequestParam int count) {
//        shoppingCartService.addItemToCart(userAccount, shopItemId, count);
//        return ResponseEntity.ok("Item added to cart");
//    }
//
//    @GetMapping("/items")
//    public List<ShoppingCart> getCartItems(@RequestParam String userAccount) {
//        return shoppingCartService.getCartItems(userAccount);
//    }
//
//    @PostMapping("/remove")
//    public ResponseEntity<String> removeItemFromCart(@RequestParam String userAccount, @RequestParam int shopItemId) {
//        shoppingCartService.removeItemFromCart(userAccount, shopItemId);
//        return ResponseEntity.ok("Item removed from cart");
//    }
//}
