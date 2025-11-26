package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.driven_adapters.mappers.StockTransactionMapper;
import com.neoapps.model.gateway.StockTransactionRepositoryGateway;
import com.neoapps.model.stockTransaction.StockTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StockTransactionRepositoryAdapter implements StockTransactionRepositoryGateway {

    private final StockTransactionReactiveRepository stockTransactionRepository;
    private final StockTransactionMapper stockTransactionMapper;

    @Override
    public Mono<Void> save(StockTransaction stockTransaction) {

        return stockTransactionRepository.save(stockTransactionMapper.toEntity(stockTransaction))
                .flatMap(transactionSaved -> {

                    if (!transactionSaved.getQuantity().equals(stockTransaction.getQuantity())) {
                        throw new RepositoryException("The stock was not successfully updated", "StockTransaction");
                    }

                    return Mono.empty();
                });


    }

    @Override
    public Flux<StockTransaction> getAll() {
        return stockTransactionRepository.findAll().map(stockTransactionMapper::toStockTransaction);
    }
}
