package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.*;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.entity.Tour;
import org.example.nishiki_koi_shop.model.payload.*;
import org.example.nishiki_koi_shop.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    private final TourService tourService;

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

    // Fish
    @PostMapping("/fish/create-fish")
    public ResponseEntity<FishDto> createFish(@ModelAttribute FishForm fishform) {
        FishDto createdFish = fishService.createFish(fishform);
        return ResponseEntity.ok(createdFish);
    }

    @GetMapping("/fish/get-all-fishes")
    public ResponseEntity<List<FishDto>> getAllFish() {
        List<FishDto> fishList = fishService.getAllFish();
        return ResponseEntity.ok(fishList);
    }

    @GetMapping("/fish/{id}")
    public ResponseEntity<FishDto> getFishById(@PathVariable("id") Long id) {
        FishDto fishDto = fishService.getFishById(id);
        return ResponseEntity.ok(fishDto);
    }

    @PutMapping("/fish/update/{id}")
    public ResponseEntity<FishDto> updateFish(@PathVariable("id") Long id, @RequestBody FishForm fishForm) {
        FishDto updatedFish = fishService.updateFish(id, fishForm);
        return ResponseEntity.ok(updatedFish);
    }

    @DeleteMapping("/fish/delete/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Long id) {
        fishService.deleteFish(id);
        return ResponseEntity.noContent().build();
    }

    // farm
    @PostMapping("/farm/create-farm")
    public ResponseEntity<FarmDto> createFarm(@RequestBody FarmForm farmForm) {
        Farm createdFarm = farmService.createFarm(farmForm);
        FarmDto farmDto = convertToFarmDto(createdFarm);
        return ResponseEntity.ok(farmDto);
    }

    @GetMapping("/farm/get-all-farm")
    public ResponseEntity<List<FarmDto>> getAllFarms() {
        List<Farm> farmList = farmService.getAllFarms();
        List<FarmDto> farmDtoList = farmList.stream().map(this::convertToFarmDto).collect(Collectors.toList());
        return ResponseEntity.ok(farmDtoList);
    }

    @GetMapping("/farm/{id}")
    public ResponseEntity<FarmDto> getFarmById(@PathVariable("id") Long id) {
        Farm farm = farmService.getFarmById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        FarmDto farmDto = convertToFarmDto(farm);
        return ResponseEntity.ok(farmDto);
    }

    // tour
    @PostMapping("/tour/create-tour")
    public ResponseEntity<TourDto> createTour(@ModelAttribute TourForm tourForm) {
        Tour createdTour = tourService.createTour(tourForm);
        TourDto tourDto = convertToTourDto(createdTour);
        return ResponseEntity.ok(tourDto);
    }

    @GetMapping("/tour/get-all-tour")
    public ResponseEntity<List<TourDto>> getAllTour() {
        List<Tour> tourList = tourService.getAllTours();
        List<TourDto> tourDtoList = tourList.stream().map(this::convertToTourDto).collect(Collectors.toList());
        return ResponseEntity.ok(tourDtoList);
    }

    // Helper method for converting entities to DTOs
    private FarmDto convertToFarmDto(Farm farm) {
        return FarmDto.builder()
                .farmId(farm.getFarmId())
                .name(farm.getName())
                .description(farm.getDescription())
                .contactInfo(farm.getContactInfo())
                .location(farm.getLocation())
                .createdDate(farm.getCreatedDate())
                .image(farm.getImage())
                .build();
    }

    private TourDto convertToTourDto(Tour tour) {
        return TourDto.builder()
                .tourId(tour.getId())  // Sử dụng tourId thay vì id
                .tourName(tour.getName())  // Sử dụng tourName thay vì name
                .tourDescription(tour.getDescription())  // Sử dụng tourDescription thay vì description
                .tourPrice(tour.getPrice())  // Sử dụng tourPrice thay vì price
                .build();
    }

}