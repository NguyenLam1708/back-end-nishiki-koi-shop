package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.payload.FarmForm; // Import nếu cần

import java.util.List;
import java.util.Optional;

public interface FarmService {
    List<Farm> getAllFarms();
    Optional<Farm> getFarmById(Long id);
    Farm saveFarm(Farm farm);
    Farm createFarm(FarmForm farmForm); // Thêm phương thức này
    Farm updateFarm(Long id, Farm updatedFarm);
    void deleteFarm(Long id);
}
