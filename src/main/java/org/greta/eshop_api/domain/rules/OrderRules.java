package org.greta.eshop_api.domain.rules;

import org.greta.eshop_api.domain.services.CustomerService;
import org.greta.eshop_api.exposition.dtos.CustomerResponseDTO;
import org.greta.eshop_api.exposition.dtos.OrderItemRequestDTO;
import org.greta.eshop_api.persistence.entities.OrderEntity;
import org.greta.eshop_api.persistence.entities.OrderItemEntity;
import org.greta.eshop_api.persistence.entities.ProductEntity;
import org.greta.eshop_api.persistence.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class OrderRules {

    public static void validateCustomer(OrderEntity order) {
        if (order.getCustomerId().equals(null)) {
            throw new RuntimeException("le client n'existe pas");
        }
    }

    public static void validateProducts(OrderEntity order) {
        if (order.getItems().isEmpty()) {
            throw new RuntimeException("la commande ne peut pas être vide");
        }
    }

    public static void validateStock(OrderEntity order) {
        List<OrderItemEntity> items = order.getItems();
        for (OrderItemEntity item : items) {
            if (item.getQuantity() > item.getProduct().getStock()) {
                throw new RuntimeException("Il n'y a pas assez de stock pour pouvoir fournir la quantité demandé du produit :" + item.getId());

            }
        }
    }

    public static void validateTotal(OrderEntity order) {
        List<OrderItemEntity> items = order.getItems();

        double res = 0;
        for (OrderItemEntity item : items) {
            double accTemp= 0;
            accTemp = item.getUnitPrice() * item.getQuantity();
            res += accTemp;
        }

        if (res > 5000) {
            throw new RuntimeException("le prix maximum de la commande est de 5000 euros");
        }
    }

    public static void validateOrderStatus(OrderEntity order) {
        if (!order.getStatus().equals("PENDING") || !order.getStatus().equals("SHIPPED") || !order.getStatus().equals("DELIVERED") || !order.getStatus().equals("CANCELLED")) {
            throw new RuntimeException("le status ne peut qu'être égal à 'PENDING', 'SHIPPED', 'DELIVERED' ou 'CANCELLED'.");
        }
    }

    public static void validateStock2(OrderItemRequestDTO item, ProductEntity product) {
        if (item.quantity() > product.getStock()) {
            throw new RuntimeException("Stock insuffisant pour le produit " + product.getName());
        }
    }

    public static void validateTotal2(double total) {
        double max = 5000.0;
        if (total > max) {
            throw new RuntimeException("Le montant total de la commande dépasse le plafond autorisé (" + max + "€)");
        }
    }

    public static void validateOrderStatus2(String status) {
        List<String> allowed = List.of("PENDING", "SHIPPED", "DELIVERED", "CANCELLED");
        if (!allowed.contains(status)) {
            throw new RuntimeException("Statut de commande invalide : " + status);
        }
    }

    public static void validateProducts2(List<ProductEntity> products) {
        if (products.isEmpty())
            throw new RuntimeException("Aucun produit dans la commande");
        boolean allInactive = products.stream().noneMatch(ProductEntity::getIsActive);
        if (allInactive)
            throw new RuntimeException("Aucun produit actif dans la commande");
    }
}
