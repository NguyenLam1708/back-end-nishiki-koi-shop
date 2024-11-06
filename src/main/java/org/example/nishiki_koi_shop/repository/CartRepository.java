package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

    Optional<Cart> deleteByUserId(Long id);
}
