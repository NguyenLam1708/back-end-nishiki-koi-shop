package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.entity.Tour;
import org.example.nishiki_koi_shop.model.payload.TourForm;

import java.util.List;
import java.util.Optional;

public interface TourService {
    List<Tour> getAllTours();
    Optional<Tour> getTourById(Long id);
    Tour saveTour(Tour tour);
    Tour updateTour(Long id, Tour updatedTour);
    void deleteTour(Long id);
    
    // Declaration of createTour method
    Tour createTour(TourForm form);
}
