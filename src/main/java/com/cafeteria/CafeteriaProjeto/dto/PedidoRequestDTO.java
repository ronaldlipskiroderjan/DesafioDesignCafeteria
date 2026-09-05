package com.cafeteria.CafeteriaProjeto.dto;

import com.cafeteria.CafeteriaProjeto.database.ItemPedido;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PedidoRequestDTO(
        @NotBlank String nomeCliente,
        List<ItemPedido> itens
){
}
