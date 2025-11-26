package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.StockTransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransactionReactiveRepository extends ReactiveCrudRepository<StockTransactionEntity, Long> {
}
