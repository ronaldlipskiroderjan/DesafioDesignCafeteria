package com.cafeteria.CafeteriaProjeto.controller;

import com.cafeteria.CafeteriaProjeto.dto.AdicionalEItemRequestDTO;
import com.cafeteria.CafeteriaProjeto.dto.AdicionalEItemResponseDTO;
import com.cafeteria.CafeteriaProjeto.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody AdicionalEItemRequestDTO dto) throws Exception {
        itemService.adicionarNovoItem(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdicionalEItemResponseDTO> findAll() {
        return itemService.findAll();
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id, @Valid @RequestBody AdicionalEItemRequestDTO dto) throws Exception {
        itemService.update(id, dto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) throws Exception {
        itemService.delete(id);
    }
}