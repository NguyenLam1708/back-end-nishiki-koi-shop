package org.example.nishiki_koi_shop.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nishiki_koi_shop.model.dto.OrderFishDetailDto;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.example.nishiki_koi_shop.model.entity.OrderFish;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.OrderFishDetailForm;
import org.example.nishiki_koi_shop.repository.FishRepository;
import org.example.nishiki_koi_shop.repository.OrderFishDetailRepository;
import org.example.nishiki_koi_shop.repository.OrderFishRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.OrderFishDetailService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderFishDetailServiceImpl implements OrderFishDetailService {

    private final OrderFishDetailRepository orderFishDetailRepository;
    private final OrderFishRepository orderFishRepository;

    @Override
    public List<OrderFishDetailDto> getOrderFishDetailByOrderFishId(long orderFishId) {
        OrderFish orderFish = orderFishRepository.findById(orderFishId).orElseThrow();
        return orderFishDetailRepository.findAllByOrderFish(orderFish).stream()
                .map(OrderFishDetailDto::from)
                .collect(Collectors.toList());
    }

}
