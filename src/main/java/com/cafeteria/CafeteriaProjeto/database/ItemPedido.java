package com.cafeteria.CafeteriaProjeto.database;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ItemPedido(
     @NotBlank String nome,
     @NotBlank List<AdicionaisItemPedido> adicionais,
     @NotNull int quantidade
) {
}
