package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.nishiki_koi_shop.model.dto.CartDto;
import org.example.nishiki_koi_shop.model.entity.Cart;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.CartForm;
import org.example.nishiki_koi_shop.repository.CartItemRepository;
import org.example.nishiki_koi_shop.repository.CartRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.CartService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public CartDto getCartByUserId(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));
        return CartDto.fromCart(cart);
    }
}
