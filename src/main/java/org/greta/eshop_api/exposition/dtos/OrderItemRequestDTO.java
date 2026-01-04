package org.greta.eshop_api.exposition.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderItemRequestDTO(

        @NotBlank(message = "le produit doit avoir une quantité")
        @PositiveOrZero(message = "la quantité ne peut pas être négative")
        Long quantity,

        @NotBlank(message = "le produit doit avoir un prix")
        @Min(value = 1, message = "Le prix doit être au moins de 1")
        @Max(value = 10000, message = "Le prix ne peut pas dépasser 10 000")
        double unit_price
) {}
