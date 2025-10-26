package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.gateway.StockTransactionRepositoryGateway;
import com.neoapps.model.gateway.StockUpdateRepositoryGateway;
import com.neoapps.model.product.Product;
import com.neoapps.model.stockTransaction.StockTransaction;
import com.neoapps.model.stockUpdate.StockUpdate;
import com.neoapps.model.stockUpdate.TransactionType;
import com.neoapps.usecase.dtos.UpdateProductStockRequest;
import com.neoapps.usecase.dtos.UpdateStockByOrderRequest;

public class UpdateProductStockUseCase {

    private final ProductRepositoryGateway productRepository;
    private final StockUpdateRepositoryGateway stockUpdateRepository;
    private final StockTransactionRepositoryGateway stockTransactionRepository;

    public UpdateProductStockUseCase(ProductRepositoryGateway productRepository, StockUpdateRepositoryGateway stockUpdateRepository, StockTransactionRepositoryGateway stockTransactionRepository) {
        this.productRepository = productRepository;
        this.stockUpdateRepository = stockUpdateRepository;
        this.stockTransactionRepository = stockTransactionRepository;
    }

    public void increaseProductStock(UpdateProductStockRequest request) {

        validateUpdateProductStockRequest(request);

        Product product = productRepository.getProductById(request.getProductId())
                .orElseThrow(() -> new DomainException("A product with id " + request.getProductId() + " does not exist", "ProductId"));

        StockUpdate stockUpdate = new StockUpdate(request.getEmployeeId(), request.getProductId(), TransactionType.INPUT, request.getQuantity());

        Integer newStock = product.getStock() + request.getQuantity();

        product.setStock(newStock);
        productRepository.save(product);
        stockUpdateRepository.save(stockUpdate);

    }

    private void validateUpdateProductStockRequest(UpdateProductStockRequest request) {
        if (request == null) {
            throw new DomainException("Request can't be null", "UpdateStockProductRequest");
        }

        if (request.getQuantity() <= 0) {
            throw new DomainException("Stock can't be equal or less than 0", "UpdateProductStockRequest");
        }
    }

    public void reduceProductStockByOrder(UpdateStockByOrderRequest request) {
        if (request == null) {
            throw new DomainException("Request can't be null", "UpdateStockByOrderRequest");
        }

        Product product = productRepository.getProductById(request.getProductId())
                .orElseThrow(() -> new DomainException("A product with id " + request.getProductId() + " does not exist", "ProductId"));

        StockTransaction stockTransaction = new StockTransaction(request.getOrderId(), request.getProductId(), TransactionType.OUTPUT, request.getQuantity(), request.getTimeStamp());

        Integer newStock = product.getStock() - request.getQuantity();

        product.setStock(newStock);
        productRepository.save(product);
        stockTransactionRepository.save(stockTransaction);

    }
}
