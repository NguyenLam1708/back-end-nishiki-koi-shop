package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    boolean existsByName(String name);
}
