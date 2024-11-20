package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.dto.FarmDto;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    boolean existsByName(String name);

    @Query("select f from Farm f where f.farmId IN  (select f2.farm.farmId from Fish f2 where f2.name=:fishName)")
    List<FarmDto> findByFishName(@Param("fishName") String fishName);
}
