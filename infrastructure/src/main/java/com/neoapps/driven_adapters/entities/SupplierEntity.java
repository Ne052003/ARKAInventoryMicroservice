package com.neoapps.driven_adapters.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "suppliers")
@Getter
@Setter
public class SupplierEntity {

    @Id
    @Column("supplier_id")
    private Long id;

    private String name;

    private String email;

    @Column("created_at")
    private LocalDateTime creationTime;
}
