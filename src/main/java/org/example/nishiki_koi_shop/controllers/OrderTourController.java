package org.example.nishiki_koi_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.OrderTourDto;
import org.example.nishiki_koi_shop.model.payload.OrderTourForm;
import org.example.nishiki_koi_shop.service.OrderTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-tours")
public class OrderTourController {
    @Autowired
    private OrderTourService orderTourService;

    @PostMapping
    public ResponseEntity<OrderTourDto> createOrderTour(@RequestBody OrderTourForm orderTourForm) {
        OrderTourDto createdOrderTour = orderTourService.createOrderTour(orderTourForm);
        return new ResponseEntity<>(createdOrderTour, HttpStatus.CREATED);
    }

}
