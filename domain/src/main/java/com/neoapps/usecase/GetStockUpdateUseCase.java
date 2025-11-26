package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.gateway.StockTransactionRepositoryGateway;
import com.neoapps.model.gateway.StockUpdateRepositoryGateway;
import com.neoapps.model.stockTransaction.StockTransaction;
import com.neoapps.model.stockUpdate.StockUpdate;
import reactor.core.publisher.Flux;

public class GetStockUpdateUseCase {

    private final StockUpdateRepositoryGateway stockUpdateRepositoryGateway;
    private final StockTransactionRepositoryGateway stockTransactionRepository;

    public GetStockUpdateUseCase(StockUpdateRepositoryGateway stockUpdateRepositoryGateway, StockTransactionRepositoryGateway stockTransactionRepository) {
        this.stockUpdateRepositoryGateway = stockUpdateRepositoryGateway;
        this.stockTransactionRepository = stockTransactionRepository;
    }

    public Flux<StockUpdate> getByProductId(Long productId) {
        if (productId == null) throw new DomainException("Id can't be null", "ProductId");

        return stockUpdateRepositoryGateway.getByProductId(productId);

    }

    public Flux<StockUpdate> getAll() {
        return stockUpdateRepositoryGateway.getAll();
    }

    public Flux<StockTransaction> getAllTransactions() {
        return stockTransactionRepository.getAll();
    }
}
