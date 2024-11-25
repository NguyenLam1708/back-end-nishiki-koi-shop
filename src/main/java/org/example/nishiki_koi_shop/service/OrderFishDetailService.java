package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.OrderFishDetailDto;
import org.example.nishiki_koi_shop.model.payload.OrderFishDetailForm;

import java.security.Principal;
import java.util.List;

public interface OrderFishDetailService {
    List<OrderFishDetailDto> getOrderFishDetailByOrderFishId(long orderFishId);
}
