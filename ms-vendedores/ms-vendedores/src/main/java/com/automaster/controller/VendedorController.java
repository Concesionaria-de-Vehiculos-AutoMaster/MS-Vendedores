package com.automaster.controller;

import com.automaster.dto.*;
import com.automaster.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorResponseDTO> crear(@RequestBody VendedorRequestDTO dto) {
        return new ResponseEntity<>(vendedorService.guardarVendedor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/mis-clientes")
    public ResponseEntity<List<ClienteResponseDTO>> verClientesAsignados() {
        return ResponseEntity.ok(vendedorService.listarClientesDelVendedor());
    }
}