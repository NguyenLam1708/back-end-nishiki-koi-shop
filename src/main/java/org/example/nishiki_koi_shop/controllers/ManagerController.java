package org.example.nishiki_koi_shop.controllers;


import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.OrderTourDetailDto;
import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.payload.OrderTourDetailForm;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;
import org.example.nishiki_koi_shop.service.OrderTourDetailService;
import org.example.nishiki_koi_shop.service.OrderTourService;
import org.example.nishiki_koi_shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
public class ManagerController {
    private final UserService userService;
    private final OrderTourService orderTourService;
    private final OrderTourDetailService orderTourDetailService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/myInfo")
    public ResponseEntity<UserDto> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("users/{loggedInUserId}/soft-delete/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable("id") Long userId, @PathVariable("loggedInUserId") Long loggedInUserId) {
        userService.softDeleteUser(userId, loggedInUserId);
        return ResponseEntity.ok("Người dùng đã được xóa mềm thành công");
    }

    @PutMapping("users/{id}/restore")
    public ResponseEntity<String> restoreUser(@PathVariable("id") Long id) {
        userService.restoreUser(id);
        return ResponseEntity.ok("User restored successfully");
    }

    // OrderTour
    @GetMapping("/order-tours/{id}")
    public ResponseEntity<OrderTourDto> getOrderTourById(@PathVariable long id) {
        OrderTourDto orderTour = orderTourService.getOrderTourById(id);
        return ResponseEntity.ok(orderTour);
    }

    @GetMapping("/order-tours")
    public ResponseEntity<List<OrderTourDto>> getAllOrderTours() {
        List<OrderTourDto> orderTours = orderTourService.getAllOrderTours();
        return ResponseEntity.ok(orderTours);
    }

    @PutMapping("/order-tours/{id}")
    public ResponseEntity<OrderTourDto> updateOrderTour(@PathVariable long id, @RequestBody OrderTourForm orderTourForm) {
        OrderTourDto updatedOrderTour = orderTourService.updateOrderTour(id, orderTourForm);
        return ResponseEntity.ok(updatedOrderTour);
    }

    // OrderTourDetail
    @GetMapping("/order-tour-detail")
    public ResponseEntity<List<OrderTourDetailDto>> getAllOrderTourDetails() {
        List<OrderTourDetailDto> orderTourDetails = orderTourDetailService.getAllOrderTourDetails();
        return new ResponseEntity<>(orderTourDetails, HttpStatus.OK);
    }

    @PutMapping("/order-tour-detail/{id}")
    public ResponseEntity<OrderTourDetailDto> updateOrderTourDetail(
            @PathVariable long id, @RequestBody OrderTourDetailForm form) {
        OrderTourDetailDto updatedDetail = orderTourDetailService.updateOrderTourDetail(id, form);
        return new ResponseEntity<>(updatedDetail, HttpStatus.OK);
    }
}

