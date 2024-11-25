    package org.example.nishiki_koi_shop.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nishiki_koi_shop.model.dto.CartItemDto;
import org.example.nishiki_koi_shop.model.dto.OrderFishDto;
import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.entity.*;
import org.example.nishiki_koi_shop.model.payload.OrderFishForm;
import org.example.nishiki_koi_shop.repository.*;
import org.example.nishiki_koi_shop.service.OrderFishService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrderFishServiceImpl implements OrderFishService {

    private final OrderFishRepository orderFishRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderFishDetailRepository orderFishDetailRepository;
    private final FishRepository fishRepository;

    @Override
    @Transactional
    public OrderFishDto createOrderFish(OrderFishForm orderFishForm, Principal principal) {
        // Lấy thông tin người dùng từ email
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        log.info("Creating order for user: {}", user.getUsername());

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("No cart found for user"));

        // Lấy danh sách các CartItem từ repository
        // Lấy danh sách các CartItem từ repository theo danh sách ID được chọn
        List<CartItem> selectedCartItems = cartItemRepository.findAllById(orderFishForm.getCartItemIds());

        if (selectedCartItems.isEmpty()) {
            log.warn("No items selected for checkout by user {}", user.getUsername());
            throw new RuntimeException("No items selected for checkout. Please select items and try again.");
        }

        BigDecimal totalAmount = selectedCartItems.stream()
                .map(item -> item.getFish().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Tạo đơn hàng (OrderFish)
        OrderFish orderFish = OrderFish.builder()
                .user(user)
                .totalAmount(totalAmount)
                .deliveryDate(orderFishForm.getDeliveryDate())
                .paymentMethod(orderFishForm.getPaymentMethod())
                .shippingAddress(orderFishForm.getShippingAddress())
                .createdDate(LocalDate.now())
                .status(OrderFish.Status.PENDING) // Trạng thái mặc định
                .build();

        // Lưu đơn hàng vào cơ sở dữ liệu
        orderFishRepository.save(orderFish);

        log.info("Order created with ID: {}", orderFish.getOrderFishId());

        // Tạo danh sách các OrderFishDetail từ CartItem
        List<OrderFishDetail> orderFishDetails = selectedCartItems.stream()
                .map(cartItem -> {
                    Fish fish = cartItem.getFish();
                    int quantity = fish.getQuantity();

                    if (quantity < cartItem.getQuantity()) {
                        throw new RuntimeException("Not enough stock for fish: " + fish.getFishId());
                    }

                    fish.setQuantity(quantity - cartItem.getQuantity());
                    fishRepository.save(fish);

                    return OrderFishDetail.builder()
                            .fish(fish)
                            .orderFish(orderFish)
                            .quantity(cartItem.getQuantity())
                            .price(fish.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                            .build();
                })
                .collect(Collectors.toList());


        // Lưu từng OrderFishDetail vào cơ sở dữ liệu
        orderFishDetailRepository.saveAll(orderFishDetails);

        log.info("Order details created for order ID: {}", orderFish.getOrderFishId());

        return OrderFishDto.from(orderFish);
    }


    @Override
    public OrderFishDto getOrderFishById(long id, Principal principal) {
        OrderFish orderFish = orderFishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderFish not found"));

        if (!orderFish.getUser().getEmail().equals(principal.getName())) {
            log.warn("Unauthorized access attempt for order ID: {}", id);
            throw new SecurityException("Unauthorized access");
        }

        log.info("Order retrieved with ID: {}", id);
        return OrderFishDto.from(orderFish);
    }

    @Override
    public List<OrderFishDto> getOrderFishByUserId(long id, Principal principal) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getEmail().equals(principal.getName())) {
            log.warn("Unauthorized access attempt for user ID: {}", id);
            throw new SecurityException("Unauthorized access");
        }

        List<OrderFish> orders = orderFishRepository.findByUserId(id);
        return orders.stream().map(OrderFishDto::from).collect(Collectors.toList());
    }

    @Override
    public List<OrderFishDto> getAllOrderFishes() {
        log.info("Retrieving all order fishes");
        return orderFishRepository.findAll()
                .stream()
                .map(OrderFishDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public OrderFishDto getOrderFishById(long id) {
        return orderFishRepository.findById(id)
                .map(OrderFishDto::from)
                .orElseThrow(() -> new RuntimeException("OrderFish not found"));
    }

    @Override
    public OrderFishDto updateOrderFish(long id, OrderFishForm orderFishForm) {
        OrderFish orderFish = orderFishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderFish not found"));

        orderFish.setDeliveryDate(orderFishForm.getDeliveryDate());
        orderFish.setPaymentMethod(orderFishForm.getPaymentMethod());

        OrderFish updatedOrderFish = orderFishRepository.save(orderFish);
        log.info("Order updated with ID: {}", updatedOrderFish.getOrderFishId());
        return OrderFishDto.from(updatedOrderFish);
    }
}
