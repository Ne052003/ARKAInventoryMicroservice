package com.neoapps.model.gateway;

import com.neoapps.model.stockTransaction.StockTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockTransactionRepositoryGateway {
    Mono<Void> save(StockTransaction stockTransaction);
    Flux<StockTransaction> getAll();
}
