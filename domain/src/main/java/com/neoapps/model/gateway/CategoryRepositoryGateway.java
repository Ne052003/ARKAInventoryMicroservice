package com.neoapps.model.gateway;

import com.neoapps.model.category.Category;
import reactor.core.publisher.Mono;

public interface CategoryRepositoryGateway {
    Mono<Category> getById(Long id);
}
