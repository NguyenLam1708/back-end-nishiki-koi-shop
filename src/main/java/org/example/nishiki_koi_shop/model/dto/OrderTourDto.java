package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.OrderTourDetail;
import java.time.LocalDate;

@Data
@Builder
public class OrderTourDto {

    private long orderTourId;
    private long totalAmount;
    private OrderTour.Status status;
    private LocalDate tourBookingDate;
    private LocalDate tourStartDate;
    private String paymentMethod;
    private LocalDate createdDate;
    private long userId;

    public static OrderTourDto from(OrderTour orderTour) {
        return OrderTourDto.builder()
                .userId(orderTour.getUser().getUserId())
                .orderTourId(orderTour.getOrderTourId())
                .totalAmount(orderTour.getTotalAmount())
                .tourBookingDate(orderTour.getTourBookingDate())
                .tourStartDate(orderTour.getTourStartDate())
                .paymentMethod(orderTour.getPaymentMethod())
                .createdDate(orderTour.getCreatedDate())
                .status(orderTour.getStatus())
                .build();
    }

}
