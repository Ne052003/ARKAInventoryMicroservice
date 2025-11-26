package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.ProductEntity;
import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.driven_adapters.mappers.ProductMapper;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryGateway {

    private final ProductReactiveRepository productReactiveRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<Product> getProductById(Long id) {

        return productReactiveRepository.findById(id).map(productMapper::toProduct);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return productReactiveRepository.existsByName(name);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return productReactiveRepository.existsById(id);
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productReactiveRepository.findAll().map(productMapper::toProduct);
    }

    @Override
    public Mono<Void> save(Product product) {

        ProductEntity productEntity = productMapper.toProductEntity(product);

        return productReactiveRepository.save(productEntity).
                then(productReactiveRepository.existsByName(product.getName()))
                .flatMap(exists -> {
                    if (!exists) {
                        throw new RepositoryException("The product could not be saved", "Product");
                    }
                    return Mono.empty();
                });

    }
}
