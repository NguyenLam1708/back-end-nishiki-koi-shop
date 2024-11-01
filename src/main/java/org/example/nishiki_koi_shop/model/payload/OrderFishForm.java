package org.example.nishiki_koi_shop.model.payload;

import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderFish;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderFishForm {
    private long totalAmount;
    private OrderFish.Status status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String paymentMethod;
    private LocalDate createdDate;
    private long userId;
    private List<OrderFishDetailForm> orderFishDetailFormList = new ArrayList<>();
}
