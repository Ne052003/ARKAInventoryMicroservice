package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.product.Product;
import com.neoapps.usecase.dtos.CreateProductRequest;
import reactor.core.publisher.Mono;

public class RegisterProductUseCase {

    private final ProductRepositoryGateway productRepositoryGateway;

    public RegisterProductUseCase(ProductRepositoryGateway productRepositoryGateway) {
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public Mono<Void> createProduct(CreateProductRequest createProductRequest) {

        if (createProductRequest == null) {
            throw new DomainException("CreateProductRequest can't be null", "CreateProductRequest");
        }

        if (createProductRequest.getWholesalePrice() >= createProductRequest.getRetailPrice()) {
            throw new DomainException("Wholesale price can't be higher or equal to retail price", "WholesalePrice");
        }

        return productRepositoryGateway.existsByName(createProductRequest.getName())
                .doOnNext(exists -> {
                    if (exists) {
                        throw new DomainException("A product named: '" + createProductRequest.getName() + "' already exists", "ProductName");
                    }
                })
                .then(productRepositoryGateway.save(buildProduct(createProductRequest)));
    }

    private Product buildProduct(CreateProductRequest createProductRequest) {

        return new Product(
                createProductRequest.getName(),
                createProductRequest.getDescription(),
                createProductRequest.getStock(),
                createProductRequest.getRetailPrice(),
                createProductRequest.getWholesalePrice(),
                createProductRequest.isActive(),
                createProductRequest.getSupplierId(),
                createProductRequest.getBrandId(),
                createProductRequest.getCategoryId());
    }
}
