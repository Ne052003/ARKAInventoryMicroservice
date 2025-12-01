package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
import com.neoapps.exceptions.ProductNotFoundException;
import com.neoapps.model.gateway.ProductRepositoryGateway;
import com.neoapps.model.gateway.StockTransactionRepositoryGateway;
import com.neoapps.model.gateway.StockUpdateRepositoryGateway;
import com.neoapps.model.stockTransaction.StockTransaction;
import com.neoapps.model.stockUpdate.StockUpdate;
import com.neoapps.model.stockUpdate.TransactionType;
import com.neoapps.usecase.dtos.UpdateProductStockRequest;
import com.neoapps.usecase.dtos.UpdateStockByOrderRequest;
import reactor.core.publisher.Mono;

public class UpdateProductStockUseCase {

    private final ProductRepositoryGateway productRepository;
    private final StockUpdateRepositoryGateway stockUpdateRepository;
    private final StockTransactionRepositoryGateway stockTransactionRepository;

    public UpdateProductStockUseCase(ProductRepositoryGateway productRepository, StockUpdateRepositoryGateway stockUpdateRepository, StockTransactionRepositoryGateway stockTransactionRepository) {
        this.productRepository = productRepository;
        this.stockUpdateRepository = stockUpdateRepository;
        this.stockTransactionRepository = stockTransactionRepository;
    }

    public Mono<Void> increaseProductStock(UpdateProductStockRequest request) {


        return productRepository.getProductById(request.getProductId())
                .switchIfEmpty(Mono.error(new ProductNotFoundException(request.getProductId())))
                .flatMap(product -> {
                    StockUpdate stockUpdate = new StockUpdate(request.getEmployeeId(), request.getProductId(), TransactionType.INPUT, request.getStock());

                    Integer newStock = product.getStock() + request.getStock();
                    product.setStock(newStock);

                    return productRepository.save(product).then(stockUpdateRepository.save(stockUpdate));
                });
    }

    public Mono<Void> reduceProductStockByOrder(UpdateStockByOrderRequest request) {

        if (request == null) {
            return Mono.error(new DomainException("Request can't be null", "UpdateStockByOrderRequest"));
        }

        return productRepository.getProductById(request.getProductId())
                .switchIfEmpty(Mono.error(new ProductNotFoundException(request.getProductId())))
                .flatMap(product -> {
                    StockTransaction stockTransaction = new StockTransaction(request.getOrderId(), request.getProductId(), TransactionType.OUTPUT, request.getQuantity(), request.getTimeStamp());

                    Integer newStock = product.getStock() - request.getQuantity();

                    product.setStock(newStock);

                    return productRepository.save(product).then(stockTransactionRepository.save(stockTransaction));
                });
    }
}
