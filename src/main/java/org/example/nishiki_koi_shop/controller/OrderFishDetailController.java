package org.example.nishiki_koi_shop.controllers;

import org.example.nishiki_koi_shop.service.OrderFishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-fish-details")
public class OrderFishDetailController {
    @Autowired
    private OrderFishDetailService orderFishDetailService;

    @GetMapping("/getByOrderFishId/{orderFishId}")
    public ResponseEntity<?> getOrderFishDetailsByOrderFishId(@PathVariable("orderFishId") long orderFishId) {
        return ResponseEntity.ok(orderFishDetailService.getOrderFishDetailListByOrderId(orderFishId));
    }

    @GetMapping("/getByOrderFishDetailId/{orderFishDetailId}")
    public ResponseEntity<?> getOrderFishDetailsByOrderFishDetailId(@PathVariable("orderFishDetailId") long orderFishDetailId) {
        return ResponseEntity.ok(orderFishDetailService.getOrderFishDetailById(orderFishDetailId));
    }

    @DeleteMapping("/delete/{orderFishDetailId}")
    public ResponseEntity<?> delete(@PathVariable("orderFishDetailId") long orderFishDetailId) {
        return ResponseEntity.ok(orderFishDetailService.deleteOrderFishDetail(orderFishDetailId));
    }
}
