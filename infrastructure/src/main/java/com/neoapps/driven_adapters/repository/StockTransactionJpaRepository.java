package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.StockTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransactionJpaRepository extends JpaRepository<StockTransactionEntity, Long> {
}
