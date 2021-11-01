package org.statsenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.statsenko.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
