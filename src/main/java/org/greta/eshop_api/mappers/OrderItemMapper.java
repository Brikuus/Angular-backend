package org.greta.eshop_api.mappers;

import org.greta.eshop_api.exposition.dtos.OrderItemRequestDTO;
import org.greta.eshop_api.exposition.dtos.OrderItemResponseDTO;
import org.greta.eshop_api.persistence.entities.OrderItemEntity;

public class OrderItemMapper {

    public static OrderItemEntity toEntity(OrderItemRequestDTO dto) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setQuantity(dto.quantity());
        entity.setUnit_price(dto.unit_price());
        return entity;
    }

    public static OrderItemResponseDTO toDto(OrderItemEntity entity) {
        return new OrderItemResponseDTO(
                entity.getId(),
                entity.getQuantity(),
                entity.getUnit_price()
        );
    }
}
