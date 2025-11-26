package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.BrandEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BrandReactiveRepository extends ReactiveCrudRepository<BrandEntity, Long> {
}
