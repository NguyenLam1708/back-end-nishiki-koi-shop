package org.example.nishiki_koi_shop.service.impl;

import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.payload.FarmForm;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmServiceImpl implements FarmService {
    @Autowired
    private FarmRepository farmRepository;

    @Override
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    @Override
    public Optional<Farm> getFarmById(Long id) {
        return farmRepository.findById(id);
    }

    @Override
    public Farm saveFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    @Override
    public Farm createFarm(FarmForm farmForm) {
        Farm farm = new Farm();
        farm.setName(farmForm.getName());
        farm.setDescription(farmForm.getDescription());
        farm.setLocation(farmForm.getLocation());
        farm.setImage(farmForm.getImage());
        farm.setContactInfo(farmForm.getContactInfo());
        farm.setCreatedDate(farmForm.getCreatedDate());
        farm.setOwnerName(farmForm.getOwnerName()); // Thêm ownerName

        return farmRepository.save(farm);
    }

    @Override
    public Farm updateFarm(Long id, Farm updatedFarm) {
        return farmRepository.findById(id).map(farm -> {
            farm.setName(updatedFarm.getName());
            farm.setDescription(updatedFarm.getDescription());
            farm.setLocation(updatedFarm.getLocation());
            farm.setImage(updatedFarm.getImage());
            farm.setContactInfo(updatedFarm.getContactInfo());
            farm.setCreatedDate(updatedFarm.getCreatedDate());
            farm.setOwnerName(updatedFarm.getOwnerName()); // Cập nhật ownerName

            return farmRepository.save(farm);
        }).orElse(null);
    }

    @Override
    public void deleteFarm(Long id) {
        farmRepository.deleteById(id); // Xóa farm theo id
    }
}
