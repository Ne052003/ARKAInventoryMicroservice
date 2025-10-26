package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.gateway.StockTransactionRepositoryGateway;
import com.neoapps.model.gateway.StockUpdateRepositoryGateway;
import com.neoapps.model.stockTransaction.StockTransaction;
import com.neoapps.model.stockUpdate.StockUpdate;

import java.util.List;

public class GetStockUpdateUseCase {

    private final StockUpdateRepositoryGateway stockUpdateRepositoryGateway;
    private final StockTransactionRepositoryGateway stockTransactionRepository;
    private final ProductRepositoryGateway productRepositoryGateway;

    public GetStockUpdateUseCase(StockUpdateRepositoryGateway stockUpdateRepositoryGateway, StockTransactionRepositoryGateway stockTransactionRepository, ProductRepositoryGateway productRepositoryGateway) {
        this.stockUpdateRepositoryGateway = stockUpdateRepositoryGateway;
        this.stockTransactionRepository = stockTransactionRepository;
        this.productRepositoryGateway = productRepositoryGateway;
    }

    public List<StockUpdate> getByProductId(Long productId) {
        if (productId == null) throw new DomainException("Id can't be null", "ProductId");

        if (!productRepositoryGateway.existsById(productId)) {
            throw new DomainException("A product with id " + productId + " does not exist", "ProductId");
        }

        return stockUpdateRepositoryGateway.getByProductId(productId);

    }

    public List<StockUpdate> getAll() {
        return stockUpdateRepositoryGateway.getAll();
    }

    public List<StockTransaction> getAllTransactions() {
        return stockTransactionRepository.getAll();
    }
}
