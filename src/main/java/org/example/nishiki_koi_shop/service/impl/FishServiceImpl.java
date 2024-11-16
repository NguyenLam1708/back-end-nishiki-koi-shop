package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.model.entity.FishType;
import org.example.nishiki_koi_shop.model.payload.FishForm;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.repository.FishRepository;
import org.example.nishiki_koi_shop.repository.FishTypeRepository;
import org.example.nishiki_koi_shop.service.CloudinaryService;
import org.example.nishiki_koi_shop.service.FishService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final FishTypeRepository fishTypeRepository;
    private final FarmRepository farmRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public FishDto createFish(FishForm fishForm) {
        Long fishTypeId = Long.valueOf(fishForm.getFishTypeId());
        FishType fishType = fishTypeRepository.findById(fishTypeId)
                .orElseThrow(() -> new IllegalArgumentException("ID loại cá không hợp lệ"));

        Farm farm = farmRepository.findById(Long.valueOf(fishForm.getFarmId()))
                .orElseThrow(() -> new IllegalArgumentException("ID nông trại không hợp lệ"));

        Fish fish = Fish.builder()
                .name(fishForm.getName())
                .price(fishForm.getPrice())
                .description(fishForm.getDescription())
                .image(cloudinaryService.handleUploadImg(fishForm.getImage(), "fish"))
                .size(fishForm.getSize())
                .quantity(fishForm.getQuantity())
                .fishType(fishType)
                .farm(farm)
                .build();

        fish = fishRepository.save(fish);
        return FishDto.from(fish);
    }


    @Override
    public List<FishDto> getAllFish() {
        return fishRepository.findAll().stream()
                .map(FishDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public FishDto getFishById(Long id) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fish not found"));
        return FishDto.from(fish);
    }

    @Override
    public FishDto updateFish(Long id, FishForm fishForm) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fish not found"));

        fish.setName(fishForm.getName());
        fish.setPrice(fishForm.getPrice());
        fish.setDescription(fishForm.getDescription());
        fish.setSize(fishForm.getSize());
        fish.setQuantity(fishForm.getQuantity());

        System.out.println("fishForm:" + fishForm.getName());
        if (!fishForm.getImage().isEmpty()) {
            String publicId = cloudinaryService.getPublicIdFromImgUrl(fish.getImage(), "fish");
            Map<String, Object> result = cloudinaryService.deleteImage(publicId);
            String imgUrl = cloudinaryService.handleUploadImg(fishForm.getImage(), "fish");
            fish.setImage(imgUrl);
        }
        FishType fishType = fishTypeRepository.findById(fishForm.getFishTypeId())
                .orElseThrow(() -> new IllegalArgumentException("FishType ID không hợp lệ"));
        fish.setFishType(fishType);

        Farm farm = farmRepository.findById(fishForm.getFarmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm ID không hợp lệ"));
        fish.setFarm(farm);

        return FishDto.from(fishRepository.save(fish));
    }


    @Override
    public void deleteFish(Long id) {
        if (!fishRepository.existsById(id)) {
            throw new RuntimeException("Fish not found");
        }
        Fish fish = fishRepository.findById(id).orElseThrow(() -> new RuntimeException("Fish not found"));
        String imagePublicId = cloudinaryService.getPublicIdFromImgUrl(fish.getImage(), "fish");

        cloudinaryService.deleteImage(imagePublicId);
        fishRepository.deleteById(id);
    }
};
