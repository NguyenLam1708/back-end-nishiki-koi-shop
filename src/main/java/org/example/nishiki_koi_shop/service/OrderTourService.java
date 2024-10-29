package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;

import java.util.List;

public interface OrderTourService {
    OrderTourDto createOrderTour(OrderTourForm orderTourForm);
    OrderTourDto getOrderTourById(long id);
    List<OrderTourDto> getAllOrderTours();
    OrderTourDto updateOrderTour(long id, OrderTourForm orderTourForm);
    List<OrderTourDto> getOrderToursByUserId(long userId);
}
