package com.neoapps.driven_adapters.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "product_categories")
@Getter
@Setter
public class CategoryEntity {

    @Id
    @Column("category_id")
    private Long id;

    private String name;

    private String description;

    @Column("created_at")
    private LocalDateTime creationTime;
}


