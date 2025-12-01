package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.brand.Brand;
import com.neoapps.model.category.Category;
import com.neoapps.model.gateway.BrandRepositoryGateway;
import com.neoapps.model.gateway.CategoryRepositoryGateway;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.gateway.SupplierRepositoryGateway;
import com.neoapps.model.product.Product;
import com.neoapps.model.supplier.Supplier;
import com.neoapps.usecase.dtos.GetProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetProductUseCase {
    private final ProductRepositoryGateway productRepository;
    private final SupplierRepositoryGateway supplierRepositoryGateway;
    private final BrandRepositoryGateway brandRepositoryGateway;
    private final CategoryRepositoryGateway categoryRepositoryGateway;

    private final Logger logger = LoggerFactory.getLogger(GetProductUseCase.class);

    public GetProductUseCase(ProductRepositoryGateway productRepository, SupplierRepositoryGateway supplierRepositoryGateway, BrandRepositoryGateway brandRepositoryGateway, CategoryRepositoryGateway categoryRepositoryGateway) {
        this.productRepository = productRepository;
        this.supplierRepositoryGateway = supplierRepositoryGateway;
        this.brandRepositoryGateway = brandRepositoryGateway;
        this.categoryRepositoryGateway = categoryRepositoryGateway;
    }

    public Mono<GetProductResponse> getProductById(Long id) {

        return productRepository.getProductById(id)
                .flatMap(this::getProductResponseWithDetails)
                .doOnError(error -> logger.error("Error when searching product with id: {}", id, error));
    }

    public Flux<GetProductResponse> getAll() {
        return productRepository.getAllProducts()
                .flatMap(this::getProductResponseWithDetails, 5);
    }

    private Mono<GetProductResponse> getProductResponseWithDetails(Product product) {
        return Mono.zip(getSupplierNameById(product.getSupplierId()),
                        getBrandNameById(product.getBrandId()),
                        getCategoryNameById(product.getCategoryId()))
                .map(tuple -> mapProductToResponse(product, tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .onErrorResume(error -> {
                    logger.error("Error trying to set details to product: {}: {}", product.getId(), error.getMessage());
                    return Mono.empty();
                });
    }


    private Mono<String> getSupplierNameById(Long supplierId) {
        if (supplierId == null) {
            return Mono.error(new DomainException("The supplier id can't be null", "SupplierId"));
        }

        return supplierRepositoryGateway.getById(supplierId)
                .map(Supplier::getName)
                .retry(2)
                .onErrorReturn("Unknown");
    }

    private Mono<String> getBrandNameById(Long brandId) {
        if (brandId == null) {
            return Mono.error(new DomainException("The brand id can't be null", "BrandId"));
        }

        return brandRepositoryGateway.getById(brandId)
                .map(Brand::getName)
                .retry(2)
                .onErrorReturn("Unknown");
    }

    private Mono<String> getCategoryNameById(Long categoryId) {
        if (categoryId == null) {
            return Mono.error(new DomainException("The category id can't be null", "CategoryId"));
        }

        return categoryRepositoryGateway.getById(categoryId)
                .map(Category::getName)
                .retry(2)
                .onErrorReturn("Unknown");
    }

    private GetProductResponse mapProductToResponse(Product product, String supplierName, String brandName, String categoryName) {

        GetProductResponse response = new GetProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setStock(product.getStock());
        response.setRetailPrice(product.getRetailPrice());
        response.setWholesalePrice(product.getWholeSalePrice());
        response.setCreationTime(product.getCreationTime());
        response.setSupplierName(supplierName);
        response.setBrandName(brandName);
        response.setCategoryName(categoryName);

        return response;
    }
}
