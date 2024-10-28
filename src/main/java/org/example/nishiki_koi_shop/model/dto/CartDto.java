package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Cart;
import org.example.nishiki_koi_shop.model.entity.CartItem;
import org.example.nishiki_koi_shop.model.payload.CartForm;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class CartDto {
    private long id;
    private long userId;
    private LocalDate createdDate;
    private List<CartItem> items;

    public static CartDto fromCart(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .createdDate(cart.getCreatedDate())
                .items(cart.getItems())
                .build();
    }
}
