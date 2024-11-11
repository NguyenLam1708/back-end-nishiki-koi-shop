package org.example.nishiki_koi_shop.repository;

import org.example.nishiki_koi_shop.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
