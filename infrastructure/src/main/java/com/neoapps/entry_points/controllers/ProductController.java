package com.neoapps.entry_points.controllers;

import com.neoapps.exceptions.DomainException;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final RegisterProductUseCase registerProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductStockUseCase updateProductStockUseCase;

    @PostMapping
    public Mono<ResponseEntity<String>> createProduct(@RequestBody CreateProductRequest request) {

        return registerProductUseCase.createProduct(request)
                .thenReturn(ResponseEntity.ok("The product " + request.getName() + " was registered successfully"))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<GetProductResponse>> getProductById(@PathVariable("id") Long id) {

        return getProductUseCase.getProductById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping
    public Mono<ResponseEntity<Flux<GetProductResponse>>> getAll() {

        Flux<GetProductResponse> responseFlux = getProductUseCase.getAll();

        return responseFlux.hasElements()
                .map(hasElements -> hasElements ? ResponseEntity.ok(responseFlux) : ResponseEntity.notFound().build());
    }

    @PutMapping("/increaseStockUpdate")
    public Mono<ResponseEntity<String>> increaseProductStockByEmployee(@RequestBody UpdateProductStockRequest request) {
        return updateProductStockUseCase.increaseProductStock(request)
                .thenReturn(ResponseEntity.ok("The product stock was updated successfully"))
                .onErrorReturn(DomainException.class, ResponseEntity.badRequest().build());
    }

    @PutMapping("/reduceStockTransaction")
    public Mono<ResponseEntity<String>> reduceProductStockByOrder(@RequestBody UpdateStockByOrderRequest request) {
        return updateProductStockUseCase.reduceProductStockByOrder(request)
                .thenReturn(ResponseEntity.ok("The product stock was updated successfully for order: " + request.getOrderId()))
                .onErrorReturn(DomainException.class, ResponseEntity.badRequest().build());
    }

}
