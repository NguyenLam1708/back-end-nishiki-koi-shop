package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;

import java.util.List;

public interface CartItemService {
    CartItemDto addCartItem(CartItemForm cartItemForm);
    void updateCartItem(Long cartItemId, CartItemForm cartItemForm);
    void deleteCartItem(Long cartItemId);
    List<CartItemDto> getCartItems(Long cartId);
    void deleteAllCartItems(long cartId);

}
