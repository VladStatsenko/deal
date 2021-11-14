package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Promotion;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {

    @Query(value = "SELECT * FROM Promotion p JOIN Product pr ON p.product_id = pr.product_id WHERE p.product_id = :id",
    nativeQuery = true)
    List<Promotion> getPromotionOfProduct(@Param("id") int id);

}
