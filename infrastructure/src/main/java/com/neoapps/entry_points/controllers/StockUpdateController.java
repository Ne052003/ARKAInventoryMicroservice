package com.neoapps.entry_points.controllers;

import com.neoapps.model.stockUpdate.StockUpdate;
import com.neoapps.usecase.GetStockUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/stockupdates")
@RequiredArgsConstructor
public class StockUpdateController {
    private final GetStockUpdateUseCase getStockUpdateUseCase;

    @GetMapping
    public Mono<ResponseEntity<Flux<StockUpdate>>> getAll() {
        return Mono.just(ResponseEntity.ok(getStockUpdateUseCase.getAll()));
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Flux<StockUpdate>>> getByProductId(@PathVariable Long productId) {
        Flux<StockUpdate> stockUpdates = getStockUpdateUseCase.getByProductId(productId);
        return stockUpdates.hasElements()
                .map(hasElements -> hasElements ? ResponseEntity.ok(stockUpdates) : ResponseEntity.notFound().build());
    }
}
