package com.cafeteria.CafeteriaProjeto.service;

import com.cafeteria.CafeteriaProjeto.enums.Status;
import com.cafeteria.CafeteriaProjeto.database.PedidoEntity;
import com.cafeteria.CafeteriaProjeto.database.repository.ItemRepository;
import com.cafeteria.CafeteriaProjeto.database.repository.PedidoRepository;
import com.cafeteria.CafeteriaProjeto.dto.PedidoRequestDTO;
import com.cafeteria.CafeteriaProjeto.dto.PedidoResponseDTO;
import com.cafeteria.CafeteriaProjeto.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemRepository itemRepository;

    public UUID criarPedido(PedidoRequestDTO dto) {
        List<BigDecimal> valoresFinais = dto.itens().stream()
                .map(i -> {
                    BigDecimal valorItem = itemRepository
                            .findByNome(i.nome()).getValor();
                    List<BigDecimal> valorAdicionais = i.adicionais().stream()
                            .map(a -> itemRepository
                                    .findByNome(a.nome())
                                    .getValor()
                                    .multiply(
                                            BigDecimal.valueOf(a.quantidade())
                                    )
                            ).toList();
                    return valorAdicionais.stream()
                            .reduce(BigDecimal.ZERO,
                                    BigDecimal::add
                            ).add(valorItem)
                            .multiply(BigDecimal.valueOf(i.quantidade()));
                }).toList();
        PedidoEntity newPedido = pedidoRepository.save(PedidoEntity.builder()
                .nomeCliente(dto.nomeCliente())
                .itens(dto.itens())
                .valorFinal(valoresFinais.stream().reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                ))
                .status(Status.PENDENTE)
                .build()
        );
        return newPedido.getId();
    }

    public PedidoResponseDTO findPedido(UUID id) throws Exception {
        return pedidoRepository.findById(id)
                .map(p -> new PedidoResponseDTO(
                        p.getNomeCliente(),
                        p.getItens(),
                        p.getValorFinal(),
                        p.getStatus()
                ) )
                .orElseThrow(() -> new NotFoundException("Pedido não existente"));

    }

    public void fecharPedido(UUID id) throws Exception {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não cadastrado..."));
        if (pedido.getStatus().equals(Status.PENDENTE)) {
            pedido.setStatus(Status.FECHADO);
            pedidoRepository.save(pedido);
        } else {
            throw new BadRequestException("Pedido já fechado!!");
        }
    }
}