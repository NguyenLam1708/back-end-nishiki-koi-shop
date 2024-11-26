package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderFish;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class OrderFishDetailDto {
    private long orderFishDetailId;
    private long orderFishId;
    private List<Long> fishId;
    private List<Integer> quantity;
    private List<BigDecimal> price;

    public static OrderFishDetailDto from(OrderFish orderFish, List<OrderFishDetail> orderFishDetails) {
        List<Long> fishIds = orderFishDetails.stream()
                .map(detail -> detail.getFish().getFishId())
                .toList();
        List<Integer> totalQuantity = orderFishDetails.stream()
                .map(OrderFishDetail::getQuantity)
                .toList();

        List<BigDecimal> totalPrice = orderFishDetails.stream()
                .map(OrderFishDetail::getPrice)
                .toList();

        return OrderFishDetailDto.builder()
                .orderFishDetailId(orderFishDetails.getFirst().getOrderFishDetailId())
                .orderFishId(orderFishDetails.getFirst().getOrderFish().getOrderFishId())
                .fishId(fishIds)
                .quantity(totalQuantity)
                .price(totalPrice)
                .build();
    }
}
