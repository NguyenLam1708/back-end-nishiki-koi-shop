package org.example.nishiki_koi_shop.service.impl;

import org.example.nishiki_koi_shop.model.dto.OrderFishDto;
import org.example.nishiki_koi_shop.model.entity.OrderFish;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.example.nishiki_koi_shop.model.payload.OrderFishDetailForm;
import org.example.nishiki_koi_shop.model.payload.OrderFishForm;
import org.example.nishiki_koi_shop.repository.OrderFishRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.OrderFishDetailService;
import org.example.nishiki_koi_shop.service.OrderFishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFishServiceImpl implements OrderFishService {
    @Autowired
    private OrderFishRepository orderFishRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderFishDetailService orderFishDetailService;
    // Lấy tất cả đơn hàng
    public List<OrderFishDto> getAllOrderFish() {
        return orderFishRepository.findAll().stream()
                .map(OrderFishDto::from)
                .collect(Collectors.toList());
    }
    // Lấy dơn hàng theo id
    @Override
    public OrderFishDto getOrderFishByOrderFishId(Long orderFishId) {
        return orderFishRepository.findById(orderFishId)
                .map(OrderFishDto::from)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy order fish có id " + orderFishId));
    }
    // Lấy đơn hàng theo user id
    @Override
    public List<OrderFishDto> getOrderFishByUserId(Long userId) {
        return orderFishRepository.findByUserId(userId).stream()
                .map(OrderFishDto::from)
                .collect(Collectors.toList());
    }
    // Tạo đơn mới
    @Override
    public OrderFishDto createOrderFish(OrderFishForm orderFishForm) {
        OrderFish orderFish = OrderFish.builder()
                .status(OrderFish.Status.PENDING)
                .orderDate(orderFishForm.getOrderDate())
                .deliveryDate(orderFishForm.getDeliveryDate())
                .paymentMethod(orderFishForm.getPaymentMethod())
                .createdDate(LocalDate.now())
                .user(userRepository.findById(orderFishForm.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found")))
                .build();

        orderFish = orderFishRepository.save(orderFish); // Lưu trước khi thêm chi tiết item

        List<OrderFishDetail> orderFishDetailList = new ArrayList<>();
        // Xử lý logic tạo đơn
        long totalAmount = 0;
        for (OrderFishDetailForm detailForm : orderFishForm.getOrderFishDetailFormList()) {
            OrderFishDetail orderFishDetail = orderFishDetailService.createOrderFishDetail(detailForm, orderFish);
            orderFishDetailList.add(orderFishDetail);
            totalAmount += orderFishDetail.getPrice() * orderFishDetail.getQuantity();
        }

        orderFish.setTotalAmount(totalAmount);
        orderFish.setOrderFishDetail(orderFishDetailList);

        return OrderFishDto.from(orderFishRepository.save(orderFish));
    }
    // Cập nhật đơn hàng
    @Override
    public OrderFishDto updateOrderFish(long orderFishId, OrderFishForm orderFishForm) {
        OrderFish orderFish = orderFishRepository.findById(orderFishId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy order fish có id " + orderFishId));

        orderFish.setStatus(orderFishForm.getStatus());
        orderFish.setOrderDate(orderFishForm.getOrderDate());
        orderFish.setDeliveryDate(orderFishForm.getDeliveryDate());
        orderFish.setPaymentMethod(orderFishForm.getPaymentMethod());
        orderFish.setCreatedDate(orderFishForm.getCreatedDate());
        orderFish.setUser(userRepository.findById(orderFishForm.getUserId()).orElse(null));
        // Xử lý logic tạo đơn
        orderFish.getOrderFishDetail().clear();
        long totalAmount = 0;

        for (OrderFishDetailForm detailForm : orderFishForm.getOrderFishDetailFormList()) {
            OrderFishDetail orderFishDetail = orderFishDetailService.updateOrderFishDetail(detailForm);
            orderFish.getOrderFishDetail().add(orderFishDetail);
            totalAmount += orderFishDetail.getPrice() * orderFishDetail.getQuantity();
        }
        orderFish.setTotalAmount(totalAmount);

        return OrderFishDto.from(orderFishRepository.save(orderFish));
    }
    // Xóa đơn hàng
    @Override
    public String deleteOrderFish(Long orderFishId) {
        orderFishRepository.findById(orderFishId)
                .ifPresentOrElse(
                        orderFishRepository::delete,
                        () -> {
                            throw new RuntimeException("Không tìm thấy order fish có id " + orderFishId);
                        }
                );

        return "Xóa order fish có id:" + orderFishId + " thành công";
    }
}
