package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.brand.Brand;
import com.neoapps.model.category.Category;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.product.Product;
import com.neoapps.model.supplier.Supplier;
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
                .flatMap(exists -> exists ? Mono.error(new DomainException("A product named: '" + createProductRequest.getName() + "' already exists", "ProductName")) : Mono.empty())
                .then(productRepositoryGateway.save(buildProduct(createProductRequest)));
    }

    private Product buildProduct(CreateProductRequest createProductRequest) {

        Supplier supplier = new Supplier(createProductRequest.getSupplierId());
        Brand brand = new Brand(createProductRequest.getBrandId());
        Category category = new Category(createProductRequest.getCategoryId());

        return new Product(
                createProductRequest.getName(),
                createProductRequest.getDescription(),
                createProductRequest.getStock(),
                createProductRequest.getRetailPrice(),
                createProductRequest.getWholesalePrice(),
                createProductRequest.isActive(),
                supplier,
                brand,
                category);
    }
}
