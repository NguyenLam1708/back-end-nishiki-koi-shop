package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.FarmDto;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.payload.FarmForm;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.service.FarmService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;

    @Override
    public List<FarmDto> getAllFarm() {
        return farmRepository.findAll().stream()
                .map(FarmDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public FarmDto createFarm(FarmForm farmForm) {
        if (farmRepository.existsByName(farmForm.getName())) {
            throw new IllegalArgumentException("Tên trang trại đã tồn tại. Vui lòng chọn tên khác.");
        }

        Farm farm = Farm.builder()
                .name(farmForm.getName())
                .description(farmForm.getDescription())
                .location(farmForm.getLocation())
                .image(farmForm.getImage())
                .contactInfo(farmForm.getContactInfo())
                .createdDate(LocalDate.now())
                .build();
        farm = farmRepository.save(farm);
        return FarmDto.from(farm);
    }
}
