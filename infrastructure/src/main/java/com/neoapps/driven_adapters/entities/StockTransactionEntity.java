package com.neoapps.driven_adapters.entities;

import com.neoapps.model.stockUpdate.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "stock_transactions")
@Getter
@NoArgsConstructor
@Setter
public class StockTransactionEntity {
    @Id
    @Column("transaction_id")
    private Long id;

    @Column("order_id")
    private Long orderId;

    @Column("product_id")
    private Long productId;

    @Column("transaction_type")
    private TransactionType transactionType;

    private LocalDateTime timeStamp;

    private Integer quantity;

    public StockTransactionEntity(Long orderId, Long product, TransactionType transactionType, LocalDateTime timeStamp, Integer quantity) {
        this.orderId = orderId;
        this.productId = product;
        this.transactionType = transactionType;
        this.timeStamp = timeStamp;
        this.quantity = quantity;
    }
}
