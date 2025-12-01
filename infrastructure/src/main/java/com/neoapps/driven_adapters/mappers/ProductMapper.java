package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.ProductEntity;
import com.neoapps.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public Product toProduct(ProductEntity productEntity) {

        if (productEntity == null) {
            return null;
        }

        Product product = new Product(productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getStock(),
                productEntity.getRetailPrice().doubleValue(),
                productEntity.getWholesalePrice().doubleValue(),
                productEntity.isActive(),
                productEntity.getSupplierId(),
                productEntity.getBrandId(),
                productEntity.getCategoryId());

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
                product.getSupplierId(),
                product.getBrandId(),
                product.getCategoryId());

        productEntity.setId(product.getId());
        productEntity.setCreationTime(product.getCreationTime());

        return productEntity;
    }
}
