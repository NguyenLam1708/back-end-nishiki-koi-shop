package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.dto.OrderFishDetailDto;
import org.example.nishiki_koi_shop.model.entity.OrderFish;
import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFishDetailRepository extends JpaRepository<OrderFishDetail, Long> {
    List<OrderFishDetail> findAllByOrderFish(OrderFish orderFish);
}
