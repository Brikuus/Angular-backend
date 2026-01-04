package org.greta.eshop_api.domain.services;

import org.greta.eshop_api.exceptions.ResourceNotFoundException;
import org.greta.eshop_api.exposition.dtos.OrderItemRequestDTO;
import org.greta.eshop_api.exposition.dtos.OrderItemResponseDTO;
import org.greta.eshop_api.mappers.OrderItemMapper;
import org.greta.eshop_api.persistence.entities.OrderItemEntity;
import org.greta.eshop_api.persistence.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItemResponseDTO> findAll() {
        return orderItemRepository.findAll().stream()
                .map(OrderItemMapper::toDto)
                .toList();
    }

    public OrderItemResponseDTO findById(Long id) {
        OrderItemEntity orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande avec l’ID " + id + " n’existe pas."));
        return OrderItemMapper.toDto(orderItem);
    }

    public OrderItemResponseDTO create(OrderItemRequestDTO dto) {
        OrderItemEntity entity = OrderItemMapper.toEntity(dto);
        OrderItemEntity saved = orderItemRepository.save(entity);
        return OrderItemMapper.toDto(saved);
    }

    public OrderItemResponseDTO update (Long id, OrderItemRequestDTO dto) {
        OrderItemEntity existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit " + id + " introuvable."));
        existing.updateFrom(dto);
        OrderItemEntity saved = orderItemRepository.save(existing);
        return OrderItemMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de supprimer : Produit " + id + " introuvable.");
        }
        orderItemRepository.deleteById(id);
    }
}
