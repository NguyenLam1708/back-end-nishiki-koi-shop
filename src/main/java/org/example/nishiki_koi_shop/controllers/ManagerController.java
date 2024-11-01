package org.example.nishiki_koi_shop.controllers;


import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.*;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.payload.*;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.service.impl.FarmServiceImpl;
import org.example.nishiki_koi_shop.service.impl.FishServiceImpl;
import org.example.nishiki_koi_shop.service.OrderTourDetailService;
import org.example.nishiki_koi_shop.service.OrderTourService;
import org.example.nishiki_koi_shop.service.UserService;
import org.example.nishiki_koi_shop.service.impl.TourServiceImpl;
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
    private final FishServiceImpl fishServiceImpl;
    private final FarmServiceImpl farmServiceImpl;
    private final TourServiceImpl tourServiceImpl;

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
    @GetMapping("/order-tours/order-tour-detail")
    public ResponseEntity<List<OrderTourDetailDto>> getAllOrderTourDetails() {
        List<OrderTourDetailDto> orderTourDetails = orderTourDetailService.getAllOrderTourDetails();
        return new ResponseEntity<>(orderTourDetails, HttpStatus.OK);
    }

    @PutMapping("/order-tours/order-tour-detail/{id}")
    public ResponseEntity<OrderTourDetailDto> updateOrderTourDetail(
            @PathVariable long id, @RequestBody OrderTourDetailForm form) {
        OrderTourDetailDto updatedDetail = orderTourDetailService.updateOrderTourDetail(id, form);
        return new ResponseEntity<>(updatedDetail, HttpStatus.OK);
    }

    // Fish
    // Create
    @PostMapping("/fish/create-fish")
    public ResponseEntity<FishDto> createFish(@RequestBody FishForm fishform) {
        FishDto createdFish = fishServiceImpl.createFish(fishform);
        return ResponseEntity.ok(createdFish);
    }

    // Read (All)
    @GetMapping("/fish/get-all-fishes")
    public ResponseEntity<List<FishDto>> getAllFish() {
        List<FishDto> fishList = fishServiceImpl.getAllFish();
        return ResponseEntity.ok(fishList);
    }

    // Read (Single)
    @GetMapping("/fish/{id}")
    public ResponseEntity<FishDto> getFishById(@PathVariable Long id) {
        FishDto fishDto = fishServiceImpl.getFishById(id);
        return ResponseEntity.ok(fishDto);
    }

    // Update
    @PutMapping("/fish/{id}")
    public ResponseEntity<FishDto> updateFish(@PathVariable Long id, @RequestBody FishForm fishForm) {
        FishDto updatedFish = fishServiceImpl.updateFish(id, fishForm);
        return ResponseEntity.ok(updatedFish);
    }

    // Delete
    @DeleteMapping("/fish/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Long id) {
        fishServiceImpl.deleteFish(id);
        return ResponseEntity.noContent().build();
    }

    //farm
    //Create
    @PostMapping("/farm/create-farm")
    public ResponseEntity<FarmDto> createFarm(@ModelAttribute FarmForm farmForm) {
        FarmDto createFarm = farmServiceImpl.createFarm(farmForm);
        return ResponseEntity.ok(createFarm);
    }

    //Read (All)
    @GetMapping("/farm/get-all-farm")
    public ResponseEntity<List<FarmDto>> getAllFarm() {
        List<FarmDto> famrList = farmServiceImpl.getAllFarm();
        return ResponseEntity.ok(famrList);
    }

    //tour
    //Create
    @PostMapping("/tour/create-tour")
    public ResponseEntity<TourDto> createTour(@ModelAttribute TourForm tourForm) {
        TourDto createTour = tourServiceImpl.createTour(tourForm);
        return ResponseEntity.ok(createTour);
    }

    //Read(All)
    @GetMapping("/tour/get-all-tour")
    public ResponseEntity<List<TourDto>> getAllTour() {
        List<TourDto> tourList = tourServiceImpl.getAllTour();
        return ResponseEntity.ok(tourList);
    }
}

