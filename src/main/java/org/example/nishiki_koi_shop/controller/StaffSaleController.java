package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.*;
import org.example.nishiki_koi_shop.model.payload.*;
import org.example.nishiki_koi_shop.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale") // API dành cho nhân viên bán hàng
public class StaffSaleController {

    private final UserService userService; // Dịch vụ quản lý thông tin người dùng
    private final OrderTourService orderTourService; // Dịch vụ quản lý đơn đặt tour
    private final OrderFishService orderFishService; // Dịch vụ quản lý đơn đặt cá
    private final FishService fishService; // Dịch vụ quản lý sản phẩm cá
    private final FishTypeService fishTypeService; // Dịch vụ quản lý loại cá
    private final TourService tourService; // Dịch vụ quản lý tour

    // Quản lý thông tin khách hàng
    // Xem danh sách khách hàng
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    // Xem thông tin chi tiết của một khách hàng
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Quản lý đơn đặt hàng - Tour
    // Xem danh sách tất cả các đơn đặt hàng (Tour)
    @GetMapping("/order-tours")
    public ResponseEntity<List<OrderTourDto>> getAllOrderTours() {
        return ResponseEntity.ok(orderTourService.getAllOrderTours());
    }

    // Xem chi tiết một đơn đặt hàng (Tour)
    @GetMapping("/order-tours/{id}")
    public ResponseEntity<OrderTourDto> getOrderTourById(@PathVariable("id") long id) {
        return ResponseEntity.ok(orderTourService.getOrderTourById(id));
    }

    // Cập nhật thông tin đơn đặt hàng (Tour)
    @PutMapping("/order-tours/update/{id}")
    public ResponseEntity<OrderTourDto> updateOrderTour(@PathVariable("id") long id, @RequestBody OrderTourForm orderTourForm) {
        return ResponseEntity.ok(orderTourService.updateOrderTour(id, orderTourForm));
    }

    // Quản lý đơn đặt hàng - Cá
    // Xem danh sách tất cả các đơn đặt hàng (Cá)
    @GetMapping("/order-fishes")
    public ResponseEntity<List<OrderFishDto>> getAllOrderFishes() {
        return ResponseEntity.ok(orderFishService.getAllOrderFishes());
    }

    // Xem chi tiết một đơn đặt hàng (Cá)
    @GetMapping("/order-fishes/{id}")
    public ResponseEntity<OrderFishDto> getOrderFishById(@PathVariable("id") long id) {
        return ResponseEntity.ok(orderFishService.getOrderFishById(id));
    }

    // Cập nhật thông tin đơn đặt hàng (Cá)
    @PutMapping("/order-fishes/update/{id}")
    public ResponseEntity<OrderFishDto> updateOrderFish(@PathVariable("id") long id, @RequestBody OrderFishForm orderFishForm) {
        return ResponseEntity.ok(orderFishService.updateOrderFish(id, orderFishForm));
    }

    // Quản lý sản phẩm - Cá
    // Xem danh sách tất cả các sản phẩm (Cá)
    @GetMapping("/fish")
    public ResponseEntity<List<FishDto>> getAllFish() {
        return ResponseEntity.ok(fishService.getAllFish());
    }

    // Xem chi tiết thông tin một sản phẩm (Cá)
    @GetMapping("/fish/{id}")
    public ResponseEntity<FishDto> getFishById(@PathVariable("id") long id) {
        return ResponseEntity.ok(fishService.getFishById(id));
    }

    // Quản lý sản phẩm - Loại Cá
    // Xem danh sách tất cả các loại cá
    @GetMapping("/fish-types")
    public ResponseEntity<List<FishTypeDto>> getAllFishTypes() {
        return ResponseEntity.ok(fishTypeService.getAllFishTypes());
    }

    // Xem thông tin chi tiết của một loại cá
    @GetMapping("/fish-types/{id}")
    public ResponseEntity<FishTypeDto> getFishTypeById(@PathVariable("id") long id) {
        return ResponseEntity.ok(fishTypeService.getFishTypeById(id));
    }

    // Quản lý sản phẩm - Tour
    // Xem danh sách tất cả các tour
    @GetMapping("/tour")
    public ResponseEntity<List<TourDto>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTour());
    }

    // Xem thông tin chi tiết của một tour
    @GetMapping("/tour/{id}")
    public ResponseEntity<TourDto> getTourById(@PathVariable("id") long id) {
        return ResponseEntity.ok(tourService.getTourById(id));
    }
}
