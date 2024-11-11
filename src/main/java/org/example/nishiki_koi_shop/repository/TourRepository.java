package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
    Optional<Tour> findByName(String name);
}
