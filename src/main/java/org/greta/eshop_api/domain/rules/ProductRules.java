package org.greta.eshop_api.domain.rules;

import org.greta.eshop_api.persistence.entities.ProductEntity;

import java.time.LocalDateTime;

public class ProductRules {

    public static void validateBeforeCreation(ProductEntity product) {
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Le prix doit être supérieur à 0.");
        }
        if (product.getStock() < 0) {
            throw new RuntimeException("Le stock ne peut pas être négatif.");
        }
    }

    public static void validateBeforeUpdate(ProductEntity product) {
        if (product.getPrice() > 10000) {
            throw new RuntimeException("Le prix dépasse la limite autorisée.");
        }
    }

    public static void validateDiscount(ProductEntity product) {
        if (product.getDiscount() > 90) {
            throw new RuntimeException("la promo ne peut pas dépasser 90%");
        }
        if (product.getDiscount() < 0) {
            throw new RuntimeException("la promo ne peut pas être négative");
        }
        if (product.getPrice() * (1 / product.getDiscount()) < 0) {
            throw new RuntimeException("le prix après réduction ne peut pas être négatif");
        }
        if (!product.getIsActive() && product.getDiscount() > 0) {
            throw new RuntimeException("Un produit inactif ne peut pas avoir de promo");
        }
    }
}
