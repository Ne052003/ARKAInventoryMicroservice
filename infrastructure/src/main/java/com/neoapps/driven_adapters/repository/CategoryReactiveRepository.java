package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.CategoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryReactiveRepository extends ReactiveCrudRepository<CategoryEntity, Long>{
}
