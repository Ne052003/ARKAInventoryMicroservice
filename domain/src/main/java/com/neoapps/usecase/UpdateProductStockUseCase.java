package com.neoapps.usecase;

import com.neoapps.exceptions.DomainException;
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


        return validateUpdateProductStockRequest(request)
                .then(productRepository.getProductById(request.getProductId())
                        .switchIfEmpty(Mono.error(new DomainException("A product with id " + request.getProductId() + " does not exist", "ProductId")))
                        .flatMap(product -> {
                            StockUpdate stockUpdate = new StockUpdate(request.getEmployeeId(), request.getProductId(), TransactionType.INPUT, request.getQuantity());

                            Integer newStock = product.getStock() + request.getQuantity();
                            product.setStock(newStock);

                            return productRepository.save(product).then(stockUpdateRepository.save(stockUpdate));
                        }));
    }

    private Mono<Void> validateUpdateProductStockRequest(UpdateProductStockRequest request) {
        if (request == null) {
            return Mono.error(new DomainException("Request can't be null", "UpdateStockProductRequest"));
        }

        if (request.getQuantity() <= 0) {
            return Mono.error(new DomainException("Stock can't be equal or less than 0", "UpdateProductStockRequest"));
        }

        return Mono.empty();
    }

    public Mono<Void> reduceProductStockByOrder(UpdateStockByOrderRequest request) {

        if (request == null) {
            return Mono.error(new DomainException("Request can't be null", "UpdateStockByOrderRequest"));
        }

        return productRepository.getProductById(request.getProductId())
                .switchIfEmpty(Mono.error(new DomainException("A product with id " + request.getProductId() + " does not exist", "ProductId")))
                .flatMap(product -> {
                    StockTransaction stockTransaction = new StockTransaction(request.getOrderId(), request.getProductId(), TransactionType.OUTPUT, request.getQuantity(), request.getTimeStamp());

                    Integer newStock = product.getStock() - request.getQuantity();

                    product.setStock(newStock);

                    return productRepository.save(product).then(stockTransactionRepository.save(stockTransaction));
                });
    }
}
