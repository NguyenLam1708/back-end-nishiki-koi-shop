package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.OrderTourDetailDto;
import org.example.nishiki_koi_shop.model.payload.OrderTourDetailForm;
import org.example.nishiki_koi_shop.service.OrderTourDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order-tour-details")
@RequiredArgsConstructor
public class OrderTourDetailController {
    private final OrderTourDetailService orderTourDetailService;

    @PostMapping
    public ResponseEntity<OrderTourDetailDto> createOrderTourDetail(@RequestBody OrderTourDetailForm form) {
        OrderTourDetailDto createdDetail = orderTourDetailService.createOrderTourDetail(form);
        return new ResponseEntity<>(createdDetail, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderTourDetailDto> getOrderTourDetailById(@PathVariable long id, Principal principal) {
        OrderTourDetailDto orderTourDetail = orderTourDetailService.getOrderTourDetailById(id,principal);
        return new ResponseEntity<>(orderTourDetail, HttpStatus.OK);
    }

    @GetMapping("/order-tour/{id}")
    public ResponseEntity<List<OrderTourDetailDto>> getOrderTourDetailsByOrderTourId(@PathVariable long id) {
        return new ResponseEntity<>(orderTourDetailService.getOrderTourDetailByOrderTourId(id), HttpStatus.OK);
    }
}
