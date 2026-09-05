package com.cafeteria.CafeteriaProjeto.database;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdicionaisItemPedido(
        @NotBlank String nome,
        @NotNull int quantidade
) {
}
