package org.example.nishiki_koi_shop.controllers;

import org.example.nishiki_koi_shop.model.dto.FishTypeDto;
import org.example.nishiki_koi_shop.service.FishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FishTypeController {
    @Autowired
    private FishTypeService fishTypeService;

    @GetMapping("/fishTypes/get")
    public ResponseEntity<?> getAllFishTypes() {
        return ResponseEntity.ok(fishTypeService.getAllFishTypes());
    }

    @GetMapping("/fishTypes/get/{id}")
    public ResponseEntity<?> getFishTypeById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(fishTypeService.getFishTypeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/fishTypes/add")
    public ResponseEntity<?> addFishType(@RequestBody FishTypeDto fishTypeDto) {
        return ResponseEntity.ok(fishTypeService.createFishType(fishTypeDto));
    }

    @PutMapping("/fishTypes/update")
    public ResponseEntity<?> updateFishType(@RequestBody FishTypeDto fishTypeDto) {
        try {
            return ResponseEntity.ok(fishTypeService.updateFishType(fishTypeDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/fishTypes/delete/{id}")
    public ResponseEntity<?> deleteFishType(@PathVariable long id) {
        try {
            return ResponseEntity.ok(fishTypeService.deleteFishTypeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/fishTypes/search")
    public ResponseEntity<?> getFishTypeByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok(fishTypeService.getFishTypeByName(name));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/fishTypes/getFish/{id}")
    public ResponseEntity<?> getFishByFishTypeId(@PathVariable long id) {
        try {
            return ResponseEntity.ok(fishTypeService.getFishByFishTypeId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
