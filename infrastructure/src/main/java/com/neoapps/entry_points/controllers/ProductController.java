package com.neoapps.entry_points.controllers;

import com.neoapps.usecase.UpdateProductStockUseCase;
import com.neoapps.usecase.dtos.UpdateProductStockRequest;
import com.neoapps.usecase.GetProductUseCase;
import com.neoapps.usecase.dtos.CreateProductRequest;
import com.neoapps.usecase.dtos.GetProductResponse;
import com.neoapps.usecase.RegisterProductUseCase;
import com.neoapps.usecase.dtos.UpdateStockByOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final RegisterProductUseCase registerProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {

        registerProductUseCase.createProduct(request);
        return ResponseEntity.ok("The product " + request.getName() + " was registered successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponse> getProductById(@PathVariable Long id) {

        Optional<GetProductResponse> getProductResponse = getProductUseCase.getProductById(id);

        return getProductResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<GetProductResponse>> getAll() {
        List<GetProductResponse> productResponseList = getProductUseCase.getAll();
        return ResponseEntity.ok(productResponseList);
    }

    @PutMapping("/increaseStockUpdate")
    public ResponseEntity<String> increaseProductStockByEmployee(@RequestBody UpdateProductStockRequest request) {
        updateProductStockUseCase.increaseProductStock(request);
        return ResponseEntity.ok("The product stock was updated successfully");

    }

    @PutMapping("/reduceStockTransaction")
    public ResponseEntity<String> reduceProductStockByOrder(@RequestBody UpdateStockByOrderRequest request) {
        updateProductStockUseCase.reduceProductStockByOrder(request);
        return ResponseEntity.ok("The product stock was updated successfully for order: " + request.getOrderId());
    }

}
