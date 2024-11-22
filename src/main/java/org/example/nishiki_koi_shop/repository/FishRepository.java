package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.dto.FishDto;
import org.example.nishiki_koi_shop.model.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
    @Query("select f from Fish f where f.fishType.fishTypeId =:fishType order by f.fishId limit 3")
    List<FishDto> findByFishType(@Param("fishType") Long fishType);

    @Query("select f from Fish f where f.farm.farmId =:farmId")
    List<FishDto> findByFarmId(@Param("farmId") Long farmId);
}
