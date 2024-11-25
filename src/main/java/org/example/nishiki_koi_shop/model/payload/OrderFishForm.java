package org.example.nishiki_koi_shop.model.payload;

import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderFish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderFishForm {
    private LocalDate deliveryDate;
    private String paymentMethod;
    private List<Long> cartItemIds;
    private String shippingAddress;
    private OrderFish.Status status;
}
