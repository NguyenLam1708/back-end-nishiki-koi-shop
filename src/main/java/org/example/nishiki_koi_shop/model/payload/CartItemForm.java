package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.CartItem;

@Data
@Builder
public class CartItemForm {
    private long id;
    private long cartId;
    private long fishId;
    private int quantity;
    private long price;

    public static CartItemForm fromCartItem(CartItem cartItem) {
        return CartItemForm.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .fishId(cartItem.getFish().getFishId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .build();

    }

}
