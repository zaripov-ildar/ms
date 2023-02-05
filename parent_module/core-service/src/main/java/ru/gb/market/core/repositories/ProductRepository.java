package ru.gb.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market.core.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}