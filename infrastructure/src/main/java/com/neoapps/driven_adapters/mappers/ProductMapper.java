package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.ProductEntity;
import com.neoapps.model.brand.Brand;
import com.neoapps.model.category.Category;
import com.neoapps.model.product.Product;
import com.neoapps.model.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Product toProduct(ProductEntity productEntity) {

        if (productEntity == null) {
            return null;
        }

        Supplier supplier = new Supplier(productEntity.getSupplierId());

        Brand brand = new Brand(productEntity.getBrandId());

        Category category = new Category(productEntity.getCategoryId());

        Product product = new Product(productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getStock(),
                productEntity.getRetailPrice().doubleValue(),
                productEntity.getWholesalePrice().doubleValue(),
                productEntity.isActive(),
                supplier,
                brand,
                category);
        product.setId(productEntity.getId());
        product.setCreationTime(productEntity.getCreationTime());

        return product;

    }

    public ProductEntity toProductEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity(product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getRetailPrice(),
                product.getWholeSalePrice(),
                product.isActive(),
                product.getSupplier().getId(),
                product.getBrand().getId(),
                product.getCategory().getId());

        productEntity.setId(product.getId());
        productEntity.setCreationTime(product.getCreationTime());

        return productEntity;
    }
}
