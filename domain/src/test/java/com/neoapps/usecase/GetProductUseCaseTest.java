package com.neoapps.usecase;

import com.neoapps.model.brand.Brand;
import com.neoapps.model.category.Category;
import com.neoapps.model.gateway.BrandRepositoryGateway;
import com.neoapps.model.gateway.CategoryRepositoryGateway;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.gateway.SupplierRepositoryGateway;
import com.neoapps.model.product.Product;
import com.neoapps.model.supplier.Supplier;
import com.neoapps.usecase.dtos.GetProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductUseCaseTest {

    @Mock
    private ProductRepositoryGateway productRepositoryGateway;
    @Mock
    private SupplierRepositoryGateway supplierRepositoryGateway;
    @Mock
    private BrandRepositoryGateway brandRepositoryGateway;
    @Mock
    private CategoryRepositoryGateway categoryRepositoryGateway;

    private GetProductUseCase getProductUseCase;

    @BeforeEach
    void setUp() {
        getProductUseCase = new GetProductUseCase(productRepositoryGateway,
                supplierRepositoryGateway,
                brandRepositoryGateway,
                categoryRepositoryGateway);
    }

    @Test
    @DisplayName("Should return product details when the id provided is valid")
    void shouldReturnProductDetails_whenProductIdIsValid() {

        Long productId = 3L;

        Product product = createProduct(productId, "MACBOOK M4",
                "Laptop with 16GB RAM, 500 SSD",
                30,
                1500D,
                1500D,
                true,
                1L,
                2L,
                1L);

        Supplier supplier = createSupplier(1L, "MAC4P", "macp4prov@gmail.com");

        Brand brand = createBrand(2L, "MAC");

        Category category = createCategory(1L, "Laptops", "Portable computers");

        GetProductResponse expectedResponse = new GetProductResponse();

        expectedResponse.setId(product.getId());
        expectedResponse.setName(product.getName());
        expectedResponse.setDescription(product.getDescription());
        expectedResponse.setStock(product.getStock());
        expectedResponse.setRetailPrice(product.getRetailPrice());
        expectedResponse.setWholesalePrice(product.getWholeSalePrice());
        expectedResponse.setCreationTime(product.getCreationTime());
        expectedResponse.setSupplierName(supplier.getName());
        expectedResponse.setBrandName(brand.getName());
        expectedResponse.setCategoryName(category.getName());

        when(productRepositoryGateway.getProductById(productId)).thenReturn(Mono.just(product));
        when(supplierRepositoryGateway.getById(product.getSupplierId())).thenReturn(Mono.just(supplier));
        when(brandRepositoryGateway.getById(product.getBrandId())).thenReturn(Mono.just(brand));
        when(categoryRepositoryGateway.getById(product.getCategoryId())).thenReturn(Mono.just(category));

        StepVerifier.create(getProductUseCase.getProductById(productId))
                .expectNext(expectedResponse)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Should return all the products stored in the repository")
    void shouldReturnAllProductsFromTheRepository() {

        Product product1 = createProduct(1L, "MACBOOK M4",
                "Laptop with 16GB RAM, 500 SSD",
                30,
                1500D,
                1500D,
                true,
                1L,
                2L,
                1L);

        Product product2 = createProduct(2L, "Lenovo Laptop",
                "Laptop with 8GB RAM, 500 SSD",
                40,
                1000D,
                800D,
                true,
                2L,
                1L,
                1L);

        Supplier supplier1 = createSupplier(1L, "MAC4P", "macp4prov@gmail.com");

        Brand brand1 = createBrand(1L, "MAC");

        Supplier supplier2 = createSupplier(2L, "PComputers8", "computers8@gmail.com");

        Brand brand2 = createBrand(2L, "Lenovo");

        Category category = createCategory(1L, "Laptops", "Portable computers");

        when(productRepositoryGateway.getAllProducts()).thenReturn(Flux.just(product1, product2));

        when(supplierRepositoryGateway.getById(product1.getSupplierId())).thenReturn(Mono.just(supplier1));
        when(supplierRepositoryGateway.getById(product2.getSupplierId())).thenReturn(Mono.just(supplier2));

        when(brandRepositoryGateway.getById(product1.getBrandId())).thenReturn(Mono.just(brand1));
        when(brandRepositoryGateway.getById(product2.getBrandId())).thenReturn(Mono.just(brand2));

        when(categoryRepositoryGateway.getById(product1.getCategoryId())).thenReturn(Mono.just(category));

        StepVerifier.create(getProductUseCase.getAll())
                .expectNextMatches(response -> response.getName().equals("MACBOOK M4"))
                .expectNextMatches(response -> response.getName().equals("Lenovo Laptop"))
                .verifyComplete();
    }

    private Product createProduct(Long id, String name, String description, Integer stock, Double retailPrice, Double wholesalePrice, boolean isActive, Long supplierId, Long brandId, Long categoryId) {
        Product product = new Product(name,
                description,
                stock,
                retailPrice,
                wholesalePrice,
                isActive,
                supplierId,
                brandId,
                categoryId);
        product.setId(id);

        return product;
    }

    private Supplier createSupplier(Long id, String name, String email) {
        Supplier supplier = new Supplier(name, email);
        supplier.setId(id);

        return supplier;
    }

    private Brand createBrand(Long id, String name) {
        Brand brand = new Brand(name);
        brand.setId(id);

        return brand;
    }

    private Category createCategory(Long id, String name, String description) {
        Category category = new Category(name, description);
        category.setId(id);

        return category;
    }
}
