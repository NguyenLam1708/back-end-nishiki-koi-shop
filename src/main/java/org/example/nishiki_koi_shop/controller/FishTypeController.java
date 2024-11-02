package org.example.nishiki_koi_shop.controller;

import org.example.nishiki_koi_shop.model.dto.FishTypeDto;
import org.example.nishiki_koi_shop.service.FishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fish-types")
public class FishTypeController {
    @Autowired
    private FishTypeService fishTypeService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getFishTypeById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(fishTypeService.getFishTypeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> getFishTypeByName(@RequestParam String name) {
//        try {
//            return ResponseEntity.ok(fishTypeService.getFishTypeByName(name));
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
}
