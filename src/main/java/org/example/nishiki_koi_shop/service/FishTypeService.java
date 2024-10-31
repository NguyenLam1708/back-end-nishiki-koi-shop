package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.model.dto.FishTypeDto;

import java.util.List;

public interface FishTypeService {
    List<FishTypeDto> getAllFishTypes();
    FishTypeDto getFishTypeById(long id);
    FishTypeDto createFishType(FishTypeDto fishTypeDto);
    FishTypeDto updateFishType(FishTypeDto fishTypeDto);
    String deleteFishTypeById(long id);
    FishTypeDto getFishTypeByName(String name);
    List<FishDto> getFishByFishTypeId(long id);
}
