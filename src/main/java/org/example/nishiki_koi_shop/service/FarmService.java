package com.example.nishikiKoiShop.services;

import com.example.nishikiKoiShop.entities.Farm;
import com.example.nishikiKoiShop.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id).orElse(null);
    }

    public Farm saveFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}
