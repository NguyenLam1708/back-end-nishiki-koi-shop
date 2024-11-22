package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.dto.TourDto;
import org.example.nishiki_koi_shop.model.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Optional<Tour> findByName(String name);

    @Query("select t from Tour  t where t.farm.farmId=:farmId")
    List<TourDto> findTourByFarmId(@Param("farmId") long farmId);
}
