package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.OrderFishDto;
import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.payload.OrderFishForm;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;
import org.example.nishiki_koi_shop.service.OrderFishService;
import org.example.nishiki_koi_shop.service.OrderTourService;
import org.example.nishiki_koi_shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-staff") // API dành cho nhân viên giao hàng
public class DeliveryStaffController {

    private final UserService userService; // Dịch vụ quản lý thông tin người dùng
    private final OrderTourService orderTourService; // Dịch vụ quản lý đơn đặt tour
    private final OrderFishService orderFishService; // Dịch vụ quản lý đơn đặt cá

    // Quản lý đơn đặt hàng - Tour
    // Xem chi tiết một đơn đặt hàng (Tour)
    @GetMapping("/order-tours/{id}")
    public ResponseEntity<OrderTourDto> getOrderTourById(@PathVariable("id") long id) {
        return ResponseEntity.ok(orderTourService.getOrderTourById(id));
    }

    // Xem danh sách tất cả đơn đặt hàng (Tour)
    @GetMapping("/order-tours")
    public ResponseEntity<List<OrderTourDto>> getAllOrderTours() {
        return new ResponseEntity<>(orderTourService.getAllOrderTours(), HttpStatus.OK);
    }

    // Cập nhật trạng thái hoặc thông tin giao hàng của một đơn đặt hàng (Tour)
    @PutMapping("/order-tours/update/{id}")
    public ResponseEntity<OrderTourDto> updateOrderTour(@PathVariable("id") long id, @RequestBody OrderTourForm orderTourForm) {
        OrderTourDto updatedOrderTour = orderTourService.updateOrderTour(id, orderTourForm);
        return ResponseEntity.ok(updatedOrderTour);
    }

    // Quản lý đơn đặt hàng - Cá
    // Xem chi tiết một đơn đặt hàng (Cá)
    @GetMapping("/order-fishes/{id}")
    public ResponseEntity<OrderFishDto> getOrderFishById(@PathVariable long id) {
        return new ResponseEntity<>(orderFishService.getOrderFishById(id), HttpStatus.OK);
    }

    // Xem danh sách tất cả đơn đặt hàng (Cá)
    @GetMapping("/order-fishes")
    public ResponseEntity<List<OrderFishDto>> getAllOrderFishes() {
        return new ResponseEntity<>(orderFishService.getAllOrderFishes(), HttpStatus.OK);
    }

    // Cập nhật trạng thái hoặc thông tin giao hàng của một đơn đặt hàng (Cá)
    @PutMapping("/order-fishes/update/{id}")
    public ResponseEntity<OrderFishDto> updateOrderFish(@PathVariable long id, @RequestBody OrderFishForm orderFishForm) {
        return new ResponseEntity<>(orderFishService.updateOrderFish(id, orderFishForm), HttpStatus.OK);
    }

    // Xem thông tin người dùng (chỉ đọc)
    // Xem chi tiết thông tin của một người dùng dựa trên ID
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
