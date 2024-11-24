package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.*;
import org.example.nishiki_koi_shop.model.payload.OrderFishForm;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;
import org.example.nishiki_koi_shop.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/v1/staff")
    public class StaffConsultController {

        private final UserService userService;
        private final OrderTourService orderTourService;
        private final OrderFishService orderFishService;
        private final FishService fishService;
        private final FarmService farmService;
        private final TourService tourService;

        // Quan ly user
        @GetMapping("/users/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
            return ResponseEntity.ok(userService.getUserById(id));
        }

        // Quản lý order tour
        @GetMapping("/order-tours/{id}")
        public ResponseEntity<OrderTourDto> getOrderTourById(@PathVariable("id") long id) {
            return ResponseEntity.ok(orderTourService.getOrderTourById(id));
        }

        @GetMapping("/order-tours")
        public ResponseEntity<List<OrderTourDto>> getAllOrderTours() {
            return new ResponseEntity<>(orderTourService.getAllOrderTours(), HttpStatus.OK);
        }

        @PutMapping("/order-tours/update/{id}")
        public ResponseEntity<OrderTourDto> updateOrderTour(@PathVariable("id") long id, @RequestBody OrderTourForm orderTourForm) {
            OrderTourDto updatedOrderTour = orderTourService.updateOrderTour(id, orderTourForm);
            return ResponseEntity.ok(updatedOrderTour);
        }

        // Quản lý order fish
        @GetMapping("/order-fishes/{id}")
        public ResponseEntity<OrderFishDto> getOrderFishById(@PathVariable long id) {
            return new ResponseEntity<>(orderFishService.getOrderFishById(id), HttpStatus.OK);
        }

        @GetMapping("/order-fishes")
        public ResponseEntity<List<OrderFishDto>> getAllOrderFishes() {
            return new ResponseEntity<>(orderFishService.getAllOrderFishes(), HttpStatus.OK);
        }

        @PutMapping("/order-fishes/update/{id}")
        public ResponseEntity<OrderFishDto> updateOrderFish(@PathVariable long id, @RequestBody OrderFishForm orderFishForm) {
            return new ResponseEntity<>(orderFishService.updateOrderFish(id, orderFishForm), HttpStatus.OK);
        }

        //Quản lý fish
        @GetMapping("/fish")
        public ResponseEntity<List<FishDto>> getAllFish() {
            List<FishDto> fishList = fishService.getAllFish();
            return ResponseEntity.ok(fishList);
        }

        @GetMapping("/fish/{id}")
        public ResponseEntity<FishDto> getFishById(@PathVariable("id") Long id) {
            FishDto fishDto = fishService.getFishById(id);
            return ResponseEntity.ok(fishDto);
        }

        // Quaản lý farm
        @GetMapping("/farms")
        public ResponseEntity<List<FarmDto>> getAllFarms() {
            List<FarmDto> farmList = farmService.getAllFarm();
            return ResponseEntity.ok(farmList);
        }

        @GetMapping("/farms/{id}")
        public ResponseEntity<FarmDto> getFarmById(@PathVariable("id") Long id) {
            FarmDto farmDto = farmService.getFarmById(id);
            return ResponseEntity.ok(farmDto);
        }

        // Quản lý tour
        @GetMapping("/tours")
        public ResponseEntity<List<TourDto>> getAllTours() {
            List<TourDto> tourList = tourService.getAllTour();
            return ResponseEntity.ok(tourList);
        }

        @GetMapping("/tours/{id}")
        public ResponseEntity<TourDto> getTourById(@PathVariable("id") long id) {
            return new ResponseEntity<>(tourService.getTourById(id), HttpStatus.OK);
        }
    }
