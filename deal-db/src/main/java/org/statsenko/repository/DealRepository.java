package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal,Integer> {
}
