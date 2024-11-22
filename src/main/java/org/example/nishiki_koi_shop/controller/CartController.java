package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.CartDto;
import org.example.nishiki_koi_shop.model.payload.CartForm;
import org.example.nishiki_koi_shop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // Lấy thông tin giỏ hàng theo user ID
    @GetMapping()
    public ResponseEntity<CartDto> getCartByUserId(Principal principal) {
        CartDto cartDto = cartService.getCartByUserId(principal);
        return ResponseEntity.ok(cartDto);
    }
}
