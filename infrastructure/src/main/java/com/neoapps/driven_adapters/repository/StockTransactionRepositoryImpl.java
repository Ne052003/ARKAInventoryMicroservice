package com.neoapps.driven_adapters.repository;

import com.neoapps.driven_adapters.entities.StockTransactionEntity;
import com.neoapps.driven_adapters.exceptions.RepositoryException;
import com.neoapps.driven_adapters.mappers.StockTransactionMapper;
import com.neoapps.model.gateway.StockTransactionRepositoryGateway;
import com.neoapps.model.stockTransaction.StockTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockTransactionRepositoryImpl implements StockTransactionRepositoryGateway {

    private final StockTransactionJpaRepository stockTransactionRepository;
    private final StockTransactionMapper stockTransactionMapper;

    @Override
    public void save(StockTransaction stockTransaction) {

        StockTransactionEntity stockTransactionEntity = stockTransactionMapper.toEntity(stockTransaction);
        StockTransactionEntity stockTransactionEntitySaved = stockTransactionRepository.save(stockTransactionEntity);

        if (!stockTransactionEntitySaved.getQuantity().equals(stockTransaction.getQuantity())) {
            throw new RepositoryException("The stock was not successfully updated", "StockTransaction");
        }
    }

    @Override
    public List<StockTransaction> getAll() {
        return stockTransactionRepository.findAll().stream().map(stockTransactionMapper::toStockTransaction).toList();
    }
}
