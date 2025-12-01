package com.neoapps.model.category;

import java.time.LocalDateTime;

public class Category {
    private Long id;
    private CategoryName name;
    private CategoryDescription description;
    private LocalDateTime creationTime;

    public Category(String name, String description) {
        this.name = new CategoryName(name);
        this.description = new CategoryDescription(description);
        this.creationTime = LocalDateTime.now();
    }

    public Category() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name.value();
    }

    public String getDescription() {
        return this.description.value();
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = new CategoryName(name);
    }

    public void setDescription(String description) {
        this.description = new CategoryDescription(description);
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
