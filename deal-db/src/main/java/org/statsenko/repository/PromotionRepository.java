package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
}
