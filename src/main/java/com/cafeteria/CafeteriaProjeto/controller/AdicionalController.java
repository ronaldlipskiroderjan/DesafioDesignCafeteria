package com.cafeteria.CafeteriaProjeto.controller;

import com.cafeteria.CafeteriaProjeto.dto.AdicionalEItemRequestDTO;
import com.cafeteria.CafeteriaProjeto.dto.AdicionalEItemResponseDTO;
import com.cafeteria.CafeteriaProjeto.service.AdicionaisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/adicionais")
@RequiredArgsConstructor
public class AdicionalController {

    private final AdicionaisService adicionaisService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(@Valid @RequestBody AdicionalEItemRequestDTO dto) throws Exception {
        adicionaisService.adicionar(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdicionalEItemResponseDTO> findAll() {
        return adicionaisService.findAll();
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id,
                       @Valid @RequestBody AdicionalEItemRequestDTO dto) throws Exception {
        adicionaisService.update(id, dto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) throws Exception {
        adicionaisService.delete(id);
    }
}
