package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Promotion;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {

    @Query("SELECT p FROM Promotion p LEFT JOIN p.product pr WHERE pr.id = :id")
    List<Promotion> findPromotionByProduct(@Param("id") int productId);

}
