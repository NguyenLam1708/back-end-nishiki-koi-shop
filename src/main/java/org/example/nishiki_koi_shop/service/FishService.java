package org.example.nishiki_koi_shop.service;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.repository.FishRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FishService {
    private final FishRepository fishRepository;

    public FishDto createFish(FishDto fishDto) {
        Fish fish = Fish.builder()
                .name(fishDto.getName())
                .price(fishDto.getPrice())
                .description(fishDto.getDescription())
                .image(fishDto.getImage())
                .size(fishDto.getSize())
                .quantity(fishDto.getQuantity())
                .createdDate(LocalDate.now())
                .build();
        fish = fishRepository.save(fish);
        return FishDto.from(fish);
    }

    public List<FishDto> getAllFish() {
        return fishRepository.findAll().stream()
                .map(FishDto::from)
                .collect(Collectors.toList());
    }

    public FishDto getFishById(Long id) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fish not found"));
        return FishDto.from(fish);
    }

    public FishDto updateFish(Long id, FishDto fishDto) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fish not found"));
        fish.setName(fishDto.getName());
        fish.setPrice(fishDto.getPrice());
        fish.setDescription(fishDto.getDescription());
        fish.setImage(fishDto.getImage());
        fish.setSize(fishDto.getSize());
        fish.setQuantity(fishDto.getQuantity());
        return FishDto.from(fishRepository.save(fish));
    }

    public void deleteFish(Long id) {
        if (!fishRepository.existsById(id)) {
            throw new RuntimeException("Fish not found");
        }
        fishRepository.deleteById(id);
    }
}
