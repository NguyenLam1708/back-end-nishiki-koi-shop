package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.entity.Cart;
import org.example.nishiki_koi_shop.model.entity.CartItem;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;
import org.example.nishiki_koi_shop.repository.CartItemRepository;
import org.example.nishiki_koi_shop.repository.CartRepository;
import org.example.nishiki_koi_shop.repository.FishRepository;
import org.example.nishiki_koi_shop.service.CartItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Log4j2
@Service
public class CartItemServiceImpl implements CartItemService {

    private final FishRepository fishRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItemDto addCartItem(CartItemForm cartItemForm) {
        // Tìm giỏ hàng của người dùng
        Cart cart = cartRepository.findByUserId(cartItemForm.getCartId())
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // Tìm cá theo ID
        Fish fish = fishRepository.findById(cartItemForm.getFishId())
                .orElseThrow(() -> new RuntimeException("Cá không tồn tại"));

        // Kiểm tra số lượng cá còn trong kho
        if (cartItemForm.getQuantity() > fish.getQuantity()) {
            throw new RuntimeException("Số lượng yêu cầu vượt quá số lượng tồn kho của cá");
        }

        // Kiểm tra xem cá đã có trong giỏ hàng chưa
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getFish().getFishId() == fish.getFishId())
                .findFirst();

        CartItem newItem;

        if (existingItem.isPresent()) {
            // Nếu có, cập nhật số lượng
            CartItem itemToUpdate = existingItem.get();

            // Tính toán số lượng mới và cập nhật
            int newQuantity = itemToUpdate.getQuantity() + cartItemForm.getQuantity();
            if (newQuantity > fish.getQuantity()) {
                throw new RuntimeException("Số lượng yêu cầu vượt quá số lượng tồn kho của cá");
            }
            itemToUpdate.setQuantity(newQuantity);

            // Cập nhật giá mới dựa trên số lượng mới
            itemToUpdate.setPrice(fish.getPrice().multiply(BigDecimal.valueOf(newQuantity)));

            cartItemRepository.save(itemToUpdate);
            return CartItemDto.fromCartItem(itemToUpdate); // Trả về item đã cập nhật
        } else {
            // Nếu chưa có, tạo mới CartItem
            newItem = CartItem.builder()
                    .fish(fish)
                    .quantity(cartItemForm.getQuantity())
                    .price(fish.getPrice().multiply(BigDecimal.valueOf(cartItemForm.getQuantity()))) // Tính giá dựa trên số lượng
                    .cart(cart)
                    .build();
            cartItemRepository.save(newItem);
        }

        return CartItemDto.fromCartItem(newItem);
    }

    @Override
    public CartItemDto updateCartItem(Long cartItemId, CartItemForm cartItemForm) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem không tồn tại"));
        Fish fish = cartItem.getFish();

        cartItem.setQuantity(cartItemForm.getQuantity());
        cartItem.setPrice(fish.getPrice().multiply(BigDecimal.valueOf(cartItemForm.getQuantity())));
        cartItemRepository.save(cartItem);
        return CartItemDto.fromCartItem(cartItem);
    }
    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem không tồn tại"));

        cartItemRepository.delete(cartItem);
    }
    @Override
    public List<CartItemDto> getCartItems(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        return cart.getItems().stream()
                .map(CartItemDto::fromCartItem)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteAllCartItems(long cartId) {
        // Xóa toàn bộ giỏ hàng
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));
        cartItemRepository.deleteAll(cart.getItems()); // Xóa tất cả mặt hàng
        cart.getItems().clear(); // Làm sạch danh sách
        cartRepository.save(cart); // Lưu giỏ hàng để cập nhật
    }
}
