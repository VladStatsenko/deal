package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
