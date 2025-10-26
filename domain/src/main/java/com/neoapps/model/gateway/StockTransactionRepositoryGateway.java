package com.neoapps.model.gateway;

import com.neoapps.model.stockTransaction.StockTransaction;

import java.util.List;

public interface StockTransactionRepositoryGateway {
    void save(StockTransaction stockTransaction);
    List<StockTransaction> getAll();
}
