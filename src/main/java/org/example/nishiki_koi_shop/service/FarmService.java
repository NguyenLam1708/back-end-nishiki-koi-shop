package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.FarmDto;
import org.example.nishiki_koi_shop.model.payload.FarmForm;

import java.util.List;

public interface FarmService {
    List<FarmDto> getAllFarm();
    FarmDto createFarm(FarmForm farmForm);
    FarmDto updateFarm(FarmForm farmForm);
    FarmDto getFarmById(long id);
}
