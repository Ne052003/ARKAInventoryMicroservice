package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.StockUpdateEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StockUpdateReactiveRepository extends ReactiveCrudRepository<StockUpdateEntity, Long> {

    Flux<StockUpdateEntity> findByProductId(Long id);
}
