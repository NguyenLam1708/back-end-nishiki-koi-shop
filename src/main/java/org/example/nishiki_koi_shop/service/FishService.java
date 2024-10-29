package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.model.payload.FishForm;

import java.util.List;

public interface FishService {
    FishDto createFish(FishForm fishForm);
    List<FishDto> getAllFish();
    FishDto getFishById(Long id);
    FishDto updateFish(Long id, FishForm fishForm);
    void deleteFish(Long id);
}
