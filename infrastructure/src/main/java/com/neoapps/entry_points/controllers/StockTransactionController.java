package com.neoapps.entry_points.controllers;

import com.neoapps.model.stockTransaction.StockTransaction;
import com.neoapps.usecase.GetStockUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stockTransactions")
@RequiredArgsConstructor
public class StockTransactionController {
    private final GetStockUpdateUseCase getStockUpdateUseCase;

    @GetMapping
    public ResponseEntity<List<StockTransaction>> getAll() {
        return ResponseEntity.ok(getStockUpdateUseCase.getAllTransactions());
    }

}
