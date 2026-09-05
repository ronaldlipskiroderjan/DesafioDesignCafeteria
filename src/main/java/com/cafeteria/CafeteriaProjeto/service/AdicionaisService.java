package com.cafeteria.CafeteriaProjeto.service;

import com.cafeteria.CafeteriaProjeto.database.AdicionaisEntity;
import com.cafeteria.CafeteriaProjeto.database.repository.AdicionaisRepository;
import com.cafeteria.CafeteriaProjeto.dto.AdicionalEItemRequestDTO;
import com.cafeteria.CafeteriaProjeto.dto.AdicionalEItemResponseDTO;
import com.cafeteria.CafeteriaProjeto.exception.AlreadyExistsException;
import com.cafeteria.CafeteriaProjeto.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdicionaisService {

    private final AdicionaisRepository adicionaisRepository;

    public void adicionar(AdicionalEItemRequestDTO dto) throws Exception{
        if(adicionaisRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new AlreadyExistsException("Adicional já cadastrado!");
        }
        adicionaisRepository.save(AdicionaisEntity.builder()
                .nome(dto.nome())
                .valor(dto.valor())
                .build());
    }

    public List<AdicionalEItemResponseDTO> findAll() {
        return adicionaisRepository.findAll()
                .stream()
                .map(a -> new AdicionalEItemResponseDTO(
                        a.getNome(),
                        a.getValor()
                )).toList();
    }

    public void update(String id, AdicionalEItemRequestDTO dto) throws Exception{
        AdicionaisEntity adicional = adicionaisRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Adicional não encontrado..."));
        adicional.setNome(dto.nome());
        adicional.setValor(dto.valor());
        adicionaisRepository.save(adicional);
    }

    public void delete(String id) throws Exception{
        if (!adicionaisRepository.existsById(id)) {
            throw new NotFoundException("Adicional não encontrado...");
        }
        adicionaisRepository.deleteById(id);
    }
}
