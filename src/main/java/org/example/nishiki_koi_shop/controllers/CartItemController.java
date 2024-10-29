package org.example.nishiki_koi_shop.controllers;

import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;
import org.example.nishiki_koi_shop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart/items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemForm cartItemForm) {
        CartItemDto addedItem = cartItemService.addCartItem(cartItemForm);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateCartItem(@PathVariable Long id, @RequestBody CartItemForm cartItemForm) {
        cartItemService.updateCartItem(id, cartItemForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable Long cartId) {
        List<CartItemDto> items = cartItemService.getCartItems(cartId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    // Xóa toàn bộ giỏ hàng
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> deleteAllCartItems(@PathVariable long cartId) {
        cartItemService.deleteAllCartItems(cartId);
        return ResponseEntity.noContent().build();
    }

}
