package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.FarmDto;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.payload.FarmForm;
import org.example.nishiki_koi_shop.model.payload.FishForm;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.service.CloudinaryService;
import org.example.nishiki_koi_shop.service.FarmService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final CloudinaryService cloudinaryService;

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
                .image(cloudinaryService.handleUploadImg(farmForm.getImage(), "farm"))
                .contactInfo(farmForm.getContactInfo())
                .createdDate(LocalDate.now())
                .build();
        farm = farmRepository.save(farm);
        return FarmDto.from(farm);
    }

    @Override
    public FarmDto getFarmById(long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID nong trai khong ton tai"));
        return FarmDto.from(farm);
    }

    @Override
    public FarmDto updateFarm(Long id, FarmForm farmForm) {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID farm khong ton tai"));

        farm.setName(farmForm.getName());
        farm.setDescription(farmForm.getDescription());
        farm.setContactInfo(farmForm.getContactInfo());
        farm.setLocation(farmForm.getLocation());

        if (!farmForm.getImage().isEmpty()) {
            String publicID = cloudinaryService.getPublicIdFromImgUrl(farm.getImage(), "farm");
            cloudinaryService.deleteImage(publicID);
            String imgUrl = cloudinaryService.handleUploadImg(farmForm.getImage(), "farm");
            farm.setImage(imgUrl);
        }

        farmRepository.save(farm);

        return FarmDto.from(farm);
    }

    @Override
    public void deleteFarm(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID farm không tồn tại"));

        String publicId = cloudinaryService.getPublicIdFromImgUrl(farm.getImage(), "farm");
        cloudinaryService.deleteImage(publicId);
        farmRepository.delete(farm);
    }
}
