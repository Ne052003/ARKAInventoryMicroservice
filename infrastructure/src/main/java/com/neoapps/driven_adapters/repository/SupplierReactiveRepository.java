package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.SupplierEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SupplierReactiveRepository extends ReactiveCrudRepository<SupplierEntity, Long> {
}
