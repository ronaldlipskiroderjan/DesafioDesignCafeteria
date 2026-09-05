package com.cafeteria.CafeteriaProjeto.database;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "adicionais")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdicionaisEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;
}
