package com.example.nishikiKoiShop.controllers;

import com.example.nishikiKoiShop.entities.Farm;
import com.example.nishikiKoiShop.services.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public Farm getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    @DeleteMapping("/{id}")
    public void deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
    }
}
