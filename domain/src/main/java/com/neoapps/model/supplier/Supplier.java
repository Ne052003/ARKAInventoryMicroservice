package com.neoapps.model.supplier;

import java.time.LocalDateTime;

public class Supplier {
    private Long id;
    private SupplierName name;
    private SupplierEmail email;
    private LocalDateTime creationTime;

    public Supplier(String name, String email) {
        this.name = new SupplierName(name);
        this.email = new SupplierEmail(email);
        this.creationTime = LocalDateTime.now();
    }

    public Supplier() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name.value();
    }

    public String getEmail() {
        return this.email.value();
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = new SupplierName(name);
    }

    public void setEmail(String email) {
        this.email = new SupplierEmail(email);
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
