package com.neoapps.model.gateway;

import com.neoapps.model.stockUpdate.StockUpdate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockUpdateRepositoryGateway {

    Mono<Void> save(StockUpdate stockUpdate);

    Flux<StockUpdate> getByProductId(Long productId);

    Flux<StockUpdate> getAll();
}
