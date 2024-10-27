package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.OrderTourDetail;

@Data
@Builder
public class OrderTourDetailDto {
    private long orderTourDetailId;
    private Integer numberOfPeople;
    private long price;
    private long tourId;
    private OrderTourDto orderTour;

    // Phương thức này nên chuyển đổi từ OrderTourDetail, không phải OrderTour
    public static OrderTourDetailDto from(OrderTourDetail orderTourDetail) {
        return OrderTourDetailDto.builder()
                .orderTourDetailId(orderTourDetail.getOrderTourDetailId())
                .numberOfPeople(orderTourDetail.getNumberOfPeople())
                .price(orderTourDetail.getPrice())
                .tourId(orderTourDetail.getTour().getTourId()) // Lấy tourId từ đối tượng Tour
                .orderTour(OrderTourDto.from(orderTourDetail.getOrderTour())) // Chuyển đổi OrderTour thành OrderTourDto
                .build();
    }
}
