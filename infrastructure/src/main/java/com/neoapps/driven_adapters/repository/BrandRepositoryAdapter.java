package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.mappers.BrandMapper;
import com.neoapps.model.brand.Brand;
import com.neoapps.model.gateway.BrandRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BrandRepositoryAdapter implements BrandRepositoryGateway {

    private final BrandReactiveRepository brandReactiveRepository;
    private final BrandMapper brandMapper;

    @Override
    public Mono<Brand> getById(Long id) {
        return brandReactiveRepository.findById(id).map(brandMapper::toBrand);
    }
}
