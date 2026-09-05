package com.cafeteria.CafeteriaProjeto.dto;

import com.cafeteria.CafeteriaProjeto.enums.Status;
import com.cafeteria.CafeteriaProjeto.database.ItemPedido;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponseDTO(
    String nomeCliente,
    List<ItemPedido> itens,
    BigDecimal valorFinal,
    Status status
){
}
