package org.example.nishiki_koi_shop.controller;

import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farms")
public class FarmController {
    @Autowired
    private FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public Optional<Farm> getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    @PutMapping("/{id}")
    public Farm updateFarm(@PathVariable Long id, @RequestBody Farm updatedFarm) {
        return farmService.updateFarm(id, updatedFarm);
    }

    @DeleteMapping("/{id}")
    public void deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
    }
}
