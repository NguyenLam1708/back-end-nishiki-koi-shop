package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.CartItem;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemDto {
    private long id;
    private long fishId;
    private int quantity;
    private BigDecimal price;
    private long cartId;

    public static CartItemDto fromCartItem(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .fishId(cartItem.getFish().getFishId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}
