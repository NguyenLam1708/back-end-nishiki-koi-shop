package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.OrderFishDto;
import org.example.nishiki_koi_shop.model.payload.OrderFishForm;

import java.util.List;

public interface OrderFishService {
    List<OrderFishDto> getAllOrderFish();
    OrderFishDto getOrderFishByOrderFishId(Long orderFishId);
    List<OrderFishDto> getOrderFishByUserId(Long userId);
    OrderFishDto createOrderFish(OrderFishForm orderFishForm);
    OrderFishDto updateOrderFish(long id, OrderFishForm orderFishForm);
    String deleteOrderFish(Long orderFishId);
}
