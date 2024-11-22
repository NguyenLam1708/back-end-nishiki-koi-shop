package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.CartItem;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemForm {
    private long fishId;
    private int quantity;

}
