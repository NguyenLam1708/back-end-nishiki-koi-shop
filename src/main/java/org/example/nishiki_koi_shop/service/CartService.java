package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.CartDto;
import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.payload.CartForm;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartService {
    // Tạo giỏ hàng mới
    CartDto createCart(CartForm cartForm);

    // Lấy thông tin giỏ hàng theo user ID
    CartDto getCartByUserId(long userId);

    void deleteCart( long cartId);
}
