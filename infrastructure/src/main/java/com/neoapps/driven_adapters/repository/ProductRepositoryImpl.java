package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.ProductEntity;
import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.driven_adapters.mappers.ProductMapper;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryGateway {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    @Override
    public Optional<Product> getProductById(Long id) {

        Optional<ProductEntity> optProductEntity = productJpaRepository.findById(id);

        if (optProductEntity.isEmpty()) {
            return Optional.empty();
        }

        return optProductEntity.map(productMapper::toProduct);
    }

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByName(name);
    }
    @Override
    public boolean existsById(Long id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productJpaRepository.findAll();
        return productEntities.stream().map(productMapper::toProduct).toList();
    }

    @Override
    public void save(Product product) {

        ProductEntity productEntity = productMapper.toProductEntity(product);

        productJpaRepository.save(productEntity);

        if (!productJpaRepository.existsByName(product.getName())) {
            throw new RepositoryException("The product could not be saved", "Product");
        }
    }
}
