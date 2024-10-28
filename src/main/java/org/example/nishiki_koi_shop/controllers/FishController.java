package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.service.FishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fish")
@RequiredArgsConstructor
public class FishController {

    private final FishService fishService;

    // Create a new fish
    @PostMapping
    public ResponseEntity<FishDto> createFish(@RequestBody FishDto fishDto) {
        FishDto createdFish = fishService.createFish(fishDto);
        return ResponseEntity.ok(createdFish);
    }

    // Get all fish
    @GetMapping
    public ResponseEntity<List<FishDto>> getAllFish() {
        List<FishDto> fishList = fishService.getAllFish();
        return ResponseEntity.ok(fishList);
    }

    // Get a fish by ID
    @GetMapping("/{id}")
    public ResponseEntity<FishDto> getFishById(@PathVariable Long id) {
        FishDto fishDto = fishService.getFishById(id);
        return ResponseEntity.ok(fishDto);
    }

    // Update a fish
    @PutMapping("/{id}")
    public ResponseEntity<FishDto> updateFish(@PathVariable Long id, @RequestBody FishDto fishDto) {
        FishDto updatedFish = fishService.updateFish(id, fishDto);
        return ResponseEntity.ok(updatedFish);
    }

    // Delete a fish
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Long id) {
        fishService.deleteFish(id);
        return ResponseEntity.noContent().build();
    }
}
