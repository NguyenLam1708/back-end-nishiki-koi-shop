package org.example.nishiki_koi_shop.controller;


import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.*;
import org.example.nishiki_koi_shop.model.entity.FishType;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.example.nishiki_koi_shop.model.payload.*;
import org.example.nishiki_koi_shop.service.*;
import org.example.nishiki_koi_shop.service.impl.FarmServiceImpl;
import org.example.nishiki_koi_shop.service.impl.FishServiceImpl;
import org.example.nishiki_koi_shop.service.impl.TourServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final UserService userService;
    private final OrderTourService orderTourService;
    private final OrderTourDetailService orderTourDetailService;
    private final FishService fishService;
    private final FarmService farmService;
    private final FishTypeService fishTypeService;
    private final TourServiceImpl tourServiceImpl;
    private final OrderFishService orderFishService;
    private final OrderFishDetailService orderFishDetailService;
    @GetMapping("/myInfo")
    public ResponseEntity<UserDto> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateMyInfo(@PathVariable("id") long id, Principal principal, @RequestBody UserForm form) {
        return ResponseEntity.ok(userService.updateMyInfo(id, principal, form));
    }

    // user
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("users/soft-delete/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable("id") Long userId, Principal principal) {
        userService.softDeleteUser(userId, principal);
        return ResponseEntity.ok("Người dùng đã được xóa mềm thành công");
    }

    @PutMapping("users/restore/{id}")
    public ResponseEntity<String> restoreUser(@PathVariable("id") Long id) {
        userService.restoreUser(id);
        return ResponseEntity.ok("User restored successfully");
    }

    @PutMapping("users/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserForm form) {
        return ResponseEntity.ok(userService.updateUser(id, form));
    }

    // OrderTour
    @GetMapping("/order-tours/{id}")
    public ResponseEntity<OrderTourDto> getOrderTourById(@PathVariable("id") long id) {
        OrderTourDto orderTour = orderTourService.getOrderTourById(id);
        return ResponseEntity.ok(orderTour);
    }

    @GetMapping("order-tours/get-all-order-tours")
    public ResponseEntity<List<OrderTourDto>> getAllOrderTours() {
        List<OrderTourDto> orderTours = orderTourService.getAllOrderTours();
        return new ResponseEntity<>(orderTours, HttpStatus.OK);
    }

    @PutMapping("/order-tours/update/{id}")
    public ResponseEntity<OrderTourDto> updateOrderTour(@PathVariable("id") long id, @RequestBody OrderTourForm orderTourForm) {
        OrderTourDto updatedOrderTour = orderTourService.updateOrderTour(id, orderTourForm);
        return ResponseEntity.ok(updatedOrderTour);
    }

    // OrderTourDetail
    @GetMapping("/order-tours/order-tour-detail")
    public ResponseEntity<List<OrderTourDetailDto>> getAllOrderTourDetails() {
        List<OrderTourDetailDto> orderTourDetails = orderTourDetailService.getAllOrderTourDetails();
        return new ResponseEntity<>(orderTourDetails, HttpStatus.OK);
    }

    @GetMapping("/order-tours/order-tour-detail/{id}")
    public ResponseEntity<OrderTourDetailDto> getOrderTourDetailById(@PathVariable long id) {
        OrderTourDetailDto orderTourDetail = orderTourDetailService.getOrderTourDetailById(id);
        return new ResponseEntity<>(orderTourDetail, HttpStatus.OK);
    }

    @PutMapping("/order-tours/order-tour-detail/update/{id}")
    public ResponseEntity<OrderTourDetailDto> updateOrderTourDetail(
            @PathVariable long id, @RequestBody OrderTourDetailForm form) {
        OrderTourDetailDto updatedDetail = orderTourDetailService.updateOrderTourDetail(id, form);
        return new ResponseEntity<>(updatedDetail, HttpStatus.OK);
    }
    // OrderFish

    @GetMapping("/order-fishes/get-all-order-fishes")
    public ResponseEntity<List<OrderFishDto>> getAllOrderFishes() {
        return new ResponseEntity<>(orderFishService.getAllOrderFishes(), HttpStatus.OK);
    }

    @GetMapping("/order-fishes/{id}")
    public ResponseEntity<OrderFishDto> getOrderFishById(@PathVariable long id) {
        return new ResponseEntity<>(orderFishService.getOrderFishById(id), HttpStatus.OK);
    }

    @PutMapping("/order-fishes/update/{id}")
    public ResponseEntity<OrderFishDto> updateOrderFish(@PathVariable long id, @RequestBody OrderFishForm orderFishForm) {
        return new ResponseEntity<>(orderFishService.updateOrderFish(id, orderFishForm), HttpStatus.OK);
    }
    //OrderFishDetail
    @GetMapping("/order-fishes/order-fishes-detail/{id}")
    public ResponseEntity<OrderFishDetailDto> getOrderFishDetailById(@PathVariable("id") long id){
        return new ResponseEntity<>(orderFishDetailService.getOrderFishDetailById(id),HttpStatus.OK);
    };
    @GetMapping("/order-fishes/order-fishes-detail")
    public ResponseEntity<List<OrderFishDetailDto>> getAllOrderFishDetails() {
        return new ResponseEntity<>(orderFishDetailService.getAllOrderFishDetails(),HttpStatus.OK);
    }
    @PutMapping("/order-fishes/order-fishes-detail/update/{id}")
    public ResponseEntity<OrderFishDetailDto> updateOrderFishDetail(@PathVariable("id") long id,@RequestBody OrderFishDetailForm orderFishDetailForm){
        return new ResponseEntity<>(orderFishDetailService.updateOrderFishDetail(id,orderFishDetailForm), HttpStatus.OK);
    };

    // Fish
    // Create
    @PostMapping("/fish/create-fish")
    public ResponseEntity<FishDto> createFish(@ModelAttribute FishForm fishform) {
        FishDto createdFish = fishService.createFish(fishform);
        return ResponseEntity.ok(createdFish);
    }

    // Read (All)
    @GetMapping("/fish/get-all-fishes")
    public ResponseEntity<List<FishDto>> getAllFish() {
        List<FishDto> fishList = fishService.getAllFish();
        return ResponseEntity.ok(fishList);
    }

    // Read (Single)
    @GetMapping("/fish/{id}")
    public ResponseEntity<FishDto> getFishById(@PathVariable("id") Long id) {
        FishDto fishDto = fishService.getFishById(id);
        return ResponseEntity.ok(fishDto);
    }

    // Update
    @PutMapping("/fish/update/{id}")
    public ResponseEntity<FishDto> updateFish(@PathVariable("id") Long id, @RequestBody FishForm fishForm) {
        FishDto updatedFish = fishService.updateFish(id, fishForm);
        return ResponseEntity.ok(updatedFish);
    }

    // Delete
    @DeleteMapping("/fish/delete/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Long id) {
        fishService.deleteFish(id);
        return ResponseEntity.noContent().build();
    }

    // farm
    // create
    @PostMapping("/farm/create-farm")
    public ResponseEntity<FarmDto> createFarm(@RequestBody FarmForm farmForm) {
        FarmDto createFarm = farmService.createFarm(farmForm);
        return ResponseEntity.ok(createFarm);
    }

    //Read (All)
    @GetMapping("/farm/get-all-farm")
    public ResponseEntity<List<FarmDto>> getAllFarms() {
        List<FarmDto> famrList = farmService.getAllFarm();
        return ResponseEntity.ok(famrList);
    }

    //find by ID
    @GetMapping("/farm/{id}")
    public ResponseEntity<FarmDto> getFarmById(@PathVariable("id") Long id) {
        FarmDto farmDto = farmService.getFarmById(id);
        return ResponseEntity.ok(farmDto);
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

    // fish type

    @GetMapping("/fish-types/get-all-fish-types")
    public ResponseEntity<List<FishTypeDto>> getAllFishTypes() {
        return ResponseEntity.ok(fishTypeService.getAllFishTypes());
    }

    @GetMapping("/fish-types/{id}")
    public ResponseEntity<?> getFishTypeById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(fishTypeService.getFishTypeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/fish-types/create")
    public ResponseEntity<FishTypeDto> addFishType(@RequestBody FishTypeForm fishTypeForm) {
        return ResponseEntity.ok(fishTypeService.createFishType(fishTypeForm));
    }

    @PutMapping("/fish-types/update/{id}")
    public ResponseEntity<FishTypeDto> updateFishType(@PathVariable long id, @RequestBody FishTypeForm fishTypeForm) {
        FishTypeDto updatedFishType = fishTypeService.updateFishType(id, fishTypeForm);
        return ResponseEntity.ok(updatedFishType);
    }

    @DeleteMapping("/fish-types/delete/{id}")
    public ResponseEntity<?> deleteFishType(@PathVariable long id) {
        try {
            return ResponseEntity.ok(fishTypeService.deleteFishTypeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

