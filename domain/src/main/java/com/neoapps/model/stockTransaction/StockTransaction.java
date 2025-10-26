package com.neoapps.model.stockTransaction;

import com.neoapps.model.product.Stock;
import com.neoapps.model.stockUpdate.TransactionType;

import java.time.LocalDateTime;

public class StockTransaction {
    private Long id;
    private Long orderId;
    private Long productId;
    private TransactionType transactionType;
    private Stock quantity;
    private LocalDateTime timeStamp;

    public StockTransaction(Long orderId, Long productId, TransactionType transactionType, Integer quantity, LocalDateTime timeStamp) {
        this.orderId = orderId;
        this.productId = productId;
        this.transactionType = transactionType;
        this.quantity = new Stock(quantity);
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getQuantity() {
        return this.quantity.value();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = new Stock(quantity);
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
