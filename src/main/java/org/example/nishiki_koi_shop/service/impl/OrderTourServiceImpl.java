package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;
import org.example.nishiki_koi_shop.repository.OrderTourRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.OrderTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderTourServiceImpl implements OrderTourService {

    @Autowired
    private OrderTourRepository orderTourRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderTourDto createOrderTour(OrderTourForm orderTourForm) {
        User user = userRepository.findById(orderTourForm.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderTour orderTour = OrderTour.builder()
                .totalAmount(orderTourForm.getTotalAmount())
                .status(orderTourForm.getStatus())
                .tourBookingDate(orderTourForm.getTourBookingDate())
                .tourStartDate(orderTourForm.getTourStartDate())
                .paymentMethod(orderTourForm.getPaymentMethod())
                .createdDate(orderTourForm.getCreatedDate())
                .user(user)
                .build();

        OrderTour savedOrderTour = orderTourRepository.save(orderTour);
        return OrderTourDto.from(savedOrderTour);
    }

    @Override
    public OrderTourDto getOrderTourById(long id) {
        OrderTour orderTour = orderTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderTourDto.from(orderTour);
    }

    @Override
    public List<OrderTourDto> getAllOrderTours() {
        return orderTourRepository.findAll().stream()
                .map(OrderTourDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public OrderTourDto updateOrderTour(long id, OrderTourForm orderTourForm) {
        OrderTour orderTour = orderTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        User user = userRepository.findById(orderTourForm.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        orderTour.setTotalAmount(orderTourForm.getTotalAmount());
        orderTour.setStatus(orderTourForm.getStatus());
        orderTour.setTourBookingDate(orderTourForm.getTourBookingDate());
        orderTour.setTourStartDate(orderTourForm.getTourStartDate());
        orderTour.setPaymentMethod(orderTourForm.getPaymentMethod());
        orderTour.setCreatedDate(orderTourForm.getCreatedDate());
        orderTour.setUser(user);

        OrderTour updatedOrderTour = orderTourRepository.save(orderTour);
        return OrderTourDto.from(updatedOrderTour);
    }
    @Override
    public List<OrderTourDto> getOrderToursByUserId(long userId) {
        List<OrderTour> orders = orderTourRepository.findByUserId(userId);
        return orders.stream().map(OrderTourDto::from).collect(Collectors.toList());
    }
}
