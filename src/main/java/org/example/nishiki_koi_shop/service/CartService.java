package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.CartDto;
import org.example.nishiki_koi_shop.model.entity.Fish;

public interface CartService {
    CartDto addToCart(Long userId, Fish fishId, int quantity);

}
