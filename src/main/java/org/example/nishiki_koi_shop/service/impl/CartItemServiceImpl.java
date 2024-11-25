package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.entity.Cart;
import org.example.nishiki_koi_shop.model.entity.CartItem;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;
import org.example.nishiki_koi_shop.repository.CartItemRepository;
import org.example.nishiki_koi_shop.repository.CartRepository;
import org.example.nishiki_koi_shop.repository.FishRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.CartItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
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
    private final UserRepository userRepository;

    @Override
    public CartItemDto addCartItem(CartItemForm cartItemForm, Principal principal) {
        // Tìm giỏ hàng của người dùng
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // Tìm cá theo ID
        Fish fish = fishRepository.findById(cartItemForm.getFishId())
                .orElseThrow(() -> new RuntimeException("Cá không tồn tại"));

        // Kiểm tra số lượng tồn kho
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

            // Kiểm tra số lượng mới có vượt quá tồn kho không
            if (newQuantity > fish.getQuantity()) {
                throw new RuntimeException("Số lượng yêu cầu vượt quá số lượng tồn kho của cá");
            }

            itemToUpdate.setQuantity(newQuantity);

            // Cập nhật giá mới dựa trên số lượng mới
            itemToUpdate.setPrice(fish.getPrice().multiply(BigDecimal.valueOf(newQuantity)));

            cartItemRepository.save(itemToUpdate);
            return CartItemDto.fromCartItem(itemToUpdate); // Trả về item đã cập nhật
        } else {
            // Nếu chưa có, tạo mới CartItem và kiểm tra số lượng trước khi thêm
            newItem = CartItem.builder()
                    .fish(fish)
                    .quantity(cartItemForm.getQuantity())
                    .price(fish.getPrice().multiply(BigDecimal.valueOf(cartItemForm.getQuantity()))) // Tính giá dựa trên số lượng
                    .cart(cart)
                    .build();

            // Kiểm tra số lượng tồn kho trước khi thêm vào giỏ hàng
            if (newItem.getQuantity() > fish.getQuantity()) {
                throw new RuntimeException("Số lượng yêu cầu vượt quá số lượng tồn kho của cá");
            }

            cartItemRepository.save(newItem);
        }

        return CartItemDto.fromCartItem(newItem);
    }


    @Override
    public CartItemDto removeCartItem(CartItemForm cartItemForm, Principal principal) {
        // Tìm người dùng
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Tìm giỏ hàng
        Cart cart = cartRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // Tìm cá theo ID
        Fish fish = fishRepository.findById(cartItemForm.getFishId())
                .orElseThrow(() -> new RuntimeException("Cá không tồn tại"));

        // Tìm sản phẩm trong giỏ hàng
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getFish().getFishId() == (fish.getFishId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem itemToUpdate = existingItem.get();

            // Tính toán số lượng mới
            int newQuantity = itemToUpdate.getQuantity() - cartItemForm.getQuantity();

            if (newQuantity > 0) {
                // Cập nhật số lượng và giá nếu số lượng mới > 0
                itemToUpdate.setQuantity(newQuantity);
                itemToUpdate.setPrice(fish.getPrice().multiply(BigDecimal.valueOf(newQuantity)));

                cartItemRepository.save(itemToUpdate);
                return CartItemDto.fromCartItem(itemToUpdate);
            } else {
                throw new RuntimeException("Số lượng không hợp lệ");
            }
        } else {
            throw new RuntimeException("Sản phẩm không tồn tại trong giỏ hàng");
        }
    }


    @Override
    public void deleteCartItem(long id, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemRepository.findCartItemByIdIs(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItemDto> getCartItems(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cart.getItems().stream()
                .map(CartItemDto::fromCartItem)
                .collect(Collectors.toList());
    }

}
