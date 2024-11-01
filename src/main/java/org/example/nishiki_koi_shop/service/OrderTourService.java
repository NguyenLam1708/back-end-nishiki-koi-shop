package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;

import java.security.Principal;
import java.util.List;

public interface OrderTourService {
    // người dung tao order tour
    OrderTourDto createOrderTour(OrderTourForm orderTourForm, Principal principal);
    // xem chi tiet order tour voi nguoi dung dang dang nhap
    OrderTourDto getOrderTourById(long id, Principal principal);
    // Xem lich su don hang cua nguoi dung dang dang nhap
    List<OrderTourDto> getOrderToursByUserId(long id, Principal principal);
    // Xem tat ca cac order tour
    List<OrderTourDto> getAllOrderTours();
    OrderTourDto getOrderTourById(long id);
    // update order tour
    OrderTourDto updateOrderTour(long id, OrderTourForm orderTourForm);
}
