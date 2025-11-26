package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.mappers.SupplierMapper;
import com.neoapps.model.gateway.SupplierRepositoryGateway;
import com.neoapps.model.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SupplierRepositoryAdapter implements SupplierRepositoryGateway {
    private final SupplierReactiveRepository supplierReactiveRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public Mono<Supplier> getById(Long id) {
        return supplierReactiveRepository.findById(id).map(supplierMapper::toSupplier);
    }
}
