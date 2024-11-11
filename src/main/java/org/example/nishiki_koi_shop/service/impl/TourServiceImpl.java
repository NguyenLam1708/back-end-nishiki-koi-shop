package org.example.nishiki_koi_shop.service.impl;

import org.example.nishiki_koi_shop.model.entity.Tour;
import org.example.nishiki_koi_shop.model.payload.TourForm;
import org.example.nishiki_koi_shop.repository.TourRepository;
import org.example.nishiki_koi_shop.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public Optional<Tour> getTourById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(Long id, Tour updatedTour) {
        return tourRepository.findById(id).map(tour -> {
            tour.setName(updatedTour.getName());
            tour.setDescription(updatedTour.getDescription());
            tour.setPrice(updatedTour.getPrice());
            return tourRepository.save(tour);
        }).orElse(null);
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
    
    // New createTour method to handle TourForm input
    @Override
    public Tour createTour(TourForm form) {
        Tour tour = new Tour();
        tour.setName(form.getName());
        tour.setDescription(form.getDescription());
        tour.setPrice(form.getPrice());
        // Populate other fields as necessary
        return tourRepository.save(tour);
    }
}
