package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.BrandEntity;
import com.neoapps.driven_adapters.entities.CategoryEntity;
import com.neoapps.driven_adapters.entities.ProductEntity;
import com.neoapps.driven_adapters.entities.SupplierEntity;
import com.neoapps.model.brand.Brand;
import com.neoapps.model.category.Category;
import com.neoapps.model.product.Product;
import com.neoapps.model.supplier.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final SupplierMapper supplierMapper;
    private final CategoryMapper categoryMapper;
    private final BrandMapper brandMapper;

    public Product toProduct(ProductEntity productEntity) {

        if (productEntity == null) {
            return null;
        }

        Supplier supplier = supplierMapper.toSupplier(productEntity.getSupplier());

        Brand brand = brandMapper.toBrand(productEntity.getBrand());

        Category category = categoryMapper.toCategory(productEntity.getCategory());

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

        SupplierEntity supplierEntity = supplierMapper.toSupplierEntity(product.getSupplier());

        BrandEntity brandEntity = brandMapper.toBrandEntity(product.getBrand());

        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(product.getCategory());


        ProductEntity productEntity = new ProductEntity(product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getRetailPrice(),
                product.getWholeSalePrice(),
                product.isActive(),
                supplierEntity,
                brandEntity,
                categoryEntity);

        productEntity.setId(product.getId());
        productEntity.setCreationTime(product.getCreationTime());

        return productEntity;
    }
}
