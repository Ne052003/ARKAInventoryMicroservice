package com.neoapps.model.gateway;

import com.neoapps.model.supplier.Supplier;
import reactor.core.publisher.Mono;

public interface SupplierRepositoryGateway {
    Mono<Supplier> getById(Long id);
}
