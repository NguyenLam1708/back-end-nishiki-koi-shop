package org.example.nishiki_koi_shop.controllers;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.service.FishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fish")
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    // Create
    @PostMapping
    public ResponseEntity<FishDto> createFish(@RequestBody FishDto fishDto) {
        FishDto createdFish = fishService.createFish(fishDto);
        return ResponseEntity.ok(createdFish);
    }

    // Read (All)
    @GetMapping
    public ResponseEntity<List<FishDto>> getAllFish() {
        List<FishDto> fishList = fishService.getAllFish();
        return ResponseEntity.ok(fishList);
    }

    // Read (Single)
    @GetMapping("/{id}")
    public ResponseEntity<FishDto> getFishById(@PathVariable Long id) {
        FishDto fishDto = fishService.getFishById(id);
        return ResponseEntity.ok(fishDto);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<FishDto> updateFish(@PathVariable Long id, @RequestBody FishDto fishDto) {
        FishDto updatedFish = fishService.updateFish(id, fishDto);
        return ResponseEntity.ok(updatedFish);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable Long id) {
        fishService.deleteFish(id);
        return ResponseEntity.noContent().build();
    }
}
