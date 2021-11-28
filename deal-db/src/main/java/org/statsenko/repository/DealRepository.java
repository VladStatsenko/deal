package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Deal;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal,Integer> {

    @Query("SELECT d FROM Deal d LEFT JOIN d.promotion p WHERE p.id = :id")
    List<Deal> findDealByPromotion(@Param("id") int promotionId);
}
