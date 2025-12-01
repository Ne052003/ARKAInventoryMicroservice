package com.neoapps.usecase;

import com.neoapps.exceptions.ProductAlreadyExistsException;
import com.neoapps.exceptions.StockCannotBeNegativeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.product.Product;
import com.neoapps.usecase.dtos.CreateProductRequest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class RegisterProductUseCaseTest {

    @Mock
    private ProductRepositoryGateway productRepositoryGateway;

    private RegisterProductUseCase registerProductUseCase;

    private Product product;

    @BeforeEach
    void setUp() {
        registerProductUseCase = new RegisterProductUseCase(productRepositoryGateway);

        product = new Product("MACBOOK M4",
                "Laptop with 16GB RAM, 500 SSD",
                30,
                1500D,
                1200D,
                true,
                1L,
                2L,
                1L);
    }

    @Test
    @DisplayName("Create a product without error")
    public void ShouldCreateProductSuccessfullyWithExpectedInput() {
        when(productRepositoryGateway.save(any(Product.class))).thenReturn(Mono.empty());
        when(productRepositoryGateway.existsByName(product.getName())).thenReturn(Mono.just(false));

        CreateProductRequest createProductRequest = getCreateProductRequest();

        StepVerifier.create(registerProductUseCase.createProduct(createProductRequest))
                .verifyComplete();
    }

    private CreateProductRequest getCreateProductRequest() {
        CreateProductRequest createProductRequest = new CreateProductRequest();

        createProductRequest.setName("MACBOOK M4");
        createProductRequest.setDescription("Laptop with 16GB RAM, 500 SSD");
        createProductRequest.setStock(30);
        createProductRequest.setIsActive(true);
        createProductRequest.setRetailPrice(1500D);
        createProductRequest.setWholesalePrice(1200D);
        createProductRequest.setSupplierId(1L);
        createProductRequest.setBrandId(2L);
        createProductRequest.setCategoryId(1L);
        return createProductRequest;
    }

    @Test
    @DisplayName("Should throw a DomainException when the CreateProductRequest has a null field")
    void shouldThrowDomainException_WhenCreateProductRequestHasNullFields() {
        CreateProductRequest createProductRequest = getCreateProductRequest();
        createProductRequest.setStock(null);

        when(productRepositoryGateway.existsByName(product.getName())).thenReturn(Mono.just(false));

        StepVerifier.create(registerProductUseCase.createProduct(createProductRequest))
                .expectErrorMatches(throwable -> throwable instanceof DomainException &&
                        throwable.getMessage().contains("Can't be null"))
                .verify();
    }

    @Test
    @DisplayName("Should throw a ProductAlreadyExistsException when the CreateProductRequest has the same name of another product")
    void shouldThrowProductAlreadyExistsException_WhenAProductWithTheSameNameAlreadyExists() {
        CreateProductRequest createProductRequest = getCreateProductRequest();

        when(productRepositoryGateway.existsByName(product.getName())).thenReturn(Mono.just(true));

        StepVerifier.create(registerProductUseCase.createProduct(createProductRequest))
                .expectErrorMatches(throwable -> throwable instanceof ProductAlreadyExistsException)
                .verify();
    }

    @Test
    @DisplayName("Should throw ProductCannotBeNegativeException when the stock provided is negative")
    void shouldThrowProductCannotBeNegativeException_WhenTheStockIsNegative() {
        CreateProductRequest createProductRequest = getCreateProductRequest();
        createProductRequest.setStock(-2);

        when(productRepositoryGateway.existsByName(product.getName())).thenReturn(Mono.just(false));

        StepVerifier.create(registerProductUseCase.createProduct(createProductRequest))
                .expectErrorMatches(throwable -> throwable instanceof StockCannotBeNegativeException)
                .verify();
    }

}
