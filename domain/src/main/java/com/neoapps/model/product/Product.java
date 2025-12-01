package com.neoapps.model.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private Long id;
    private ProductName name;
    private ProductDescription productDescription;
    private Stock stock;
    private RetailPrice retailPrice;
    private WholeSalePrice wholeSalePrice;
    private boolean isActive;
    private LocalDateTime creationTime;
    private Long supplierId;
    private Long brandId;
    private Long categoryId;

    public Product(
            String name,
            String description,
            Integer stock,
            Double retailPrice,
            Double wholeSalePrice,
            boolean isActive,
            Long supplierId,
            Long brandId,
            Long categoryId) {
        this.name = new ProductName(name);
        this.productDescription = new ProductDescription(description);
        this.stock = new Stock(stock);
        this.retailPrice = new RetailPrice(retailPrice);
        this.wholeSalePrice = new WholeSalePrice(wholeSalePrice);
        this.isActive = isActive;
        this.creationTime = LocalDateTime.now();
        this.supplierId = supplierId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.creationTime = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name.value();
    }

    public void setName(String name) {
        this.name = new ProductName(name);
    }

    public String getDescription() {
        return this.productDescription.value();
    }

    public void setDescription(String description) {
        this.productDescription = new ProductDescription(description);
    }

    public int getStock() {
        return this.stock.value();
    }

    public void setStock(Integer stock) {
        this.stock = new Stock(stock);
    }

    public BigDecimal getRetailPrice() {
        return this.retailPrice.value();
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = new RetailPrice(retailPrice);
    }

    public BigDecimal getWholeSalePrice() {
        return this.wholeSalePrice.value();
    }

    public void setWholeSalePrice(Double wholeSalePrice) {
        this.wholeSalePrice = new WholeSalePrice(wholeSalePrice);
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Long getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(Long supplier) {
        this.supplierId = supplier;
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public void setBrandId(Long brand) {
        this.brandId = brand;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long category) {
        this.categoryId = category;
    }
}

