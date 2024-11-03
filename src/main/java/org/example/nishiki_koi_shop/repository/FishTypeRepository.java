package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.FishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FishTypeRepository extends JpaRepository<FishType, Long> {
    Optional<FishType> findFishTypesByName(String name);
    boolean existsByName(String name);
}
