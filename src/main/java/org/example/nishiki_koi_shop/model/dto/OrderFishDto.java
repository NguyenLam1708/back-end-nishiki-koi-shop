package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.OrderFish;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderFishDto {
    private long orderFishId;
    private long totalAmount;
    private OrderFish.Status status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String paymentMethod;
    private LocalDate createdDate;
    private long userId;
    private List<OrderFishDetailDto> orderFishDetailDtoList;

    public static OrderFishDto from(OrderFish orderFish) {
        return OrderFishDto.builder()
                .orderFishId(orderFish.getOrderFishId())
                .totalAmount(orderFish.getTotalAmount())
                .status(orderFish.getStatus())
                .orderDate(orderFish.getOrderDate())
                .deliveryDate(orderFish.getDeliveryDate())
                .paymentMethod(orderFish.getPaymentMethod())
                .createdDate(orderFish.getCreatedDate())
                .userId(orderFish.getUser().getId())
                .orderFishDetailDtoList(orderFish.getOrderFishDetail().stream()
                        .map(OrderFishDetailDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
