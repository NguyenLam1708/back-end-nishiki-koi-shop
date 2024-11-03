package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;
import org.example.nishiki_koi_shop.repository.OrderTourRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.OrderTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    public OrderTourDto createOrderTour(OrderTourForm orderTourForm, Principal principal) {
        long currentUserId = getUserIdFromPrincipal(principal);

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderTour orderTour = OrderTour.builder()
                .totalAmount(orderTourForm.getTotalAmount())
                .status(orderTourForm.getStatus())
                .tourBookingDate(orderTourForm.getTourBookingDate())
                .tourStartDate(orderTourForm.getTourStartDate())
                .paymentMethod(orderTourForm.getPaymentMethod())
                .createdDate(orderTourForm.getCreatedDate())
                .user(user) // Gán người dùng hiện tại vào đơn hàng
                .build();

        OrderTour savedOrderTour = orderTourRepository.save(orderTour);
        return OrderTourDto.from(savedOrderTour);
    }

    @Override
    public OrderTourDto getOrderTourById(long id, Principal principal) {
        OrderTour orderTour = orderTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Lấy userId từ principal (giả sử bạn có phương thức để lấy userId từ tên người dùng)
        long currentUserId = getUserIdFromPrincipal(principal);

        // Kiểm tra quyền truy cập
        if (orderTour.getUser().getId() != currentUserId) {
            throw new SecurityException("Access denied. You are not authorized to view this order.");
        }

        return OrderTourDto.from(orderTour);
    }

    @Override
    public List<OrderTourDto> getAllOrderTours() {
        return orderTourRepository.findAll().stream()
                .map(OrderTourDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public OrderTourDto getOrderTourById(long id) {
        OrderTour orderTour = orderTourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderTourDto.from(orderTour);
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
    public List<OrderTourDto> getOrderToursByUserId(long id, Principal principal) {
        // Lấy userId từ principal
        long currentUserId = getUserIdFromPrincipal(principal);

        // Kiểm tra quyền truy cập
        if (id != currentUserId) {
            throw new SecurityException("Access denied. You are not authorized to view these orders.");
        }

        List<OrderTour> orders = orderTourRepository.findByUserId(id);
        return orders.stream().map(OrderTourDto::from).collect(Collectors.toList());
    }
    private long getUserIdFromPrincipal(Principal principal) {
//        String email = principal.getName();
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        String email = au.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
