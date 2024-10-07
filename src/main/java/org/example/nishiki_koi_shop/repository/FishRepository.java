package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {
}
