package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.CartDto;
import org.example.nishiki_koi_shop.model.payload.CartForm;
import org.example.nishiki_koi_shop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // Tạo giỏ hàng mới
    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartForm cartForm) {
        CartDto cartDto = cartService.createCart(cartForm);
        return ResponseEntity.ok(cartDto);
    }

    // Lấy thông tin giỏ hàng theo user ID
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable long userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

}
