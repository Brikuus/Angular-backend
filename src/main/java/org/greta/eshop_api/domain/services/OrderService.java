package org.greta.eshop_api.domain.services;

import org.greta.eshop_api.domain.rules.OrderRules;
import org.greta.eshop_api.exceptions.ResourceNotFoundException;
import org.greta.eshop_api.exposition.dtos.OrderRequestDTO;
import org.greta.eshop_api.exposition.dtos.OrderResponseDTO;
import org.greta.eshop_api.mappers.OrderMapper;
import org.greta.eshop_api.persistence.entities.OrderEntity;
import org.greta.eshop_api.persistence.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderResponseDTO findById(Long id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande avec l’ID " + id + " n’existe pas."));
        return OrderMapper.toDto(order);
    }

    public OrderResponseDTO create(OrderRequestDTO dto) {
        OrderEntity entity = OrderMapper.toEntity(dto);
        OrderRules.validateOrderStatus(entity);
        OrderRules.validateProducts(entity);
        OrderRules.validateCustomer(entity);
        OrderRules.validateStock(entity);
        OrderRules.validateTotal(entity);
        OrderEntity saved = orderRepository.save(entity);
        return OrderMapper.toDto(saved);
    }

    public OrderResponseDTO update (Long id,OrderRequestDTO dto) {
        OrderEntity existing = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande " + id + " introuvable."));
        existing.updateFrom(dto);
        OrderRules.validateOrderStatus(existing);
        OrderRules.validateProducts(existing);
        OrderRules.validateCustomer(existing);
        OrderRules.validateStock(existing);
        OrderRules.validateTotal(existing);
        OrderEntity saved = orderRepository.save(existing);
        return OrderMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de supprimer : commande" + id + "introuvable.");
        }
        orderRepository.deleteById(id);
    }
}
