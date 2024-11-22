package org.example.nishiki_koi_shop.controller;

import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;
import org.example.nishiki_koi_shop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart/items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addCartItem(@ModelAttribute CartItemForm cartItemForm, Principal principal) {
        return new ResponseEntity<>(cartItemService.addCartItem(cartItemForm, principal), HttpStatus.CREATED);
    }

    @PostMapping("/remove")
    public ResponseEntity<CartItemDto> removeCartItem(@ModelAttribute CartItemForm cartItemForm, Principal principal) {
        return new ResponseEntity<>(cartItemService.removeCartItem(cartItemForm, principal), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable long id, Principal principal) {
        cartItemService.deleteCartItem(id, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<CartItemDto>> getCartItems(Principal principal) {
        List<CartItemDto> items = cartItemService.getCartItems(principal);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
