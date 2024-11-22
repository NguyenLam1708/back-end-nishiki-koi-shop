package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.CartItem;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.payload.CartItemForm;
import org.example.nishiki_koi_shop.repository.FishRepository;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemDto {
    private long id;
    private long fishId;
    private String name;
    private String image;
    private int quantity;
    private int stored;
    private BigDecimal price;
    private long cartId;

    public static CartItemDto fromCartItem(CartItem cartItem) {


        return CartItemDto.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .fishId(cartItem.getFish().getFishId())
                .name(cartItem.getFish().getName())
                .image(cartItem.getFish().getImage())
                .quantity(cartItem.getQuantity() > cartItem.getFish().getQuantity() ? cartItem.getFish().getQuantity() : cartItem.getQuantity())
                .stored(cartItem.getFish().getQuantity())
                .price(cartItem.getPrice())
                .build();
    }
}
