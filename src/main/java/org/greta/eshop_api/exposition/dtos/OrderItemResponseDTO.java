package org.greta.eshop_api.exposition.dtos;

public record OrderItemResponseDTO(
        Long id,
        Long quantity,
        double unit_price
) {}
