package org.example.nishiki_koi_shop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.nishiki_koi_shop.model.dto.OrderFishDetailDto;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.entity.OrderFish;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.example.nishiki_koi_shop.model.payload.OrderFishDetailForm;
import org.example.nishiki_koi_shop.repository.FishRepository;
import org.example.nishiki_koi_shop.repository.OrderFishDetailRepository;
import org.example.nishiki_koi_shop.repository.OrderFishRepository;
import org.example.nishiki_koi_shop.service.OrderFishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderFishDetailServiceImpl implements OrderFishDetailService {
    @Autowired
    private OrderFishDetailRepository orderFishDetailRepository;
    @Autowired
    private OrderFishRepository orderFishRepository;
    @Autowired
    private FishRepository fishRepository;
    // Lấy chi tiết đơn theo order id
    @Override
    public List<OrderFishDetailDto> getOrderFishDetailListByOrderId(long orderFishId) {
        OrderFish orderFish = orderFishRepository.findById(orderFishId)
                .orElseThrow(() -> new RuntimeException("OrderFish not found"));

        return orderFish.getOrderFishDetail().stream()
                .map(OrderFishDetailDto::from)
                .collect(Collectors.toList());
    }
    // Lấy chi tiết đơn theo item id
    @Override
    public OrderFishDetailDto getOrderFishDetailById(long orderFishDetailId) {
        return OrderFishDetailDto.from(Objects.requireNonNull(orderFishDetailRepository.findById(orderFishDetailId).orElse(null)));
    }
    // Tạo chi tiết đơn
    @Override
    public OrderFishDetail createOrderFishDetail(OrderFishDetailForm orderFishDetailForm, OrderFish orderFish) {
        Fish fish = fishRepository.findById(orderFishDetailForm.getFishId())
                .orElseThrow(() -> new EntityNotFoundException("Fish not found with ID: " + orderFishDetailForm.getFishId()));

        if (fish.getQuantity() < orderFishDetailForm.getQuantity()) {
            throw new RuntimeException("Not enough fish '" + fish.getName() + "' in stock. Required: " + orderFishDetailForm.getQuantity() + ", Available: " + fish.getQuantity());
        }

        OrderFishDetail orderFishDetail = OrderFishDetail.builder()
                .orderFish(orderFish)
                .fish(fish)
                .price(fish.getPrice())
                .quantity(orderFishDetailForm.getQuantity())
                .build();

        return orderFishDetailRepository.save(orderFishDetail);
    }
    // Cập nhật chi tiết đơn
    @Override
    public OrderFishDetail updateOrderFishDetail(OrderFishDetailForm orderFishDetailForm) {
        OrderFish orderFish = orderFishRepository.findById(orderFishDetailForm.getOrderFishId())
                .orElseThrow(() -> new EntityNotFoundException("OrderFish not found with ID: " + orderFishDetailForm.getOrderFishId()));

        Fish fish = fishRepository.findById(orderFishDetailForm.getFishId())
                .orElseThrow(() -> new EntityNotFoundException("Fish not found with ID: " + orderFishDetailForm.getFishId()));

        if (fish.getQuantity() < orderFishDetailForm.getQuantity()) {
            throw new RuntimeException("Not enough fish: " + fish.getName());
        }

        OrderFishDetail orderFishDetail = orderFishDetailRepository.findById(orderFishDetailForm.getOrderFishDetailId())
                .orElseGet(() -> createOrderFishDetail(orderFishDetailForm, orderFish));    // Nếu chua co đơn thi tao moi

        orderFishDetail.setOrderFish(orderFish);
        orderFishDetail.setFish(fish);
        orderFishDetail.setPrice(fish.getPrice());
        orderFishDetail.setQuantity(orderFishDetailForm.getQuantity());

        return orderFishDetailRepository.save(orderFishDetail);
    }
    // Xóa chi tiết đơn
    @Override
    public String deleteOrderFishDetail(long orderFishDetailId) {
        OrderFishDetail orderFishDetail = orderFishDetailRepository.findById(orderFishDetailId)
                .orElseThrow(() -> new EntityNotFoundException("OrderFishDetail not found with ID: " + orderFishDetailId));
        orderFishDetailRepository.delete(orderFishDetail);
        return "Xóa order detail fish thành công";
    }
}
