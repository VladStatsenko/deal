package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
}
