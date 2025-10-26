package com.neoapps.model.gateway;

import com.neoapps.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryGateway {

    Optional<Product> getProductById(Long id);

    boolean existsByName(String name);

    boolean existsById(Long id);

    List<Product> getAllProducts();

    void save(Product product);
}
