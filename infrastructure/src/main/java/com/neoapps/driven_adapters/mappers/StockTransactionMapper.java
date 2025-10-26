package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.StockTransactionEntity;
import com.neoapps.model.stockTransaction.StockTransaction;
import org.springframework.stereotype.Component;

@Component
public class StockTransactionMapper {
    public StockTransactionEntity toEntity(StockTransaction stockTransaction) {

        if (stockTransaction == null) {
            return null;
        }
        return new StockTransactionEntity(
                stockTransaction.getOrderId(),
                stockTransaction.getProductId(),
                stockTransaction.getTransactionType(),
                stockTransaction.getTimeStamp(),
                stockTransaction.getQuantity());

    }

    public StockTransaction toStockTransaction(StockTransactionEntity entity) {
        if (entity == null) return null;

        StockTransaction stockTransaction = new StockTransaction(
                entity.getOrderId(),
                entity.getProductId(),
                entity.getTransactionType(),
                entity.getQuantity(),
                entity.getTimeStamp()
        );
        stockTransaction.setId(entity.getId());

        return stockTransaction;
    }
}
