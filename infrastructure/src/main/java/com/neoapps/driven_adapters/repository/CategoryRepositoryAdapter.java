package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.mappers.CategoryMapper;
import com.neoapps.model.category.Category;
import com.neoapps.model.gateway.CategoryRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepositoryGateway {
    private final CategoryReactiveRepository categoryReactiveRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Mono<Category> getById(Long id) {
        return categoryReactiveRepository.findById(id).map(categoryMapper::toCategory);
    }
}
