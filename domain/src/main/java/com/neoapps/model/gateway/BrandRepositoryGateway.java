package com.neoapps.model.gateway;

import com.neoapps.model.brand.Brand;
import reactor.core.publisher.Mono;

public interface BrandRepositoryGateway {
    Mono<Brand> getById(Long id);
}
