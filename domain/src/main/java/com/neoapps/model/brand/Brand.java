package com.neoapps.model.brand;

import java.time.LocalDateTime;

public class Brand {

    private Long id;
    private BrandName name;
    private LocalDateTime creationTime;

    public Brand(String name) {
        this.name = new BrandName(name);
        this.creationTime = LocalDateTime.now();
    }

    public Brand(Long id) {
        this.id = id;
    }

    public Brand() {
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
        this.name = new BrandName(name);
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
