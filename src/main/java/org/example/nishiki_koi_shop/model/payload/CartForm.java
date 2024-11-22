package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Cart;
import org.example.nishiki_koi_shop.model.entity.CartItem;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CartForm {
    private long userId;
    private List<CartItemForm> items;

}
