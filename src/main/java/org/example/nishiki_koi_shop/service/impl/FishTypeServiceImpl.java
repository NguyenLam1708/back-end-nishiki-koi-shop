package org.example.nishiki_koi_shop.service.impl;

import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.model.dto.FishTypeDto;
import org.example.nishiki_koi_shop.model.entity.FishType;
import org.example.nishiki_koi_shop.repository.FishTypeRepository;
import org.example.nishiki_koi_shop.service.FishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishTypeServiceImpl implements FishTypeService {
    @Autowired
    private FishTypeRepository fishTypeRepository;

    @Override
    public List<FishTypeDto> getAllFishTypes() {
        List<FishType> fishTypes = fishTypeRepository.findAll();
        return fishTypes.stream()
                .map(FishTypeDto::fromFishType)
                .collect(Collectors.toList());
    }

    @Override
    public FishTypeDto getFishTypeById(long id) {
        return FishTypeDto.fromFishType(fishTypeRepository.findFishTypesByFishTypeId(id)
                .orElseThrow(() -> new RuntimeException("Fish type not found with id " + id)));
    }

    @Override
    public FishTypeDto createFishType(FishTypeDto fishTypeDto) {
        FishType fishType = new FishType();
        fishType.setName(fishTypeDto.getName());
        fishType.setDescription(fishTypeDto.getDescription());

        return FishTypeDto.fromFishType(fishTypeRepository.save(fishType));
    }

    @Override
    public FishTypeDto updateFishType(FishTypeDto fishTypeDto) {
        Optional<FishType> fishType = fishTypeRepository.findFishTypesByFishTypeId(fishTypeDto.getFishTypeId());
        if (fishType.isPresent()) {
            FishType oldFishType = fishType.get();
            oldFishType.setName(fishTypeDto.getName());
            oldFishType.setDescription(fishTypeDto.getDescription());
            return FishTypeDto.fromFishType(fishTypeRepository.save(oldFishType));
        } else {
            throw new RuntimeException("Fish type not found with id " + fishTypeDto.getFishTypeId());
        }
    }

    @Override
    public String deleteFishTypeById(long id) {
        FishType fishType = fishTypeRepository.findFishTypesByFishTypeId(id)
                .orElseThrow(() -> new RuntimeException("Fish type not found with id " + id));

        fishTypeRepository.delete(fishType);
        return "Xóa Fish Type " + fishType.getName() + " thành công";
    }

    @Override
    public FishTypeDto getFishTypeByName(String name) {
        return FishTypeDto.fromFishType(fishTypeRepository.findFishTypesByName(name)
                .orElseThrow(() -> new RuntimeException("Fish type not found with name " + name)));
    }

    @Override
    public List<FishDto> getFishByFishTypeId(long id) {
        FishType fishType = fishTypeRepository.findFishTypesByFishTypeId(id)
                .orElseThrow(() -> new RuntimeException("Fish type not found with id " + id));

        return fishType.getFish().stream()
                .map(FishDto::from)
                .collect(Collectors.toList());
    }
}
