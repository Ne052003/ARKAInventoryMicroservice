package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductReactiveRepository extends ReactiveCrudRepository<ProductEntity, Long> {

    Mono<Boolean> existsByName(String name);
}
