package com.neoapps.driven_adapters.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "brands")
@Getter
@Setter
public class BrandEntity {
    @Id
    @Column("brand_id")
    private Long id;

    private String name;

    @Column("created_at")
    private LocalDateTime creationTime;
}
