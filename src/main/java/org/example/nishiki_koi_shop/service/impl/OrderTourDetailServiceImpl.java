package org.example.nishiki_koi_shop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nishiki_koi_shop.model.dto.OrderTourDetailDto;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.OrderTourDetail;
import org.example.nishiki_koi_shop.model.entity.Tour;
import org.example.nishiki_koi_shop.model.payload.OrderTourDetailForm;
import org.example.nishiki_koi_shop.repository.OrderTourDetailRepository;
import org.example.nishiki_koi_shop.repository.OrderTourRepository;
import org.example.nishiki_koi_shop.repository.TourRepository;
import org.example.nishiki_koi_shop.service.OrderTourDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderTourDetailServiceImpl implements OrderTourDetailService {

    private final OrderTourDetailRepository orderTourDetailRepository;
    private final OrderTourRepository orderTourRepository;
    private final TourRepository tourRepository;

    @Override
    public OrderTourDetailDto createOrderTourDetail(OrderTourDetailForm form) {
        log.info("Creating OrderTourDetail with form: {}", form);

        Tour tour = tourRepository.findById(form.getTourId())
                .orElseThrow(() -> new EntityNotFoundException("Tour not found with id: " + form.getTourId()));

        OrderTour orderTour = orderTourRepository.findById(form.getOrderTourId())
                .orElseThrow(() -> new EntityNotFoundException("OrderTour not found with id: " + form.getOrderTourId()));

        OrderTourDetail orderTourDetail = OrderTourDetail.builder()
                .numberOfPeople(form.getNumberOfPeople())
                .price(form.getPrice())
                .orderTour(orderTour)
                .tour(tour)
                .build();

        orderTourDetail = orderTourDetailRepository.save(orderTourDetail);
        log.info("OrderTourDetail created with ID: {}", orderTourDetail.getOrderTourDetailId());

        return OrderTourDetailDto.from(orderTourDetail);
    }

    @Override
    public OrderTourDetailDto getOrderTourDetailById(long id) {
        log.info("Fetching OrderTourDetail with id: {}", id);

        OrderTourDetail orderTourDetail = orderTourDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderTourDetail not found with id: " + id));

        return OrderTourDetailDto.from(orderTourDetail);
    }

    @Override
    public List<OrderTourDetailDto> getAllOrderTourDetails() {
        log.info("Fetching all OrderTourDetails");

        return orderTourDetailRepository.findAll().stream()
                .map(OrderTourDetailDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public OrderTourDetailDto updateOrderTourDetail(long id, OrderTourDetailForm form) {
        log.info("Updating OrderTourDetail with id: {}", id);

        OrderTourDetail orderTourDetail = orderTourDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderTourDetail not found with id: " + id));

        Tour tour = tourRepository.findById(form.getTourId())
                .orElseThrow(() -> new EntityNotFoundException("Tour not found with id: " + form.getTourId()));

        orderTourDetail.setNumberOfPeople(form.getNumberOfPeople());
        orderTourDetail.setPrice(form.getPrice());
        orderTourDetail.setTour(tour);

        OrderTourDetail updatedDetail = orderTourDetailRepository.save(orderTourDetail);
        log.info("OrderTourDetail updated with ID: {}", updatedDetail.getOrderTourDetailId());

        return OrderTourDetailDto.from(updatedDetail);
    }
}
