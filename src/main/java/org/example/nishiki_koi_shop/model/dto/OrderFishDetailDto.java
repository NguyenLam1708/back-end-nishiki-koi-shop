package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;

@Data
@Builder
public class OrderFishDetailDto {
    private long orderFishDetailId;
    private long orderFishId;
    private long fishId;
    private int quantity;
    private long price;

    public static OrderFishDetailDto from(OrderFishDetail orderFishDetail) {
        return OrderFishDetailDto.builder()
                .orderFishDetailId(orderFishDetail.getOrderFishDetailId())
                .orderFishId(orderFishDetail.getOrderFish().getOrderFishId())
                .fishId(orderFishDetail.getFish().getFishId())
                .quantity(orderFishDetail.getQuantity())
                .price(orderFishDetail.getPrice())
                .build();
    }
}
