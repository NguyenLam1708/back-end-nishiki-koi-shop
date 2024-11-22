package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;

import java.security.Principal;
import java.util.List;

public interface CartItemService {
    // them so luong ca
    CartItemDto addCartItem(CartItemForm cartItemForm, Principal principal);
    // giam so luong ca
    CartItemDto removeCartItem(CartItemForm cartItemForm, Principal principal);
    // xoa ca ra khoi gio hang
    void deleteCartItem(Principal principal);
    // lay ra toan bo ca trong gio hang
    List<CartItemDto> getCartItems(Principal principal);
}
