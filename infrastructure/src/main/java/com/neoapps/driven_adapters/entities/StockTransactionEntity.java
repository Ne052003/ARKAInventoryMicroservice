package com.neoapps.driven_adapters.entities;

import com.neoapps.model.stockUpdate.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transactions")
@Getter
@NoArgsConstructor
@Setter
public class StockTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private Integer quantity;

    public StockTransactionEntity(Long orderId, Long product, TransactionType transactionType, LocalDateTime timeStamp, Integer quantity) {
        this.orderId = orderId;
        this.productId = product;
        this.transactionType = transactionType;
        this.timeStamp = timeStamp;
        this.quantity = quantity;
    }
}
