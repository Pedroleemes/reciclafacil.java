package br.com.fiap.reciclafacil.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record DescarteRequestDto(
        @NotNull(message = "O peso do descarte é obrigatório.")
        @Positive(message = "O peso do descarte deve ser maior que zero.")
        Double pesoKg
) {}