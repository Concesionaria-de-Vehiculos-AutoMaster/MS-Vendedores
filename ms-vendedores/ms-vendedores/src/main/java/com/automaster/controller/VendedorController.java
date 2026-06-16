package com.automaster.controller;

import com.automaster.dto.*;
import com.automaster.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vendedores")
@RequiredArgsConstructor
@Tag(name="Garantias",description = "Venta de accesorios y piezas.")
public class VendedorController {

    private final VendedorService vendedorService;

    @PostMapping
    @Operation(summary = "Obtener todos los vendedores  ", description = "Obtiene una lista de todos los vendedores ")
    public ResponseEntity<VendedorResponseDTO> crear(@RequestBody VendedorRequestDTO dto) {
        return new ResponseEntity<>(vendedorService.guardarVendedor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/mis-clientes")
    @Operation(summary = "Crear nuevo vendedor ", description = "Creacion de nuevos vendedores  ")
    public ResponseEntity<List<ClienteResponseDTO>> verClientesAsignados() {
        return ResponseEntity.ok(vendedorService.listarClientesDelVendedor());
    }
}