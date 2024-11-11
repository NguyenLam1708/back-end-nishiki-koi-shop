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

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    public CartDto createCart(CartForm cartForm) {
        User user = userRepository.findById(cartForm.getUserId())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedDate(LocalDate.now());
        return CartDto.fromCart(cartRepository.save(cart));
    }

    @Override
    public CartDto getCartByUserId(long userId) {
        // Lấy thông tin giỏ hàng theo userId
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));
        return CartDto.fromCart(cart);
    }
    @Override
    public void deleteCart( long cartId){
        if (!cartRepository.existsById(cartId)) {
            throw new RuntimeException("Cart not found");
        }
        cartRepository.deleteById(cartId);
    }
}
