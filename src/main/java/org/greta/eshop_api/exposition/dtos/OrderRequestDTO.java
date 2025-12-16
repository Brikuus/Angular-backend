package org.greta.eshop_api.exposition.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrderRequestDTO(

        @NotBlank(message = "le status ne peut pas être vide")
        @Size(max = 20, message = "la taille du status ne peut pas dépasser 20 caractères")
        String status
) {}
