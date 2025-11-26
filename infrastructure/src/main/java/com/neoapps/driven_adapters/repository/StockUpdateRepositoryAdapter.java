package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.driven_adapters.mappers.StockUpdateMapper;
import com.neoapps.model.gateway.StockUpdateRepositoryGateway;
import com.neoapps.model.stockUpdate.StockUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StockUpdateRepositoryAdapter implements StockUpdateRepositoryGateway {

    private final StockUpdateReactiveRepository stockUpdateReactiveRepository;
    private final StockUpdateMapper stockUpdateMapper;

    @Override
    public Mono<Void> save(StockUpdate stockUpdate) {

        return stockUpdateReactiveRepository.save(stockUpdateMapper.toEntity(stockUpdate)).flatMap(updateSaved -> {
            if (!updateSaved.getQuantity().equals(stockUpdate.getQuantity())) {
                throw new RepositoryException("The stock was not successfully updated", "StockUpdate");
            }
            return Mono.empty();
        });
    }

    @Override
    public Flux<StockUpdate> getByProductId(Long productId) {

        return stockUpdateReactiveRepository.findByProductId(productId).map(stockUpdateMapper::toStockUpdate);
    }

    @Override
    public Flux<StockUpdate> getAll() {
        return stockUpdateReactiveRepository.findAll().map(stockUpdateMapper::toStockUpdate);
    }
}
