package org.example.nishiki_koi_shop.controllers;

import org.example.nishiki_koi_shop.model.payload.OrderFishForm;
import org.example.nishiki_koi_shop.service.OrderFishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-fish")
public class OrderFishController {
    @Autowired
    private OrderFishService orderFishService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllOrderFish() {
        return ResponseEntity.ok(orderFishService.getAllOrderFish());
    }

    @GetMapping("/get/{orderFishId}")
    public ResponseEntity<?> getOrderFishById(@PathVariable("orderFishId") long orderFishId) {
        try {
            return ResponseEntity.ok(orderFishService.getOrderFishByOrderFishId(orderFishId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getOrderFishByUserId(@PathVariable("userId") long userId) {
        try {
            return ResponseEntity.ok(orderFishService.getOrderFishByUserId(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrderFish(@RequestBody OrderFishForm orderFishForm) {
        try {
            return ResponseEntity.ok(orderFishService.createOrderFish(orderFishForm));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{orderFishId}")
    public ResponseEntity<?> updateOrderFish(@PathVariable("orderFishId") long id, @RequestBody OrderFishForm orderFishForm) {
        try {
            return ResponseEntity.ok(orderFishService.updateOrderFish(id, orderFishForm));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{orderFishId}")
    public ResponseEntity<?> deleteOrderFish(@PathVariable("orderFishId") long orderFishId) {
        try {
            return ResponseEntity.ok(orderFishService.deleteOrderFish(orderFishId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
