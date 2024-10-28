package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.OrderTourDetail;

import java.time.LocalDate;

@Data
@Builder
public class OrderTourForm {
    private long orderTourId;
    private long userId;
    private long totalAmount;
    private OrderTour.Status status;
    private LocalDate tourBookingDate;
    private LocalDate tourStartDate;
    private String paymentMethod;
    private LocalDate createdDate;
}
