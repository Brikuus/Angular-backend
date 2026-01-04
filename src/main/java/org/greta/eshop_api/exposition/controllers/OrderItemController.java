package org.greta.eshop_api.exposition.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.greta.eshop_api.domain.services.OrderItemService;
import org.greta.eshop_api.exposition.dtos.OrderItemRequestDTO;
import org.greta.eshop_api.exposition.dtos.OrderItemResponseDTO;
import org.greta.eshop_api.exposition.dtos.ProductRequestDTO;
import org.greta.eshop_api.exposition.dtos.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDTO>> getAll() {
        List<OrderItemResponseDTO> response = orderItemService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> getById(@PathVariable Long id) {
        OrderItemResponseDTO response = orderItemService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDTO> create(@Valid @RequestBody OrderItemRequestDTO request) {
        OrderItemResponseDTO response = orderItemService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OrderItemRequestDTO request) {
        OrderItemResponseDTO response = orderItemService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
