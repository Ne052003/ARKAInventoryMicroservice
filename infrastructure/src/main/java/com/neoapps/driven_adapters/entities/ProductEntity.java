package com.neoapps.driven_adapters.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column("product_id")
    private Long id;

    private String name;

    private String description;

    private Integer stock;

    @Column("retail_price")
    private BigDecimal retailPrice;

    @Column("wholesale_price")
    private BigDecimal wholesalePrice;

    @Column("is_active")
    private boolean isActive;

    @Column("created_at")
    private LocalDateTime creationTime;

    @Column("supplier_id")
    private Long supplierId;

    @Column("brand_id")
    private Long brandId;

    @Column("category_id")
    private Long categoryId;

    public ProductEntity(String name,
                         String description,
                         Integer stock,
                         BigDecimal retailPrice,
                         BigDecimal wholesalePrice,
                         boolean isActive,
                         Long supplierId,
                         Long brandId,
                         Long categoryId) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
        this.isActive = isActive;
        this.supplierId = supplierId;
        this.brandId = brandId;
        this.categoryId = categoryId;
    }
}
