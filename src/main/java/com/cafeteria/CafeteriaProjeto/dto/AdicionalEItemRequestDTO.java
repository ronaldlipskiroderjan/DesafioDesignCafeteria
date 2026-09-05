package com.cafeteria.CafeteriaProjeto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AdicionalEItemRequestDTO(
        @NotBlank String nome,
        @NotNull BigDecimal valor
) {
}
