package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Deal;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal,Integer> {

    @Query(value = "SELECT * FROM Deal d JOIN Promotion p ON d.promotion_id = p.promotion_id WHERE p.promotion_id = :id"
            ,nativeQuery = true)
    List<Deal> getAllDealWithPromotion(@Param("id") int id);
}
