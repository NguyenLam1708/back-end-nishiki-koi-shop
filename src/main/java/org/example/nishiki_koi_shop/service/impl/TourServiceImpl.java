package org.example.nishiki_koi_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.TourDto;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.entity.Tour;
import org.example.nishiki_koi_shop.model.payload.TourForm;
import org.example.nishiki_koi_shop.repository.FarmRepository;
import org.example.nishiki_koi_shop.repository.TourRepository;
import org.example.nishiki_koi_shop.service.CloudinaryService;
import org.example.nishiki_koi_shop.service.TourService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final FarmRepository farmRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public TourDto createTour(TourForm tourForm) {
        System.out.println("farm id:" + tourForm.getFarmId());
        Farm farm = farmRepository.findById(tourForm.getFarmId()).orElseThrow(() -> new IllegalArgumentException("ID nông trại không hợp lệ!"));

        Tour tour = Tour.builder()
                .name(tourForm.getName())
                .description(tourForm.getDescription())
                .startDate(tourForm.getStartDate())
                .endDate(tourForm.getEndDate())
                .price(tourForm.getPrice())
                .farm(farm)
                .image(cloudinaryService.handleUploadImg(tourForm.getImage(), "tour"))
                .maxParticipants(tourForm.getCapacity())
                .build();
        tour = tourRepository.save(tour);
        return TourDto.from(tour);
    }

    @Override
    public TourDto getTourById(long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tour"));
        return TourDto.from(tour);
    }

    @Override
    public TourDto updateTour(Long id, TourForm tourForm) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tour"));

        Farm farm = farmRepository.findById(tourForm.getFarmId()).orElseThrow(() -> new IllegalArgumentException("Khong tìm thây farm"));

        tour.setName(tourForm.getName());
        tour.setDescription(tourForm.getDescription());
        tour.setStartDate(tourForm.getStartDate());
        tour.setEndDate(tourForm.getEndDate());
        tour.setPrice(tourForm.getPrice());

        tour.setCreatedDate(LocalDate.now());
        tour.setFarm(farm);

        if (tourForm.getImage() != null) {
            String publicID = cloudinaryService.getPublicIdFromImgUrl(tour.getImage(), "tour");
            cloudinaryService.deleteImage(publicID);
            String imgUrl = cloudinaryService.handleUploadImg(tourForm.getImage(), "tour");
            tour.setImage(imgUrl);
        }

        return TourDto.from(tourRepository.save(tour));
    }

    @Override
    public void deleteTour(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy tour"));
        tourRepository.delete(tour);
    }

    @Override
    public List<TourDto> getAllTour() {
        return tourRepository.findAll().stream()
                .map(TourDto::from)
                .collect(Collectors.toList());
    }


}
