package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.dto.OrderTourDetailDto;
import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.example.nishiki_koi_shop.model.entity.OrderTourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTourDetailRepository extends JpaRepository<OrderTourDetail, Long> {
    List<OrderTourDetail> findAllByOrderTour(OrderTour orderTour);
}
