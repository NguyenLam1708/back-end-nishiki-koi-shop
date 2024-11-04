package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.OrderFishDetailDto;
import org.example.nishiki_koi_shop.model.entity.OrderFish;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.example.nishiki_koi_shop.model.payload.OrderFishDetailForm;

import java.util.List;

public interface OrderFishDetailService {
    List<OrderFishDetailDto> getOrderFishDetailListByOrderId(long orderFishId);
    OrderFishDetailDto getOrderFishDetailById(long orderFishDetailId);
    OrderFishDetail createOrderFishDetail(OrderFishDetailForm orderFishDetailForm, OrderFish orderFish);
    OrderFishDetail updateOrderFishDetail(OrderFishDetailForm orderFishDetailForm);
    String deleteOrderFishDetail(long orderFishDetailId);
}
