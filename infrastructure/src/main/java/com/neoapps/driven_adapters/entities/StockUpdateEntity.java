package com.neoapps.driven_adapters.entities;

import com.neoapps.model.stockUpdate.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "stock_updates")
@Getter
@Setter
@NoArgsConstructor
public class StockUpdateEntity {

    @Id
    @Column("stock_update_id")
    private Long id;

    @Column( "employee_id")
    private Long employeeId;

    @Column("product_id")
    private Long productId;

    @Column("update_type")
    private TransactionType updateType;

    private LocalDateTime date;

    private Integer quantity;

    public StockUpdateEntity(Long employeeId, Long product, TransactionType updateType, LocalDateTime date, Integer quantity) {
        this.employeeId = employeeId;
        this.productId = product;
        this.updateType = updateType;
        this.date = date;
        this.quantity = quantity;
    }
}
