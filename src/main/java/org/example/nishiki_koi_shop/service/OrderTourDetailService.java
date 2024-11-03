package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.OrderTourDetailDto;
import org.example.nishiki_koi_shop.model.payload.OrderTourDetailForm;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;

import java.security.Principal;
import java.util.List;

public interface OrderTourDetailService {
    OrderTourDetailDto createOrderTourDetail(OrderTourDetailForm orderTourDetailForm);
    OrderTourDetailDto getOrderTourDetailById(long id, Principal principal);
    OrderTourDetailDto getOrderTourDetailById(long id);
    List<OrderTourDetailDto> getAllOrderTourDetails();
    OrderTourDetailDto updateOrderTourDetail(long id, OrderTourDetailForm orderTourDetailForm);
}
