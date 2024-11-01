package org.example.nishiki_koi_shop.model.payload;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.nishiki_koi_shop.model.entity.OrderFish;

import java.time.LocalDate;

public class OrderFIshForm {
    private long totalAmount;
    private OrderFish.Status status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private LocalDate paymentMethod;
    private LocalDate createdDate;
    private long userId;
}
