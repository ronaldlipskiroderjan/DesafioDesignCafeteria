package com.cafeteria.CafeteriaProjeto.controller;

import com.cafeteria.CafeteriaProjeto.dto.PedidoRequestDTO;
import com.cafeteria.CafeteriaProjeto.dto.PedidoResponseDTO;
import com.cafeteria.CafeteriaProjeto.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@Valid @RequestBody PedidoRequestDTO dto) {
        return pedidoService.criarPedido(dto);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponseDTO findById(@PathVariable UUID id) throws Exception{
        return pedidoService.findPedido(id);
    }

    @PatchMapping("/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void fecharPedido(@PathVariable UUID id) throws Exception{
        pedidoService.fecharPedido(id);
    }
}
