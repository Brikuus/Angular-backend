package org.greta.eshop_api.exposition.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequestDTO(

        @NotBlank(message = "le prénom ne peut pas être vide")
        @Size(max = 50, message = "la taille du prénom ne doit pas dépasser 50 caractères")
        String first_name,

        @NotBlank(message = "le nom de famille ne peut être vide")
        @Size(max = 50, message = "la taille du nom de famille ne doit pas dépasser 50 caractères")
        String last_name
) {}
