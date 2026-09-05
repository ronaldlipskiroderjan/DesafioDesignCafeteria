package com.cafeteria.CafeteriaProjeto.database.repository;

import com.cafeteria.CafeteriaProjeto.database.AdicionaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdicionaisRepository extends JpaRepository<AdicionaisEntity, String> {
    boolean existsByNomeIgnoreCase(String nome);
    AdicionaisEntity findByNome(String nome);
}
