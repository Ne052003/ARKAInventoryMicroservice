package com.neoapps.usecase;

import com.neoapps.model.gateway.BrandRepositoryGateway;
import com.neoapps.model.gateway.CategoryRepositoryGateway;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.gateway.SupplierRepositoryGateway;
import com.neoapps.usecase.dtos.GetProductResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetProductUseCase {
    private final ProductRepositoryGateway productRepository;
    private final SupplierRepositoryGateway supplierRepositoryGateway;
    private final BrandRepositoryGateway brandRepositoryGateway;
    private final CategoryRepositoryGateway categoryRepositoryGateway;

    public GetProductUseCase(ProductRepositoryGateway productRepository, SupplierRepositoryGateway supplierRepositoryGateway, BrandRepositoryGateway brandRepositoryGateway, CategoryRepositoryGateway categoryRepositoryGateway) {
        this.productRepository = productRepository;
        this.supplierRepositoryGateway = supplierRepositoryGateway;
        this.brandRepositoryGateway = brandRepositoryGateway;
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    public Mono<GetProductResponse> getProductById(Long id) {

        return productRepository.getProductById(id)
                .flatMap(product -> {
                    GetProductResponse response = new GetProductResponse();
                    response.setId(product.getId());
                    response.setName(product.getName());
                    response.setDescription(product.getDescription());
                    response.setStock(product.getStock());
                    response.setRetailPrice(product.getRetailPrice());
                    response.setWholesalePrice(product.getWholeSalePrice());
                    response.setCreationTime(product.getCreationTime());

                    return supplierRepositoryGateway.getById(product.getSupplier().getId())
                            .doOnNext(supplier -> response.setSupplierName(supplier.getName()))
                            .then(brandRepositoryGateway.getById(product.getBrand().getId())
                                    .doOnNext(brand -> response.setBrandName(brand.getName())))
                            .then(categoryRepositoryGateway.getById(product.getCategory().getId())
                                    .map(category -> {
                                        response.setCategoryName(category.getName());
                                        return response;
                                    }));

                });
    }

    public Flux<GetProductResponse> getAll() {
        return productRepository.getAllProducts()
                .flatMap(product -> {
                    GetProductResponse response = new GetProductResponse();
                    response.setId(product.getId());
                    response.setName(product.getName());
                    response.setDescription(product.getDescription());
                    response.setStock(product.getStock());
                    response.setRetailPrice(product.getRetailPrice());
                    response.setWholesalePrice(product.getWholeSalePrice());
                    response.setCreationTime(product.getCreationTime());

                    return supplierRepositoryGateway.getById(product.getSupplier().getId())
                            .doOnNext(supplier -> response.setSupplierName(supplier.getName()))
                            .then(brandRepositoryGateway.getById(product.getBrand().getId())
                                    .doOnNext(brand -> response.setBrandName(brand.getName())))
                            .then(categoryRepositoryGateway.getById(product.getCategory().getId())
                                    .map(category -> {
                                        response.setCategoryName(category.getName());
                                        return response;
                                    }));

                });
    }
}
