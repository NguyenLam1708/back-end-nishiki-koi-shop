package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.TourDto;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.entity.Tour;
import org.example.nishiki_koi_shop.model.payload.TourForm;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.repository.TourRepository;
import org.example.nishiki_koi_shop.service.TourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final FarmRepository farmRepository;

    @Override
    public TourDto createTour(TourForm tourForm) {
        Farm farm = farmRepository.findById(tourForm.getFarmId()).orElseThrow(() -> new IllegalArgumentException("ID nông trại không hợp lệ!"));

        Tour tour = Tour.builder()
                .name(tourForm.getTourName())
                .description(tourForm.getTourDescription())
                .startDate(tourForm.getTourStartDate())
                .endDate(tourForm.getTourEndDate())
                .price(tourForm.getTourPrice())
                .farm(farm)
                .maxParticipants(tourForm.getTourCapacity())
                .build();
        tour = tourRepository.save(tour);
        return TourDto.from(tour);
    }

    @Override
    public List<TourDto> getAllTour() {
        return tourRepository.findAll().stream()
                .map(TourDto::from)
                .collect(Collectors.toList());
    }


}
