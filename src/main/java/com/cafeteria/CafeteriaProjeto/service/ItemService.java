package com.cafeteria.CafeteriaProjeto.service;

import com.cafeteria.CafeteriaProjeto.database.ItemEntity;
import com.cafeteria.CafeteriaProjeto.database.repository.ItemRepository;
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
public class ItemService {

    private final ItemRepository itemRepository;

    public void adicionarNovoItem(AdicionalEItemRequestDTO dto) throws Exception{
        if (itemRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new AlreadyExistsException("Item  já cadastrado!!!");
        }
        itemRepository.save(ItemEntity.builder()
                .nome(dto.nome())
                .valor((dto.valor()))
                .build()
        );
    }

    public List<AdicionalEItemResponseDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(i -> new AdicionalEItemResponseDTO(
                        i.getNome(),
                        i.getValor()
                )).toList();
    }

    public void update(String id, AdicionalEItemRequestDTO dto) throws Exception{
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item não encontrado..."));
        item.setNome(dto.nome());
        item.setValor(dto.valor());
        itemRepository.save(item);
    }

    public void delete(String id) throws Exception{
        if (!itemRepository.existsById(id)) {
            throw new NotFoundException("Item não encontrado...");
        }
        itemRepository.deleteById(id);
    }
}
