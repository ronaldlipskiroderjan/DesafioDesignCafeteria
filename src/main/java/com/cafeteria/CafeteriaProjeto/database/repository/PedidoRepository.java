package com.cafeteria.CafeteriaProjeto.database.repository;

import com.cafeteria.CafeteriaProjeto.database.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
    Optional<PedidoEntity> findByNomeCliente(String nome);
}
