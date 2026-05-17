package com.automaster.msvendedoress.service;

import com.automaster.msvendedoress.dto.VendedorRequestDTO;
import com.automaster.msvendedoress.dto.VendedorResponseDTO;
import com.automaster.msvendedoress.model.Sucursal;
import com.automaster.msvendedoress.model.Vendedor;
import com.automaster.msvendedoress.repository.SucursalRepository;
import com.automaster.msvendedoress.repository.VendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class VendedorServiceImpl {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    public VendedorResponseDTO crearVendedor(VendedorRequestDTO request) {
        log.info("Iniciando creación de vendedor con RUT: {}", request.getRut());

        if (vendedorRepository.existsByRut(request.getRut())) {
            log.error("El vendedor con RUT {} ya existe.", request.getRut());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El RUT ya está registrado.");
        }

        Sucursal sucursal = sucursalRepository.findById(request.getIdSucursal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La sucursal indicada no existe."));

        Vendedor vendedor = new Vendedor();
        vendedor.setRut(request.getRut());
        vendedor.setNombre(request.getNombre());
        vendedor.setMetaVentas(request.getMetaVentas());
        vendedor.setComisiones(request.getComisiones());
        vendedor.setSucursal(sucursal);

        Vendedor guardado = vendedorRepository.save(vendedor);
        log.info("Vendedor creado con éxito. ID: {}", guardado.getId());

        return mapearADTO(guardado);
    }

    public VendedorResponseDTO buscarPorRut(String rut) {
        log.info("Buscando vendedor por RUT: {}", rut);
        Vendedor vendedor = vendedorRepository.findByRut(rut)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor no encontrado."));
        return mapearADTO(vendedor);
    }

    private VendedorResponseDTO mapearADTO(Vendedor vendedor) {
        VendedorResponseDTO dto = new VendedorResponseDTO();
        dto.setId(vendedor.getId());
        dto.setRut(vendedor.getRut());
        dto.setNombre(vendedor.getNombre());
        dto.setMetaVentas(vendedor.getMetaVentas());
        dto.setComisiones(vendedor.getComisiones());
        dto.setIdSucursal(vendedor.getSucursal().getId());
        dto.setNombreSucursal(vendedor.getSucursal().getNombre());
        return dto;
    }
}