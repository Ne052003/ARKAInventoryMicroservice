package com.neoapps.entry_points.controllers;

import com.neoapps.model.stockTransaction.StockTransaction;
import com.neoapps.usecase.GetStockUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/stockTransactions")
@RequiredArgsConstructor
public class StockTransactionController {
    private final GetStockUpdateUseCase getStockUpdateUseCase;

    @GetMapping
    public Mono<ResponseEntity<Flux<StockTransaction>>> getAll() {
        Flux<StockTransaction> transactionFlux = getStockUpdateUseCase.getAllTransactions();

        return transactionFlux.hasElements()
                .map(hasElements -> hasElements ? ResponseEntity.ok(transactionFlux) : ResponseEntity.notFound().build());
    }

}
