package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Cart;
import org.example.nishiki_koi_shop.model.entity.CartItem;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CartDto {
    private long cartId;
    private long userId;
    private LocalDate createdDate;
    private List<CartItemDto> items;

    public static CartDto fromCart(Cart cart) {
        List<CartItemDto> itemDtos = cart.getItems().stream()
                .map(CartItemDto::fromCartItem) // Chuyển đổi CartItem sang CartItemDto
                .collect(Collectors.toList());

        return CartDto.builder()
                .cartId(cart.getId())
                .userId(cart.getUser().getId())
                .createdDate(cart.getCreatedDate())
                .items(itemDtos)
                .build();
    }
}
