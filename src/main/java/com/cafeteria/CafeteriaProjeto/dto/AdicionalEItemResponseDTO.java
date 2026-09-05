package com.cafeteria.CafeteriaProjeto.dto;

import java.math.BigDecimal;

public record AdicionalEItemResponseDTO(
        String nome,
        BigDecimal valor
) {
}
