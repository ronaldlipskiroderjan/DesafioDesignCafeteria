package com.cafeteria.CafeteriaProjeto.database.repository;

import com.cafeteria.CafeteriaProjeto.database.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    boolean existsByNomeIgnoreCase(String Nome);
    ItemEntity findByNome(String nome);
}
