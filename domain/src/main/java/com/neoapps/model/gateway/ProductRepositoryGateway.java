package com.neoapps.model.gateway;

import com.neoapps.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductRepositoryGateway {

    Mono<Product> getProductById(Long id);

    Mono<Boolean> existsByName(String name);

    Mono<Boolean> existsById(Long id);

    Flux<Product> getAllProducts();

    Mono<Void> save(Product product);
}
