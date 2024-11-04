package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.OrderFishDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFishDetailRepository extends JpaRepository<OrderFishDetail, Long> {
}
