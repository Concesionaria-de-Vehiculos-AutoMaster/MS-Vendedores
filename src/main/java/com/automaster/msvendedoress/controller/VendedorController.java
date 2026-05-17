package com.automaster.msvendedoress.controller;

import com.automaster.msvendedoress.dto.VendedorRequestDTO;
import com.automaster.msvendedoress.dto.VendedorResponseDTO;
import com.automaster.msvendedoress.service.VendedorServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorServiceImpl vendedorService;

    @PostMapping
    public ResponseEntity<VendedorResponseDTO> registrarVendedor(@Valid @RequestBody VendedorRequestDTO request) {
        log.info("Petición POST recibida para registrar vendedor");
        VendedorResponseDTO response = vendedorService.crearVendedor(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<VendedorResponseDTO> obtenerPorRut(@PathVariable String rut) {
        log.info("Petición GET recibida para buscar vendedor por RUT");
        VendedorResponseDTO response = vendedorService.buscarPorRut(rut);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}