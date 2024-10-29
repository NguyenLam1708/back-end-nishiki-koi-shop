package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.OrderTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTourRepository extends JpaRepository<OrderTour,Long> {
    List<OrderTour> findByUserId(long userId);
}
